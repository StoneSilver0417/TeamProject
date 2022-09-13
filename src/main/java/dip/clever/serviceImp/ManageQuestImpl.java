package dip.clever.serviceImp;

import dip.clever.mapper.ManageQuestMapper;
import dip.clever.model.Quest;
import dip.clever.model.Test;
import dip.clever.model.Round;
import dip.clever.service.MangeQuestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageQuestImpl implements MangeQuestService {
	
	@Autowired
    ManageQuestMapper manageQuestMapper;
	
	// 회차정보입력
	@Override
	public void insertRound(Round testRound) {
		manageQuestMapper.insertRound(testRound);
	}
	
	// 회차정보수정
	@Override
	public void modifyRound(long roundNo, Round testRound) {
		// TODO Auto-generated method stub
		
	}
	// 회차정보삭제
	@Override
	public void deleteRound(long roundNo) {
		// TODO Auto-generated method stub
		
	}

	// 문제정보입력
	@Override
	public void insertQuest(Quest quest) {
		// TODO Auto-generated method stub
		
	}
	// 문제정보수정
	@Override
	public void modifyQuest(long questNo, Quest quest) {
		// TODO Auto-generated method stub
		
	}
	// 문제정보삭제
	@Override
	public void deleteQuest(long questNo) {
		// TODO Auto-generated method stub
		
	}


	

}
