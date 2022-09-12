package dip.clever.mapper;

import dip.clever.model.Quest;
import dip.clever.model.Test;
import dip.clever.model.TestRound;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageQuestMapper {
	
	// 회차정보등록
    public void insertRound(TestRound testRound);
    
    // 회차정보수정
    public void modifyRound(long roundNo, TestRound testRound);
    
    // 회차정보삭제
    public void deleteRound(long roundNo);
    
    // 문제등록
    public void insertQuest(Quest quest);
    
    // 문제수정
    public void modifyQuest(long questNo, Quest quest);
    
    // 문제삭제
    public void deleteQuest(long questNo);

}
