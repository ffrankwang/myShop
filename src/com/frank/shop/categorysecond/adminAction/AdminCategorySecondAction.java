package com.frank.shop.categorysecond.adminAction;

import java.util.List;

import com.frank.shop.category.domain.Category;
import com.frank.shop.category.service.CategoryService;
import com.frank.shop.categorysecond.domain.CategorySecond;
import com.frank.shop.categorysecond.service.CategorySecondService;
import com.frank.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	CategorySecond categorySecond=new CategorySecond();
	private CategorySecondService categorySecondService;
	private Integer page;
	private CategoryService categoryService;
	public String findAll(){
		PageBean<CategorySecond> pageBean=categorySecondService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	public String add(){
		
		return "addPage";
	}
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	public String edit(){
		this.categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		// 查询所有一级分类:
				List<Category> cList = categoryService.findAll();
				// 将集合存入到值栈中.
				ActionContext.getContext().getValueStack().set("cList", cList);
				// 页面跳转:
		return "editPage";
	}
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
	public String delete(){
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	
	
	
	
	
	
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
}
