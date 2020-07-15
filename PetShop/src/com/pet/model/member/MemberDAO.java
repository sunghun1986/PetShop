package com.pet.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.exception.DMLException;
import com.pet.exception.DataNotFoundException;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public void insert(Member member) throws DMLException{
		int result = sessionTemplate.insert("Member.insert",member);
		if(result == 0) {
			throw new DMLException("회원가입이 되지 않았습니다");
		}
	}
	public Member loginCheck(Member member) throws DataNotFoundException{
		Member obj = sessionTemplate.selectOne("Member.loginCheck",member);
		if(obj == null) {//회원이 없을 경우, 비지니스적 에러상황으로 보자!!
			throw new DataNotFoundException("로그인 정보가 올바르지 않습니다.");
		}		
		return obj;
	}
}
