package com.pet.model.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.exception.CategoryDMLException;

@Service
public class CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;
	
	public void regist(Category category) throws RuntimeException {
		categoryDAO.insert(category);
	}
	
	public List selectAll() {
		return categoryDAO.selectAll();
	}
	
	public void delete(int category_id) throws CategoryDMLException{
		categoryDAO.delete(category_id);
	}
	

}







