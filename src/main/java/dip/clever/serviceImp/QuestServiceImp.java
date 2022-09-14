package dip.clever.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dip.clever.mapper.QuestMapper;
import dip.clever.mapper.TestMapper;
import dip.clever.model.Category;
import dip.clever.model.Quest;
import dip.clever.model.Round;
import dip.clever.model.SearchCondition;
import dip.clever.model.Test;
import dip.clever.service.QuestService;
import dip.clever.service.TestService;

@Service
public class QuestServiceImp implements QuestService{
	@Autowired
	private QuestMapper questMapper;

	@Override
	public List<Quest> selectQuestList(Round round) {
		return questMapper.selectQuestList(round.getRoundNo());		
	}

	@Override
	public Quest selectQuest(Quest quest) {
		return questMapper.selectQuest(quest.getQuestNo());
	}
}