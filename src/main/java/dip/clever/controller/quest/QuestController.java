package dip.clever.controller.quest;

import dip.clever.service.quest.MangeQuest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class QuestController {

    private final MangeQuest mangeQuest;

}
