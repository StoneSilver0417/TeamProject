package dip.clever.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dip.clever.model.Category;
import dip.clever.model.Test;
import dip.clever.service.TestService;

@Controller
@RequestMapping("test")
public class TestController {
	@Autowired
	private TestService testService;
	
	//카테고리 목록 반환
	@PostMapping("/categoryList")
	public ResponseEntity<List<Category>> selectCategoryList(){
		List<Category> categoryList = testService.selectCategoryList();
		
		return new ResponseEntity<List<Category>> (categoryList, HttpStatus.OK);		
	}
	
	//시험 목록 반환
	@PostMapping("/list")
	public String testList(Model model, Category category) {
		List<Test> testList = testService.selectTestList(category);		
		
		category = selectCategory(category);
		
		model.addAttribute("testList", testList);
		model.addAttribute("category", category);
		
		return "testList";
	}
	
	//카테고리 정보
	private Category selectCategory(Category category) {
		return testService.selectCategory(category);
	}
}