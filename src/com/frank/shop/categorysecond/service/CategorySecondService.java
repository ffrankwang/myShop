package com.frank.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.frank.shop.categorysecond.dao.CategorySecondDao;
import com.frank.shop.categorysecond.domain.CategorySecond;
import com.frank.shop.utils.PageBean;
@Transactional
public class CategorySecondService {
	CategorySecondDao categorySecondDao;
	
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public PageBean<CategorySecond> findAllByPage(Integer page) {
		PageBean<CategorySecond> pageBean=new PageBean<CategorySecond>();
		//每页显示记录数
		int limit=10;
		//总记录数
		int totalCount;
		totalCount=categorySecondDao.findTotalCount();
		//总页数
		int totalPage=totalCount/limit;
		if(totalCount%limit!=0){
			totalPage=totalPage+1;
		}
		Integer begin=limit*(page-1);
		//查询到的结果
		List<CategorySecond> list=categorySecondDao.findByPage(begin,limit);
		pageBean.setLimit(limit);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setPage(page);
		pageBean.setList(list);		
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
		
	}

	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
		
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
		
	}

	public CategorySecond findByCsid(Integer csid) {
		
		return categorySecondDao.findByCsid(csid);
	}

	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return categorySecondDao.findAll();
	}

}
