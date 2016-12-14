package com.frank.shop.cart.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//购物车属性
	//购物项集合：Map的key就是商品pid,value:购物项
	private Map<Integer,CartItem> map=new LinkedHashMap<Integer, CartItem>();
	//Cart对象中有一个叫cartItems属性。
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	//购物总计
	private double total;
	public double getTotal() {
		return total;
	}
	//购物车的功能：
	//1.将购物项添加到购物车
	public void addCart(CartItem cartItem){
		/**
		 * 判断购物车中是否已经存在该购物项：
		 * 如果存在，总计=总计+购物项小计
		 * 如果不存在
		 * 向map中添加购物项
		 * 总计=总计+购物项小计
		 * 
		 */
		//获得商品id
		Integer pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//存在
			CartItem  _cartItem=map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			//不存在
			map.put(pid, cartItem);
		}
		//设置总计的值
		total+=cartItem.getSubtotal();
	}
	
	//2.从购物车移除购物项
	public void removeCart(Integer pid){
		if(map.containsKey(pid)){
			CartItem cartItem=map.remove(pid);
			//总计=总计-移除的购物项小计
			total-=cartItem.getSubtotal();
		}
	}
	//3.清空购物车
	public void clearCart(){
		//将所有购物项清空
		map.clear();
		//将总计置为0
		total=0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
