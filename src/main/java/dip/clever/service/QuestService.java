package dip.clever.service;

import java.util.List;
import java.util.Map;

import dip.clever.model.Choice;
import dip.clever.model.Quest;
import dip.clever.model.Round;
import dip.clever.model.User;

public interface QuestService {
	public List<Quest> selectQuestList(Round round);	

	public List<Quest> selectSolvedList (User user);
	
	public List<Quest> selectUploadList(User user);
	
	public Quest selectQuest(Quest quest);
	
	public Choice selectChoice(Quest quest);
	
	public Map<String, Object> selectQuestInfo(Quest quest);
	
	public Integer selectNextQuest(Quest quest);
	
	public Integer[] checkAnswer(List<Map<String, Object>> answerList);
}
