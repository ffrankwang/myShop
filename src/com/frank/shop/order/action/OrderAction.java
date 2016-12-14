package com.frank.shop.order.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.frank.shop.cart.domain.Cart;
import com.frank.shop.cart.domain.CartItem;
import com.frank.shop.order.domain.Order;
import com.frank.shop.order.domain.OrderItem;
import com.frank.shop.order.service.OrderService;
import com.frank.shop.user.domain.User;
import com.frank.shop.utils.PageBean;
import com.frank.shop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private Order order = new Order();
	private OrderService orderService;
	private Integer page;// ��ȡҳ��
	private String pd_FrpId;// ��ȡ֧��ͨ������
	// ���ո���ɹ����ױ����ص���Ϣ
	private String r3_Amt;// ������
	private String r6_Order;// �̻�������

	public String saveOrder() {
		// ��ȡ���ﳵ��Ϣ
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");

		order.setTotal(cart.getTotal());
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("��!����û�й���!");
			return "login";
		}
		order.setUser(existUser);
		// ���ö�����
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		order.setState(1);
		order.setOrdertime(new Date());
		order.setAddr(existUser.getAddr());
		order.setName(existUser.getName());
		order.setPhone(existUser.getPhone());
		orderService.saveOrder(order);
		cart.clearCart();
		return "saveOrder";
	}

	// �����û�uid��ѯ����
	public String findOrdersByUid() {
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findOrdersByUid(
				existUser.getUid(), page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findOrdersByUid";
	}

	// ���ݶ�����Ų�ѯ����
	public String findOrderByOid() {
		order = orderService.findOrderByOid(order.getOid());
		// Order.hbm.xml�ļ��е�many-to-one�Զ�Ӧ��user������û�����ó�false�����Ե���order.getUser����Ϊ�գ�
		// ҳ��ת��<s:property value="model.user.name"/>��ʾ�������ݡ�
		return "findOrderByOid";
	}

	/* Ϊ�������� */
	public String payOrder() throws IOException {
		// 1.�޸����ݣ�
		System.out.println(order.getOid());
		Order currOrder = orderService.findOrderByOid(order.getOid());
		currOrder.setName(order.getName());
		currOrder.setAddr(order.getAddr());
		currOrder.setPhone(order.getPhone());
		// �޸Ķ���
		orderService.update(currOrder);
		// 2.��ɸ���
		// ������Ҫ�Ĳ���
		String p0_Cmd = "Buy"; // ҵ������:
		String p1_MerId = "10001126856";// �̻����:
		String p2_Order = order.getOid().toString();// �������:
		String p3_Amt = "0.01"; // ������:
		String p4_Cur = "CNY"; // ���ױ���:
		String p5_Pid = ""; // ��Ʒ����:
		String p6_Pcat = ""; // ��Ʒ����:
		String p7_Pdesc = ""; // ��Ʒ����:
		String p8_Url = "http://localhost:8080/myShop/order_callBack.action"; // �̻�����֧���ɹ����ݵĵ�ַ:
		String p9_SAF = ""; // �ͻ���ַ:
		String pa_MP = ""; // �̻���չ��Ϣ:
		String pd_FrpId = this.pd_FrpId;// ֧��ͨ������:
		String pr_NeedResponse = "1"; // Ӧ�����:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // ��Կ
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		
		
		
		// ���ױ���������:
		
		StringBuffer sb=new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		//�ض���ת��,ת���ױ�
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		
		return NONE;
	}
	
	//����ɹ��󷵻ص�·��
	public String callBack(){
		//���Զ�����״̬
		Order currOrder=orderService.findOrderByOid(Integer.parseInt(r6_Order));
		//�޸Ķ���״̬Ϊ2���Ѿ�����
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("֧���ɹ�!�������Ϊ: "+r6_Order +" ������Ϊ: "+r3_Amt);
		return "msg";
	}

	// ����get,set����
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

}
