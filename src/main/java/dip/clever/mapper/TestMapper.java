package dip.clever.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dip.clever.model.Category;
import dip.clever.model.Test;

@Mapper
public interface TestMapper{	
	public List<Category> selectCategoryList();
	
	public List<Test> selectTestList(int categoryNo);
	
	public Category selectCategory(int categoryNo);
}