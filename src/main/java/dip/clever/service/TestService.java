package dip.clever.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dip.clever.model.Category;

@Service
public interface TestService {
	public List<Category> selectCategoryList();
}
