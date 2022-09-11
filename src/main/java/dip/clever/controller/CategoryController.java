package dip.clever.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonValue;

import dip.clever.model.Category;
import dip.clever.model.Test;
import dip.clever.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("")
	public String category(Model model, Category category) {
		model.addAttribute("category",category);
		
		System.out.println(category);
		return "category";
	}
	
	//카테고리 목록 반환
	@PostMapping("/list")
	public ResponseEntity<List<Category>> selectCategoryList(){
		List<Category> categoryList = categoryService.selectCategoryList();
		
		return new ResponseEntity<List<Category>> (categoryList, HttpStatus.OK);		
	}
	
	//카테고리 정보 반환
	@PostMapping("/info")
	public String testList(Model model, Category category) {
		model.addAttribute("category", categoryService.selectCategory(category));
		model.addAttribute("testList", new TestController().testList(category));
		
		return "testList";
	}
	
	//카테고리 관리 폼
	@PostMapping("/manage")
	public String manage(Model model) {
		model.addAttribute("categoryList", categoryService.selectCategoryList());
		
		return "categoryForm";
	}
	
	//카테고리 추가
	@PostMapping("/insert")
	public ResponseEntity<String> insertCategory() {
		System.out.println("123");
		//System.out.println(category);
		//categoryService.insertCategory(category);
		
		return new ResponseEntity<String> ("", HttpStatus.OK);
	}
	
	//카테고리 정보
	private Category selectCategory(Category category) {
		return categoryService.selectCategory(category);
	}
}
