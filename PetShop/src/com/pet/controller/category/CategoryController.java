package com.pet.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.domain.Category;
import com.pet.exception.DMLException;
import com.pet.model.category.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/category/regist", method = RequestMethod.POST)
	@ResponseBody // jsp로 결과를 보여주는 것이 아니라.
	// 데이터만 전송할 경우에 사용됨...(비동기랑 알맞는 어노테이션)
	public String regist(Category category) {
		System.out.println("파라미터값: " + category.getCategory_name());
		categoryService.regist(category);
		return "1";
	}

	@RequestMapping(value = "/category/list", 
			method = RequestMethod.GET,produces = "text/html;charset=utf8")
	@ResponseBody
	public String selectAll() {
		List<Category> categoryList = categoryService.selectAll();

		StringBuilder sb = new StringBuilder();

		sb.append("{");
		sb.append("\"categoryList\":[");
		for (int i = 0; i < categoryList.size(); i++) {
			Category category = categoryList.get(i);
			sb.append("{");
			sb.append("\"category_id\":\"" + category.getCategory_id() + "\",");
			sb.append("\"category_name\":\"" + category.getCategory_name() + "\",");
			sb.append("\"rank\":\"" + category.getRank() + "\"");
			if (i < categoryList.size() - 1) {
				sb.append("},");
			} else {
				sb.append("}");
			}
		}
		sb.append("]");
		sb.append("}");

		return sb.toString();
	}

	@RequestMapping(value = "/category/del", method = RequestMethod.GET)
	@ResponseBody
	public String delete(@RequestParam("category_id") int category_id) {
		categoryService.delete(category_id);
		return "1";
	}

	// 컨트롤러가 보유한 메서드들에게서 예외가 발생할 경우,
	// 아래의 어노테이션이 붙은 메서드가 에러를 전달받아 처리하게 된다
	@ExceptionHandler(DMLException.class)
	@ResponseBody
	public String handle(DMLException e) {
		System.out.println("에러발견!!");
		return "0";
	}

}
