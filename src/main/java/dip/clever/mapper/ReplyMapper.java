package dip.clever.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dip.clever.model.Reply;


@Mapper
public interface ReplyMapper {
	
	//전체 데이터 가져오기
	public List<Reply> findAll();

}
