package com.frank.shop.cart.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//���ﳵ����
	//������ϣ�Map��key������Ʒpid,value:������
	private Map<Integer,CartItem> map=new LinkedHashMap<Integer, CartItem>();
	//Cart��������һ����cartItems���ԡ�
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	//�����ܼ�
	private double total;
	public double getTotal() {
		return total;
	}
	//���ﳵ�Ĺ��ܣ�
	//1.����������ӵ����ﳵ
	public void addCart(CartItem cartItem){
		/**
		 * �жϹ��ﳵ���Ƿ��Ѿ����ڸù����
		 * ������ڣ��ܼ�=�ܼ�+������С��
		 * ���������
		 * ��map����ӹ�����
		 * �ܼ�=�ܼ�+������С��
		 * 
		 */
		//�����Ʒid
		Integer pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//����
			CartItem  _cartItem=map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			//������
			map.put(pid, cartItem);
		}
		//�����ܼƵ�ֵ
		total+=cartItem.getSubtotal();
	}
	
	//2.�ӹ��ﳵ�Ƴ�������
	public void removeCart(Integer pid){
		if(map.containsKey(pid)){
			CartItem cartItem=map.remove(pid);
			//�ܼ�=�ܼ�-�Ƴ��Ĺ�����С��
			total-=cartItem.getSubtotal();
		}
	}
	//3.��չ��ﳵ
	public void clearCart(){
		//�����й��������
		map.clear();
		//���ܼ���Ϊ0
		total=0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
