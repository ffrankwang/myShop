package com.frank.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.frank.shop.adminuser.domain.AdminUser;
import com.frank.shop.adminuser.service.AdminUserService;
import com.frank.shop.category.domain.Category;
import com.frank.shop.category.service.CategoryService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {
	private AdminUser adminUser = new AdminUser();
	private AdminUserService adminUserService;
	public String login() {
		AdminUser existAdminUser=adminUserService.login(adminUser);
		
		if(existAdminUser!=null){
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}else{
			this.addActionError("用户名或密码错误！");
			return "loginFail";
		}
	}

	public AdminUser getModel() {
		return adminUser;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	

}
