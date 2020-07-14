package com.pet.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.exception.DMLException;
import com.pet.model.common.file.FileManager;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private FileManager fileManager;
	
	public void insert(Member member) throws DMLException{
		memberDAO.insert(member);
	}

}
