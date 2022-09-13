package dip.clever.controller;

import java.util.List;

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
import dip.clever.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("")
	public String category(Model model, Category category) {
		model.addAttribute("category",category);

		return "category";
	}

	@GetMapping("/{no}")
	public String category(Model model, @PathVariable int no) {
		Category category = new Category();
		
		category.setCategoryNo(no);
		category = categoryService.selectCategory(category);
		
		model.addAttribute("category",category);
		
		return "test";
	}
	
	//카테고리 관리 폼
	@PostMapping("/manage")
	public String manage(Model model) {
		model.addAttribute("categoryList", categoryService.selectCategoryList());
		
		return "categoryForm";
	}
	
	@PostMapping("/table")
	public String categoryTable(Model model) {
		model.addAttribute("categoryList", categoryService.selectCategoryList());
		
		return "categoryTable"; 
	}
	
	@PostMapping("/info")
	public String categoryInfo() {
		return "categoryInfo";
	}
	
	//카테고리 추가
	@PostMapping("/insert")
	public boolean insertCategory(Category category) {
		categoryService.insertCategory(category);
		
		return true;
	} 
	
	//카테고리 목록 반환
	@PostMapping("/list")
	public ResponseEntity<List<Category>> selectCategoryList(){
		List<Category> categoryList = categoryService.selectCategoryList();
		
		return new ResponseEntity<List<Category>> (categoryList, HttpStatus.OK);		
	}
	
	//카테고리 정보
	private Category selectCategory(Category category) {
		return categoryService.selectCategory(category);
	}
}
