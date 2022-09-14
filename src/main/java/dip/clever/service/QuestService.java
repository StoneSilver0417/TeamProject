package dip.clever.service;

import java.util.List;

import dip.clever.model.Quest;
import dip.clever.model.Round;
import dip.clever.model.Test;

public interface QuestService {	
	public List<Quest> selectQuestList(Round round);
	
	public Quest selectQuest(Quest quest);
}
