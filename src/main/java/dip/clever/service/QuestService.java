package dip.clever.service;

import java.util.List;

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
}
