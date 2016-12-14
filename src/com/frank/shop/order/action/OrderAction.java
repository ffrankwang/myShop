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
	private Integer page;// 获取页数
	private String pd_FrpId;// 获取支付通道编码
	// 接收付款成功后易宝返回的信息
	private String r3_Amt;// 付款金额
	private String r6_Order;// 商户订单号

	public String saveOrder() {
		// 获取购物车信息
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");

		order.setTotal(cart.getTotal());
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("亲!您还没有购物!");
			return "login";
		}
		order.setUser(existUser);
		// 设置订单项
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

	// 根据用户uid查询订单
	public String findOrdersByUid() {
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findOrdersByUid(
				existUser.getUid(), page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findOrdersByUid";
	}

	// 根据订单编号查询订单
	public String findOrderByOid() {
		order = orderService.findOrderByOid(order.getOid());
		// Order.hbm.xml文件中的many-to-one对对应的user懒加载没有设置成false，所以导致order.getUser属性为空，
		// 页跳转后<s:property value="model.user.name"/>显示不了数据。
		return "findOrderByOid";
	}

	/* 为订单付款 */
	public String payOrder() throws IOException {
		// 1.修改数据：
		System.out.println(order.getOid());
		Order currOrder = orderService.findOrderByOid(order.getOid());
		currOrder.setName(order.getName());
		currOrder.setAddr(order.getAddr());
		currOrder.setPhone(order.getPhone());
		// 修改订单
		orderService.update(currOrder);
		// 2.完成付款
		// 付款需要的参数
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/myShop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		
		
		
		// 向易宝发送请求:
		
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
		//重定向转发,转向易宝
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		
		return NONE;
	}
	
	//付款成功后返回的路径
	public String callBack(){
		//个性订单的状态
		Order currOrder=orderService.findOrderByOid(Integer.parseInt(r6_Order));
		//修改订单状态为2，已经付款
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
	}

	// 各种get,set方法
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
