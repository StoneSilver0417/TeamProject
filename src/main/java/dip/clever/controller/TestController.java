package dip.clever.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//시험 목록 반환
	@PostMapping("/list")
	public String testList(Model model, Category category) {
		List<Test> testList = testService.selectTestList(category);		

		model.addAttribute("testList", testList);
		
		return "testList";
	}
	
	//검색 결과 반환
	@PostMapping("/search")
	public ResponseEntity<Map<SearchCondition, List>> searchResult(SearchCondition where, String query){
		Map<SearchCondition, List> resultMap = new HashMap<>();		
		SearchCondition[] searchConditions;
		
		if (where == SearchCondition.ALL) {
			searchConditions = SearchCondition.values();
			for(int a = 1; a < searchConditions.length; a++) {
				resultMap.put(searchConditions[a], testService.getResultList(searchConditions[a], query));
			}			
		}
		else {
			resultMap.put(where, testService.getResultList(where, query));
		}
		
		return new ResponseEntity<Map<SearchCondition,List>> (resultMap, HttpStatus.OK);
	}
	
	public List<Test> testList(Category category) {
		return testService.selectTestList(category);
	}
}