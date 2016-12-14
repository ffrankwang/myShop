package com.frank.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.frank.shop.order.domain.Order;
import com.frank.shop.order.domain.OrderItem;
import com.frank.shop.utils.PageBean;
import com.frank.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport {

	public void saveOrder(Order order) {
		getHibernateTemplate().save(order);

	}

	public Integer findOrderCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0)
			return list.get(0).intValue();
		return null;
	}

	public List<Order> findOrdersByUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if (list != null && list.size() > 0)
			return list;
		return null;
	}

	public Order findOrderByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
		
	}

	public int findCount() {
		String hql="select count(*) from Order order by ordertime desc";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0) return list.get(0).intValue();
		return 0;
	}

	public List<Order> findAllByPage(int begin, int limit) {
		String hql="from Order order by ordertime desc ";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}

	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.oid=?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql, oid);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

}
