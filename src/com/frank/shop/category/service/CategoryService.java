package com.frank.shop.category.service;

import java.util.List;

import com.frank.shop.category.dao.CategoryDao;
import com.frank.shop.category.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao;
	public List<Category> findAll() {
		List<Category> list=categoryDao.findAll();
		return list;
	}
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	public void save(Category category) {
		categoryDao.save(category);
	}
	public void delete(Category category) {
		categoryDao.delete(category);
		
	}
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}
	public void update(Category category) {
		categoryDao.update(category);
		
	}
	

}
