package dip.clever.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dip.clever.model.Category;
import dip.clever.model.SearchCondition;
import dip.clever.model.Test;
import dip.clever.service.TestService;

@Controller
@RequestMapping("test")
public class TestController {
	@Autowired
	private TestService testService;
	
	@PostMapping("")
	public String categoryInfo(Model model, Category category){
		model.addAttribute("category",category);
		model.addAttribute("testList", testService.selectTestList(category));
		
		return "categoryInfo";
	}
	
	//시험 목록 반환
	@PostMapping("/list")
	public String testList(Model model, Category category) {
		List<Test> testList = testService.selectTestList(category);		

		model.addAttribute("testList", testList);
		
		return "testList";
	}
	
	@GetMapping("/{no}")
	public String test(Model model, @PathVariable int no) {
		Test test = new Test();
		
		test.setTestNo(no);
		test = testService.selectTest(test);
		
		model.addAttribute("test", test);
		
		return "test";
	}
}