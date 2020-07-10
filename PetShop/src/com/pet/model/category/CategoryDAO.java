package com.pet.model.category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.exception.CategoryDMLException;

@Repository
public class CategoryDAO {
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public void insert(Category category) throws RuntimeException {
		int result = sessionTemplate.insert("Category.insert" , category);
		//result=0;
		if(result != 1) {
			//서비스 영역에서 , 여기서 문제가 발생했음을 알수 있도록
			//조치를 취한다!
			throw new CategoryDMLException("등록실패입니다");
		}
	}
	
	public List selectAll() {
		List list = null;
		list = sessionTemplate.selectList("Category.selectAll");
		return list;
	}
	
	public void delete(int category_id) throws CategoryDMLException{
		int result = sessionTemplate.delete("Category.delete" , category_id);
		
		if(result == 0) {
			throw new CategoryDMLException("카테고리 삭제에 실패하였습니다");
		}
	}

}












