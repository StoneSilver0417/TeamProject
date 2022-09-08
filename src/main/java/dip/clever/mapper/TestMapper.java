package dip.clever.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dip.clever.model.Category;

@Mapper
public interface TestMapper{	
	public List<Category> selectCategoryList();
}