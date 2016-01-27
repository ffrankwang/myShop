package com.frank.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.frank.shop.user.dao.UserDao;
import com.frank.shop.user.domain.User;
import com.frank.shop.utils.MailUtils;
import com.frank.shop.utils.UUIDUtils;
@Transactional
public class UserService {
	
	//注入UserDao
	private UserDao userDao;//拼写错误，导致userDao不能被注入Service,导致Service不能注入到Action,而后不能访问Action
	//按用户名查询用户
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void save(User user) {
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		user.setState(0);
		MailUtils.sendMail(user.getEmail(), code);
		userDao.save(user);
	}
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	
	public void update(User existUser){
		userDao.update(existUser);
	}
	public User login(User user) {
		
		return userDao.login(user);
	}
	
}
