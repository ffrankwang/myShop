package com.frank.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.frank.shop.user.domain.User;
import com.frank.shop.utils.MailUtils;
import com.frank.shop.utils.UUIDUtils;

public class UserDao extends HibernateDaoSupport{
	//按用户名查询用户
		public User findByUsername(String username){
			String hql = "from User where username = ?";
			List<User> list = this.getHibernateTemplate().find(hql, username);
			if(list != null && list.size() > 0){		
				return list.get(0);
			}
			return null;
		}
		
		public void save(User user) {
			String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
			user.setCode(code);
			user.setState(0);
			MailUtils.sendMain(user.getEmail(), code);
			this.getHibernateTemplate().save(user);		
		}
}
