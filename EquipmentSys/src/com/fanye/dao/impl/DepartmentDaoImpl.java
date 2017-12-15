package com.fanye.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.fanye.dao.DepartmentDao;
import com.fanye.model.Department;
import com.fanye.model.PageBean;
import com.fanye.util.StringUtil;

@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao{
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Department> find(PageBean pageBean, Department s_department) {
		StringBuffer sb=new StringBuffer("select * from t_department");
		if(s_department!=null){
			if(StringUtil.isNotEmpty(s_department.getDeptName())){
				sb.append(" and deptName like '%"+s_department.getDeptName()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<Department> departmentList=new ArrayList<Department>();
		jdbcTemplate.query(sb.toString().replaceFirst("and", "where"), new Object[]{},new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet re) throws SQLException {
				Department department=new Department();
				department.setId(re.getInt("id"));
				department.setDeptName(re.getString("deptName"));
				department.setRemark(re.getString("remark"));
				departmentList.add(department);
			}
		});
		return departmentList;
	}

	@Override
	public int count(Department s_department) {
		StringBuffer sb=new StringBuffer("select count(*) from t_department");
		if(s_department!=null){
			if(StringUtil.isNotEmpty(s_department.getDeptName())){
				sb.append(" and deptName like '%"+s_department.getDeptName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString().replaceFirst("and", "where"),Integer.class);
	}

	@Override
	public void add(Department department) {
		String sql="insert into t_department values(null,?,?)";
		jdbcTemplate.update(sql, new Object[]{department.getDeptName(),department.getRemark()});
	}

	@Override
	public void update(Department department) {
		String sql="update t_department set deptName=?,remark=? where id=?";
		jdbcTemplate.update(sql, new Object[]{department.getDeptName(),department.getRemark(),department.getId()});
	}

	@Override
	public void delete(int id) {
		String sql="delete from t_department where id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public Department loadById(int id) {
		String sql="select * from t_department where id=?";
		final Department department=new Department();
		jdbcTemplate.query(sql, new Object[]{id},new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet re) throws SQLException {
				department.setId(re.getInt("id"));
				department.setDeptName(re.getString("deptName"));
				department.setRemark(re.getString("remark"));
			}
		});
		return department;
	}

}
