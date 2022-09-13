package dip.clever.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dip.clever.model.Choice;
import dip.clever.model.Quest;
import dip.clever.model.TestRound;
import dip.clever.service.MangeQuestService;
import dip.clever.service.UserService;
import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/quest")
public class ManageQuestController {
	
	@Autowired
    private MangeQuestService mangeQuestService;
	
	
	// 문제등록
	@PostMapping("insertQuest")
	public String insertQuest(Model model, int roundNo, Choice choice, Quest quest) {
		Round round = new Round();
		round.setRoundNo(roundNo);
		model.addAttribute("round", mangeQuestService.selectRound(round));
		quest.setRoundNo(roundNo);
		mangeQuestService.insertQuest(quest);
		mangeQuestService.insertChoice(choice);
		return "questForm";
	}
	


}
