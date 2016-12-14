package com.frank.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.frank.shop.product.domain.Product;
import com.frank.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport {

	public List<Product> findHot() {
		// ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		criteria.addOrder(Order.desc("pdate"));
		List<Product> hList = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		return hList;
	}

	public List<Product> findNew() {

		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> nList = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		return nList;
	}

	public Product findById(Integer pid) {

		return this.getHibernateTemplate().get(Product.class, pid);
	}

	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	// ���ݷ���id��ѯ��Ʒ�ļ���
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// select p.* from category c,categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2
		// select p from Category c,CategorySecond cs,Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// ��ҳ��һ��д��:
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
		
	}

	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		// ��ҳ��һ��д��:
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
//��ѯͳ��������Ʒ������
	public int findCount() {
		String hql="select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//��ҳ��ѯ��Ʒ
	public List<Product> findAll(int begin, int limit) {
		String hql="from Product p order by pid desc";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,null,begin,limit));
		return list;
	}

	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	public void delete(Product product) {
	this.getHibernateTemplate().delete(product);
		
	}

	public void update(Product product) {
		this.getHibernateTemplate().update(product);
		
	}
}
