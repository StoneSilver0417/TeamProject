package dip.clever.service.quest;

import dip.clever.dto.QuestInfoDto;
import dip.clever.dto.RoundInfoDto;
import dip.clever.dto.TestInfoDto;

public interface MangeQuest {
    void addRound(RoundInfoDto roundInfoDto);
    void modifyRound(long roundNo, RoundInfoDto roundInfoDto);
    void deleteRound(long roundNo);
    void addTest(TestInfoDto testInfoDto);
    void modifyTest(long testNo, TestInfoDto testInfoDto);
    void deleteTest(long testNo);
    void addQuest(QuestInfoDto questInfoDto);
    void modifyQuest(long questNo, QuestInfoDto questInfoDto);
    void deleteQuest(long questNo);
}
