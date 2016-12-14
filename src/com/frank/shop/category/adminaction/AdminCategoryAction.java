package com.frank.shop.category.adminaction;

import java.util.List;

import com.frank.shop.category.domain.Category;
import com.frank.shop.category.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	private Category category=new Category();
	private CategoryService categoryService;
	public String findAll(){
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	public String add(){
		
		return "addPage";
	}
	
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	public String delete(){
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	public String edit(){
		category=categoryService.findByCid(category.getCid());
		return "editPage";
	}
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	public Category getModel() {
		return category;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
