package dip.clever.mapper;

import org.apache.ibatis.annotations.Mapper;

import dip.clever.model.Choice;
import dip.clever.model.Quest;
import dip.clever.model.Round;

@Mapper
public interface ManageQuestMapper {
	
    // 문제등록
    public void insertQuest(Quest quest);
    
    // 선지등록
    public void insertChoice(Choice choice);
    
    // 회차선택
    public Round selectRound(Round Round);
    
    // 문제수정
    public void modifyQuest(long questNo, Quest quest);
    
    // 문제삭제
    public void deleteQuest(long questNo);

}
