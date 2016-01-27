package com.frank.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.frank.shop.user.domain.User;
import com.frank.shop.utils.MailUtils;
import com.frank.shop.utils.UUIDUtils;

public class UserDao extends HibernateDaoSupport {
	// 按用户名查询用户
	public User findByUsername(String username) {
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 查找用户激活码
	 * 
	 * @param code
	 *            激活码
	 */
	public User findByCode(String code) {
		String hql = "from User where code=?";
		List<User> list=this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 个修改用户状态的方法
	 * 
	 * @param existUser
	 */
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	public User login(User user) {
		String hql="from User where username=? and password=? and state=?";
		List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


	
}
