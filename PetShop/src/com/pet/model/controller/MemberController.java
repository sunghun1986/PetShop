package com.pet.model.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pet.exception.DMLException;
import com.pet.exception.DataNotFoundException;
import com.pet.model.member.Member;
import com.pet.model.member.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/member/login" , method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Member loginCheck(Member member, HttpSession session) {
		System.out.println(member.getId());
		System.out.println(member.getPassword());
		
		Member obj = memberService.loginCheck(member);
		//로그인 성공시 세션에 멤버정보를 담자!!
		session.setAttribute("member", obj);
		
		//VO를 자동으로 json 형식으로 변환해주는 라이브러리가 있음..		
		return obj;
	}
	
	
	@RequestMapping(value="/member/regist" , method=RequestMethod.POST)
	public String regist(Model model ,Member member) {
		memberService.insert(member);
		model.addAttribute("msg","회원가입축하");
		model.addAttribute("url","/");
		
		return "view/message";
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseBody
	public String handle(DataNotFoundException e) {	
		System.out.println("로그인 실패 메서드 수행");
		
		StringBuilder msg = new StringBuilder();
		msg.append("{");
		msg.append("\"code\":0,");
		msg.append("\"msg\":\""+e.getMessage()+"\"");
		msg.append("}");
		return msg.toString();
	}
	
	@ExceptionHandler({DMLException.class})
	public ModelAndView handle(DMLException e) {
		ModelAndView mav = new ModelAndView();

		// 파일 업로드 에러인경우
		if (e != null) {
			mav.addObject("e", e);// 에러객체 자체를 담는다!!
			mav.addObject("msg", e.getMessage());
		}

		// 입력 에러인경우
		mav.setViewName("view/error");
		return mav;
	}

}
