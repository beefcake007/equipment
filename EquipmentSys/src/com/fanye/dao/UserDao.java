package com.fanye.dao;

import java.util.List;

import com.fanye.model.PageBean;
import com.fanye.model.User;

public interface UserDao {

	public User login(User user);
	
	public List<User> findUserList(PageBean pageBean,User s_user);
	
	public int count(User s_user);
	
	public void delete(int id);
}
