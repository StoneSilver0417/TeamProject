package dip.clever.mapper.quest;

import dip.clever.dto.RoundInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageQuestMapper {

    void addRound(RoundInfoDto roundInfoDto);

}
