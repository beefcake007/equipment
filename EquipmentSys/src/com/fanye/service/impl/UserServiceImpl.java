package com.fanye.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fanye.dao.UserDao;
import com.fanye.model.PageBean;
import com.fanye.model.User;
import com.fanye.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public List<User> findUserList(PageBean pageBean, User s_user) {
		return userDao.findUserList(pageBean, s_user);
	}

	@Override
	public int count(User s_user) {
		return userDao.count(s_user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

}
