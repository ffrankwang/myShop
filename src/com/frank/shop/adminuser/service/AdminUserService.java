package com.frank.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import com.frank.shop.adminuser.dao.AdminUserDao;
import com.frank.shop.adminuser.domain.AdminUser;
@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		return adminUserDao.find(adminUser);
	}
	
	
}
