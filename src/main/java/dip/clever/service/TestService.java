package dip.clever.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dip.clever.model.Category;
import dip.clever.model.Test;

@Service
public interface TestService {
	public List<Category> selectCategoryList();
	
	public List<Test> selectTestList(Category category);
	
	public Category selectCategory(Category category);
}
