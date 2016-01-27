package com.frank.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.frank.shop.user.dao.UserDao;
import com.frank.shop.user.domain.User;
import com.frank.shop.utils.MailUtils;
import com.frank.shop.utils.UUIDUtils;
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
