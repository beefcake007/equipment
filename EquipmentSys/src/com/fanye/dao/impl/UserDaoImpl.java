package com.fanye.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.fanye.dao.UserDao;
import com.fanye.model.PageBean;
import com.fanye.model.User;
import com.fanye.util.StringUtil;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User login(User user) {
		String sql="select * from t_user where userName=? and password=?";
		final User resultUser=new User();
		jdbcTemplate.query(sql, new Object[]{user.getUserName(),user.getPassword()},new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				resultUser.setId(rs.getInt("id"));
				resultUser.setUserName(rs.getString("userName"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setRoleName(rs.getString("roleName"));
			}
		});
		return resultUser;
	}

	@Override
	public List<User> findUserList(PageBean pageBean, User s_user) {
		StringBuffer sb=new StringBuffer("select * from t_user t1,t_department t2 where t1.deptId=t2.id");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				sb.append(" and t1.userName like '%"+s_user.getUserName()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<User> userList=new ArrayList<User>();
		jdbcTemplate.query(sb.toString().replaceFirst("and", "where"), new Object[]{},new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet re) throws SQLException {
				User user=new User();
				user.setId(re.getInt("id"));
				user.setUserName(re.getString("userName"));
				user.setPassword(re.getString("password"));
				user.setTrueName(re.getString("trueName"));
				user.setRoleName(re.getString("roleName"));
				user.setDeptId(re.getInt("deptId"));
				user.setDeptName(re.getString("deptName"));
				userList.add(user);
			}
		});
		return userList;
	}

	@Override
	public int count(User s_user) {
		StringBuffer sb=new StringBuffer("select count(*) from t_user t1,t_department t2 where t1.deptId=t2.id");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				sb.append(" and userName like '%"+s_user.getUserName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString().replaceFirst("and", "where"),Integer.class);
	}

	@Override
	public void delete(int id) {
		String sql="delete from t_user where id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

}
