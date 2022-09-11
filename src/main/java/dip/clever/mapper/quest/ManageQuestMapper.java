package dip.clever.mapper.quest;

import dip.clever.dto.RoundInfoDto;
import dip.clever.model.TestRound;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageQuestMapper {

    void addRound(TestRound testRound);

}
