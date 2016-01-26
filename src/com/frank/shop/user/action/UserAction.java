package com.frank.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.frank.shop.user.domain.User;
import com.frank.shop.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author mstig �û�ģ���Action
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	// ģ������ʹ�õĶ���
	private User user = new User();

	public User getModel() {
		return user;
	}

	/**
	 * ��ת��ע��ҳ���ִ�з���
	 */
	public String registPage() {
		return "registPage";
	}

	// ע��UserService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * AJAX�����첽У���û�����ִ�з���
	 * 
	 * @throws IOException
	 */
	public String findByUsername() throws IOException {
		// ����Service���в�ѯ:
		User existUser = userService.findByUsername(user.getUsername());
		// ���response����,��ҳ�����:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// �ж�
		if (existUser != null) {
			// ��ѯ�����û�:�û����Ѿ�����
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		} else {
			// û��ѯ�����û�:�û�������ʹ��
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}
	/**
	 * �û�ע�᷽��
	 */
	public String regist(){
		save();
		this.addActionMessage("ע��ɹ�����ȥ���伤�");
		return "msg";
	}
	
	public String save(){
		userService.save(user);
		return NONE;
	}
	/**�û�����ķ���
	 * 
	 */
	/*public String active(){
		User existUser=userService.findByCode(user.getCode());
		if(existUser==null){
			this.addActionMessage("����ʧ�ܣ����������");
		}
	}*/

}