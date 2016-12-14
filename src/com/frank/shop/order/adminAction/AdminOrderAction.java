package com.frank.shop.order.adminAction;

import java.util.List;

import com.frank.shop.order.domain.Order;
import com.frank.shop.order.domain.OrderItem;
import com.frank.shop.order.service.OrderService;
import com.frank.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
	Order order=new Order();
	OrderService orderService;
	Integer page;
	public String findAll(){
		PageBean<Order> pageBean=orderService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	public String findOrderItem(){
		// ���ݶ���id��ѯ������:
			List<OrderItem> list = orderService.findOrderItem(order.getOid());
			// ��ʾ��ҳ��:
			ActionContext.getContext().getValueStack().set("list", list);
			// ҳ����ת
			return "findOrderItem";
	}
	
	public Order getModel() {
		return order;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
}
