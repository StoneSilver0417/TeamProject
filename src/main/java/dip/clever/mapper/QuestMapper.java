package dip.clever.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import dip.clever.model.Quest;

@Mapper
public interface QuestMapper {	
	public List<Quest> selectQuestList(int roundNo);
	
	public Quest selectQuest(int questNo);
}
