package dip.clever.serviceImp.quest;

import dip.clever.dto.QuestInfoDto;
import dip.clever.dto.RoundInfoDto;
import dip.clever.dto.TestInfoDto;
import dip.clever.mapper.quest.ManageQuestMapper;
import dip.clever.model.TestRound;
import dip.clever.service.quest.MangeQuest;
import org.springframework.stereotype.Service;

@Service
public class ManageQuestImpl implements MangeQuest {

    ManageQuestMapper manageQuestMapper;

    @Override
    public void addRound(RoundInfoDto roundInfoDto) {
        TestRound testRound = roundInfoDto.convertTestRound();
        manageQuestMapper.addRound(testRound);
    }

    @Override
    public void modifyRound(long roundNo, RoundInfoDto roundInfoDto) {

    }

    @Override
    public void deleteRound(long roundNo) {

    }

    @Override
    public void addTest(TestInfoDto testInfoDto) {

    }

    @Override
    public void modifyTest(long testNo, TestInfoDto testInfoDto) {

    }

    @Override
    public void deleteTest(long testNo) {

    }

    @Override
    public void addQuest(QuestInfoDto questInfoDto) {

    }

    @Override
    public void modifyQuest(long questNo, QuestInfoDto questInfoDto) {

    }

    @Override
    public void deleteQuest(long questNo) {

    }
}
