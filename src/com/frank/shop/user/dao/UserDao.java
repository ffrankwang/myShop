package com.frank.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.frank.shop.user.domain.User;
import com.frank.shop.utils.MailUtils;
import com.frank.shop.utils.UUIDUtils;

public class UserDao extends HibernateDaoSupport {
	// ���û�����ѯ�û�
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
	 * �����û�������
	 * 
	 * @param code
	 *            ������
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
	 * ���޸��û�״̬�ķ���
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
