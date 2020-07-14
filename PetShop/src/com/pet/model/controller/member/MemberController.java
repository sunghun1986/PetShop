package com.pet.model.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pet.exception.DMLException;
import com.pet.model.member.Member;
import com.pet.model.member.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/member/regist" , method=RequestMethod.POST)
	public String regist(Model model ,Member member) {
			memberService.insert(member);
			model.addAttribute("msg","회원가입축하");
			model.addAttribute("url","/");
			
		return "view/message";
	}
	
	@ExceptionHandler(DMLException.class)
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
