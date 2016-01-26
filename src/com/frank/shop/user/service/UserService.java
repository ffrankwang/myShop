package com.frank.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.frank.shop.user.dao.UserDao;
import com.frank.shop.user.domain.User;
@Transactional
public class UserService {
	
	//ע��UserDao
	private UserDao userDao;//ƴд���󣬵���userDao���ܱ�ע��Service,����Service����ע�뵽Action,�����ܷ���Action
	//���û�����ѯ�û�
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void save(User user) {
		userDao.save(user);
	}
	
}
