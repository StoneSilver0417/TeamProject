package dip.clever.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dip.clever.mapper.TestMapper;
import dip.clever.model.Category;
import dip.clever.model.Test;
import dip.clever.service.TestService;

@Service
public class TestServiceImp implements TestService{
	@Autowired
	private TestMapper testMapper;
	
	@Override
	public List<Category> selectCategoryList() {
		return testMapper.selectCategoryList();		
	}

	@Override
	public List<Test> selectTestList(Category category) {
		return testMapper.selectTestList(category.getCategoryNo());		
	}

	@Override
	public Category selectCategory(Category category) {
		return testMapper.selectCategory(category.getCategoryNo());
	}
}