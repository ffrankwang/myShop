package com.frank.shop.cart.domain;

import com.frank.shop.product.domain.Product;

public class CartItem {
	// һ�������������Ʒ��������С��
	private Product product;
	private int count;
	private double subtotal;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSubtotal() {
		return count * product.getShop_price();
	}
	/*
	 * public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
	 */

}
