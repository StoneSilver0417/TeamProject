package dip.clever.serviceImp;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dip.clever.mapper.TestMapper;
import dip.clever.model.Category;
import dip.clever.service.TestService;

@Service
public class TestServiceImp implements TestService{
	@Autowired
	private TestMapper testMapper;
	
	@Override
	public List<Category> selectCategoryList() {
		return testMapper.selectCategoryList();		
	}	
}