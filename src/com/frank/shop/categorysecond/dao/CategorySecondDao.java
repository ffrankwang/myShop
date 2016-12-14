package com.frank.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.frank.shop.categorysecond.domain.CategorySecond;
import com.frank.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport {

	public Integer findTotalCount() {
		String hql="select count(*) from CategorySecond";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0)return list.get(0).intValue();
		return 0;
	}

	public List<CategorySecond> findByPage(Integer begin, Integer limit) {
		String hql="from CategorySecond order by csid desc";
		List<CategorySecond> list=this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql,null,begin,limit));
		return list;
	}

	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
		
	}

	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
		
	}

	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
		
	}

	public CategorySecond findByCsid(Integer csid) {
		String hql="from CategorySecond cs where cs.csid=?";
		List<CategorySecond> list=this.getHibernateTemplate().find(hql, csid);
		if(list!=null&&list.size()>0)return list.get(0);
		return null;
	}

	public List<CategorySecond> findAll() {
		String hql="from CategorySecond order by csid desc";
		List<CategorySecond> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0)return list;
		return null;
	}

}
