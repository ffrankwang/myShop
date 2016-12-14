package com.frank.shop.product.adminAction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.frank.shop.categorysecond.domain.CategorySecond;
import com.frank.shop.categorysecond.service.CategorySecondService;
import com.frank.shop.product.domain.Product;
import com.frank.shop.product.service.ProductService;
import com.frank.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
	private Product product=new Product();
	private ProductService productService;
	private CategorySecondService categorySecondService;
	private Integer page;
	//����ͼƬ�ϴ��Ĳ���
	private File upload;//��Ҫ������ϴ����ļ�name������ͬ
	private String uploadFileName;
	private String uploadContentType;
	public String findAll(){
		PageBean<Product> pageBean=productService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	public String add(){
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	public String save() throws IOException{
		product.setPdate(new Date());
		if(upload!=null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload,diskFile);
			product.setImage("products/"+uploadFileName);
		}
		
		productService.save(product);
		return "saveSuccess";
	}
	public String edit(){
		//��ʱ�����ǲ�ѯ�����Ա����ݻ���ʧ��
		product=productService.findById(product.getPid());
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editPage";
	}
	public String update() throws IOException{
		//����ʱ�׳��ִ����ڱ���ʾ�������ر�ǩ�������ݣ�product��idֵ��image���Ե�ֵȱʧ�����¸���ʧ��
		product.setPdate(new Date());
		if(upload!=null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload,diskFile);
			product.setImage("products/"+uploadFileName);
		}		
		productService.update(product);
		return "updateSuccess";
	}
	
	public String delete(){
		product=productService.findById(product.getPid());
		String  path=ServletActionContext.getServletContext().getRealPath(product.getImage());
		File file=new File(path);
		file.delete();
		productService.delete(product);
		return "deleteSuccess";
	}
	public Product getModel() {
		return product;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	
}
