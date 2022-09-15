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
import org.springframework.web.bind.annotation.RequestParam;

import dip.clever.model.Category;
import dip.clever.model.Round;
import dip.clever.model.SearchCondition;
import dip.clever.model.Test;
import dip.clever.service.TestService;
import dip.clever.util.Json;
import dip.clever.util.Util;

@Controller
@RequestMapping("test")
public class TestController {
	@Autowired
	private TestService testService;
	
	@PostMapping("")
	public String test(Model model, @RequestParam HashMap<String, String> param){
		model.addAttribute("testList", Json.parse(param.get("param")));

		return "testList";
	}
	
	//시험 목록 반환
	@PostMapping("/list")
	public ResponseEntity<List<Test>> testList(Category category) {
		List<Test> testList = testService.selectTestList(category);		
		
		return new ResponseEntity<List<Test>> (testList, HttpStatus.OK);
	}
	
	@PostMapping("/selectByRound")
	public ResponseEntity<Test> selectByRound(Round round){
		return Util.resoponse(testService.selectTest(round));
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