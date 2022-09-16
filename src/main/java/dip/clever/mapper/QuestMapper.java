package dip.clever.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import dip.clever.model.Choice;
import dip.clever.model.Quest;

@Mapper
public interface QuestMapper {	
	public List<Quest> selectQuestList(int roundNo);
	
	public List<Quest> selectSolvedList(String userId);
	
	public List<Quest> selectUploadList(String userId);
	
	public Quest selectQuest(int questNo);
	
	public Choice selectChoice(int questNo);
	
	public Map<String, Object> selectQuestInfo(int questNo);
	
	public Integer selectNextQuest(int questNo);
	
	public Integer[] checkAnswer(List<Map<String, Object>> answerList);
}
