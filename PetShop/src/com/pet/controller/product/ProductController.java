package com.pet.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pet.controller.common.Pager;
import com.pet.domain.Category;
import com.pet.domain.Event;
import com.pet.domain.Product;
import com.pet.exception.DMLException;
import com.pet.exception.FileException;
import com.pet.model.common.file.FileManager;
import com.pet.model.product.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private Pager pager;

	@RequestMapping(value = "/admin/product/regist", method = RequestMethod.POST)
	public ModelAndView regist(Product product, HttpServletRequest request) {
		// 파일 업로드 처리~!

		System.out.println("카테고리 id " + product.getCategory().getCategory_id());
		System.out.println("상품명은 " + product.getProduct_name());
		System.out.println("가격은 " + product.getPrice());
		System.out.println("브랜드는 " + product.getBrand());

		// Product VO 가 보유한 MultipartFile 안에 업로드된 파일 정보가
		// 들어있다.. 따라서 메모리상에서 존재하므로, 원하는 경로에 저장하자!!
		// MultipartFile myFile = product.getMyFile();
		// String realPath = request.getServletContext().getRealPath("/data/");

		// FileManager.saveFile(myFile, realPath);

		productService.regist(product, product.getMyFile(), request.getServletContext().getRealPath("/data/"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", "/admin/product/list");
		mav.addObject("msg", "파일 업로드 성공");
		mav.setViewName("view/message");

		return mav;
	}

	@RequestMapping(value = "/admin/product/list", method = RequestMethod.GET)
	public ModelAndView selectAll(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List productList = productService.selectAll();
		List eventList = productService.getEventList();
		// 페이징 처리 객체
		pager.init(productList, request);
		mav.addObject("productList", productList);
		mav.addObject("eventList", eventList);
		mav.addObject("pager", pager);
		
		mav.setViewName("admin/product/index");
		return mav;
	}

	// 상세보기 요청
	@RequestMapping(value = "/admin/product/detail", method = RequestMethod.GET)
	public String select(Model model, @RequestParam int product_id) {
		System.out.println("product_id" + product_id);
		Product product = productService.select(product_id);
		model.addAttribute("product", product);
		return "admin/product/detail";
	}

	// 삭제요청
	@RequestMapping(value = "/admin/product/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(HttpServletRequest request,Model model, @RequestParam int product_id,@RequestParam String filename) {
		// 파일도 삭제!!
		String realPath = request.getServletContext().getRealPath("/data/"+filename);
		
		FileManager.removeFile(realPath);			
		
		productService.delete(product_id);
		
		model.addAttribute("url", "/admin/product/list");
		model.addAttribute("msg", "삭제 성공");

		return "view/message";
	}

	// 수정요청처리
	@RequestMapping(value = "/admin/product/edit", method = RequestMethod.POST)
	public String update(Model model,Product product, HttpServletRequest request) {
		productService.update(product, request.getServletContext().getRealPath("/data/"));
		model.addAttribute("url", "/admin/product/detail?product_id="+product.getProduct_id());
		model.addAttribute("msg", "수정 성공");
		return "view/message";
	}	
	
	//사용자 요청처리, 메인 상세보기
	@RequestMapping(value = "/shop/detail", method = RequestMethod.GET)
	public String getDetail(Model model, @RequestParam int product_id) {		
		Product product = productService.select(product_id);
		model.addAttribute("product", product);
		return "shop/detail";
	}
	
	/*기획상품 관련*/
	@RequestMapping(value = "/event/list", method = RequestMethod.GET)
	public String getEventList() {
		
		return "event/list";
	}
	
	//기획상품 관리
	@RequestMapping(value = "/admin/event/list", method = RequestMethod.GET)
	public String getEventListAdmin() {
		
		return "admin/event/list";
	}
	//기획상품 등록
	@RequestMapping(value = "/admin/event/regist", method = RequestMethod.POST)
	@ResponseBody
	public String registEvent(Event event) {
		String result = null;
		try {
			productService.registEvent(event);
			result="ok";
		} catch (DMLException e) {
			e.printStackTrace();
			result="fail";
		}		
		return result;
	}
	
	@RequestMapping(value = "/admin/event/selectall", 
			method = RequestMethod.GET,produces = "text/html;charset=utf8")
	@ResponseBody
	public String selectAll() {
		List<Event> eventList = productService.getEventList();

		StringBuilder sb = new StringBuilder();

		sb.append("{");
		sb.append("\"eventList\":[");
		for (int i = 0; i < eventList.size(); i++) {
			Event event = eventList.get(i);
			sb.append("{");
			sb.append("\"event_id\":\"" + event.getEvent_id() + "\",");
			sb.append("\"title\":\"" + event.getTitle() + "\"");			
			if (i < eventList.size() - 1) {
				sb.append("},");
			} else {
				sb.append("}");
			}
		}
		sb.append("]");
		sb.append("}");

		return sb.toString();
	}
	
	//기존 상품을 기획상품으로 등록요청 처리
	@RequestMapping(value = "/admin/eventproduct/regist", method = RequestMethod.POST)
	public String registEventProduct(@RequestParam int event_id,@RequestParam int[] ch) {
		System.out.println("선택한 이벤트의 유형은 " + event_id);
		for(int i = 0; i < ch.length; i++) {
			System.out.println("등록될 상품코드는 " + ch[i]);
		}
		return null;
	}

	@ExceptionHandler({ FileException.class, DMLException.class })
	public ModelAndView handle(FileException e, DMLException e2) {
		ModelAndView mav = new ModelAndView();

		// 파일 업로드 에러인경우
		if (e != null) {
			mav.addObject("e", e);// 에러객체 자체를 담는다!!
			mav.addObject("msg", e.getMessage());
		} else if (e2 != null) {
			mav.addObject("e", e2);
			mav.addObject("msg", e2.getMessage());
		}

		// 입력 에러인경우
		mav.setViewName("view/error");
		return mav;
	}

}
