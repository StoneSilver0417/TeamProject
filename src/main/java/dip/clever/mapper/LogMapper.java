package dip.clever.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import dip.clever.model.Log;
import dip.clever.model.Quest;

@Mapper
public interface LogMapper {	
	public List<Quest> selectSolvedQuestList(String userId);
	
	public List<Quest> selectUploadQuestList(Log log);
}
