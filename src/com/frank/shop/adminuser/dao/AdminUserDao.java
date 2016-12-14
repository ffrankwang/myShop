package com.frank.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.frank.shop.adminuser.domain.AdminUser;

public class AdminUserDao extends HibernateDaoSupport {

	public AdminUser find(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql, adminUser.getUsername(),adminUser.getPassword());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
