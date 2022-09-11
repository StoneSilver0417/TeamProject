package dip.clever.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dip.clever.model.Category;
import dip.clever.model.SearchCondition;
import dip.clever.model.Test;

public interface TestService {
	
	public List<Test> selectTestList(Category category);
	
	public List getResultList(SearchCondition searchCondition, String query);
}
