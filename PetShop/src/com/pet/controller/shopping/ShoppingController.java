package com.pet.controller.shopping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pet.model.product.Cart;

@Controller
public class ShoppingController {
	
	//장바구니 담기!!
	@RequestMapping(value="/shop/cart/regist", method=RequestMethod.POST)
	public String regist(Model model, Cart cart, HttpSession session) {
		//넘겨받은 상품에 대한 파라미터 정보를 VO에 담은후
		//다시 세션에 담자!! 세션에 담았으므로 세션종료까지는 데이터를 유지할수 있다
		System.out.println("부여받은 세션 아이디는" + session.getId());
		
		
		List<Cart> cartList = (List)session.getAttribute("cartList");
		if(cartList==null) {
			//장바구니에 담을 리스트가 최초라면.. new 
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);//키값부여
		}
		int count =0;
		Cart obj = null;
		
		for(int i = 0; i<cartList.size(); i++) {
			obj = cartList.get(i);
			if(obj.getProduct_id() == cart.getProduct_id()) {
				count++;
				obj.setEa(obj.getEa()+1);				
			}
		}
		if(count==0) {		
			cart.setEa(1);
			cartList.add(cart);//장바구니 리스트에 상품추가!!
		}		
		
		model.addAttribute("msg","장바구니에"+cart.getProduct_name()+"가 담겼습니다");
		model.addAttribute("url","/shop/cart/list");
		return "view/message";			
		
	}
	
	//
	@RequestMapping(value="/shop/cart/list", method=RequestMethod.GET)
	public String getList() {		
		return "shop/cart";
	}	
	//장바구니에서 상품 1개 삭제
	@RequestMapping(value="/shop/cart/del", method=RequestMethod.GET)
	public String removeOne(Model model ,@RequestParam int product_id, HttpSession session) {
		//cartList에서 product_id를 조회하여, 해당 객체를 List에서 제거
		
		List<Cart> cartList = (List)session.getAttribute("cartList");
		
		for(int i = 0; i < cartList.size(); i++) {
			Cart cart = cartList.get(i);
			if(product_id == cart.getProduct_id()) {//발견되면
				cartList.remove(cart);//리스트에서 상품 삭제
			}
		}
		model.addAttribute("msg","삭제완료");
		model.addAttribute("url","/shop/cart/list");
		
		return "view/message";
	}
	//장바구니 비우기
	@RequestMapping(value="/shop/cart/remove", method=RequestMethod.GET)
	public String removeOne(Model model, HttpSession session) {
		session.removeAttribute("cartList");
		model.addAttribute("msg","장바구니를 비웠습니다");
		model.addAttribute("url","/shop/cart/list");
		
		return "view/message";
	}
	
	//수정하기
	@RequestMapping(value="/shop/cart/edit", method=RequestMethod.GET)
	public String edit(Model model, Cart cart, HttpSession session) {
		List<Cart> cartList = (List)session.getAttribute("cartList");
		
		//List에 들어있는 Cart 객체 끄집어 내기
		for(int i = 0; i<cartList.size(); i++) {
			Cart obj = cartList.get(i);
			if(obj.getProduct_id() == cart.getProduct_id()) {
				obj.setEa(cart.getEa());//갯수 대체
			}
		}
		model.addAttribute("msg","장바구니가 수정되었습니다");
		model.addAttribute("url","/shop/cart/list");
		
		
		return "view/message";
	}
	
}














