package com.frank.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.frank.shop.category.domain.Category;

public class CategoryDao extends HibernateDaoSupport {

	public List<Category> findAll() {
		String hql="from Category";//查询的是对象，首字母大写。
		List<Category> list=this.getHibernateTemplate().find(hql);
		return list;
	}

	public void save(Category category) {
		getHibernateTemplate().save(category);
	}

	public void delete(Category category) {
		getHibernateTemplate().delete(category);
		
	}

	public Category findByCid(Integer cid) {
		String hql="from Category where cid=?";
		List<Category> list=this.getHibernateTemplate().find(hql, cid);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void update(Category category) {
		this.getHibernateTemplate().update(category);
		
	}


}
