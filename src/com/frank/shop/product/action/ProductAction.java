package com.frank.shop.product.action;

import com.frank.shop.categorysecond.domain.CategorySecond;
import com.frank.shop.categorysecond.service.CategorySecondService;
import com.frank.shop.product.domain.Product;
import com.frank.shop.product.service.ProductService;
import com.frank.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements  ModelDriven<Product>{
	private ProductService productService;
	private CategorySecondService categorySecondService;
	private Product product=new Product();
	
	private Integer cid;//接收一级分类cid
	private Integer page;//接收当前页数
	private Integer csid;//接收二级分类csid

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getCsid() {
		return csid;
	}
	

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getModel() {
		return product;
	}
	
	

	public String findByPid(){
		product=productService.findById(product.getPid());
		return "findByPid";
	}
	public String findByCid(){
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	public String findByCsid(){
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
