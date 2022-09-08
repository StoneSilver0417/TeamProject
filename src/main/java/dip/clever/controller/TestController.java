package dip.clever.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dip.clever.model.Category;
import dip.clever.service.TestService;

@RestController
@RequestMapping("test")
public class TestController {
	@Autowired
	private TestService testService;
	
	@PostMapping("/categoryList")
	public List<Category> selectCategoryList(){
		return testService.selectCategoryList();
	}
}