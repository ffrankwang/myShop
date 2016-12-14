package com.frank.shop.index.action;

import java.util.List;

import com.frank.shop.category.domain.Category;
import com.frank.shop.category.service.CategoryService;
import com.frank.shop.product.domain.Product;
import com.frank.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	private CategoryService categoryService;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String execute() throws Exception {
		List<Category> cList =	categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		//首页热闹商品展示
		List<Product> hList=productService.findHot();
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//首页最新商品展示
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}

}
