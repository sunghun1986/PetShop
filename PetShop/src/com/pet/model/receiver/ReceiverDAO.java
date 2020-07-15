package com.pet.model.receiver;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.exception.DMLException;

@Repository
public class ReceiverDAO {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public void insert(Receiver receiver) throws DMLException{
		
		int result = sessionTemplate.insert("Receiver.insert", receiver);
		
		
		if(result == 0) {
			throw new DMLException("배송자 정보가 입력되지 않았습니다");
		}
	}
	
}











