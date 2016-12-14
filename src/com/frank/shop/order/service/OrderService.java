package com.frank.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.frank.shop.order.dao.OrderDao;
import com.frank.shop.order.domain.Order;
import com.frank.shop.order.domain.OrderItem;
import com.frank.shop.utils.PageBean;
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}


	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
		
	}


	public PageBean<Order> findOrdersByUid(Integer uid,Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//����ÿҳ��ʾ������
		Integer limit=5;
		pageBean.setLimit(limit);
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//�����ܵļ�¼��
		Integer totalCount=orderDao.findOrderCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		
		//������ҳ������ҳ��=�ܼ�¼��%ÿҳ������
		Integer totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findOrdersByUid(uid, begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


	public Order findOrderByOid(Integer oid) {
		Order order=orderDao.findOrderByOid(oid);
		return order;
	}


	public void update(Order currOrder) {
		orderDao.update(currOrder);
		
	}


	public PageBean<Order> findAllByPage(Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		int limit=10;//ÿҳ��ʾ��¼��
		int totalCount=0;//�ܶ�����
		int totalPage=0;//��ҳ��
		int begin=0;//ÿ�ο�ʼ��ѯ��¼��
		List<Order> list=null;//��ѯ���Ľ��
		totalCount=orderDao.findCount();
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit+1;
		}
		begin=(page-1)*limit;
		list=orderDao.findAllByPage(begin,limit);
		
		pageBean.setLimit(limit);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setPage(page);
		pageBean.setList(list);
		return pageBean;
	}


	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}



	
}
