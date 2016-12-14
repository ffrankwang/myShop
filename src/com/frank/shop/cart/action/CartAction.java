package com.frank.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.frank.shop.cart.domain.Cart;
import com.frank.shop.cart.domain.CartItem;
import com.frank.shop.product.domain.Product;
import com.frank.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport {
	private int pid;
	private int count;
	private ProductService productService;

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String addCart() {
		// ����������
		CartItem cartItem = new CartItem();
		// ��������
		cartItem.setCount(count);
		// ͨ��pid��ȡ��Ʒ�����ø�������
		Product product = productService.findById(pid);
		cartItem.setProduct(product);
		// ��ȡ���ﳵ���ѹ�������ӵ����ﳵ
		Cart cart = getCart();
		cart.addCart(cartItem);
		//�����ܽ��
		
		return "addCart";
	}
	
	public String removeCartItem(){
		getCart().removeCart(pid);
		return "removeCartItem";
	}
	public String clearCart(){
		getCart().clearCart();
		return "clearCart";
	}
	public String myCart(){
		return "myCart";
	}

	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
