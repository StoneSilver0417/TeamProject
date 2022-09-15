package dip.clever.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dip.clever.mapper.QuestMapper;
import dip.clever.mapper.TestMapper;
import dip.clever.model.Choice;
import dip.clever.model.Quest;
import dip.clever.model.Round;
import dip.clever.model.User;
import dip.clever.service.QuestService;

@Service
public class QuestServiceImp implements QuestService{
	@Autowired
	private QuestMapper questMapper;

	@Override
	public List<Quest> selectQuestList(Round round) {
		return questMapper.selectQuestList(round.getRoundNo());		
	}
	
	@Override
	public List<Quest> selectSolvedList(User user) {
		return questMapper.selectSolvedList(user.getUserId());
	}

	@Override
	public List<Quest> selectUploadList(User user) {
		return questMapper.selectUploadList(user.getUserId());
	}
	
	@Override
	public Quest selectQuest(Quest quest) {
		return questMapper.selectQuest(quest.getQuestNo());
	}

	@Override
	public Choice selectChoice(Quest quest) {
		return questMapper.selectChoice(quest.getQuestNo());
	}
}