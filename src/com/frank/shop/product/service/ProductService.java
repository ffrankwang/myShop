package com.frank.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.frank.shop.product.dao.ProductDao;
import com.frank.shop.product.domain.Product;
import com.frank.shop.utils.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	// 通过商品id查找商品
	public Product findById(Integer pid) {
		return productDao.findById(pid);

	}

	public PageBean<Product> findByPageCid(Integer cid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		if(page==null)page=1;
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 8;
		pageBean.setLimit(limit);
		//  设置总记录数:
		int totalCount = 0 ;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPageCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		if(page==null)page=1;
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 8;
		pageBean.setLimit(limit);
		//  设置总记录数:
		int totalCount = 0 ;
		totalCount = productDao.findCountCid(csid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findAll(Integer page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//每页显示条数
		int limit=10;
		//总记录数
		int totalCount=productDao.findCount();
		//总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit+1;
		}
	//查询结果集合
		int begin=limit*(page-1);
		List<Product> list=productDao.findAll(begin,limit);
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		productDao.save(product);
	}

	public void delete(Product product) {
		productDao.delete(product);
		
	}

	public void update(Product product) {
	productDao.update(product);
		
	}

}
