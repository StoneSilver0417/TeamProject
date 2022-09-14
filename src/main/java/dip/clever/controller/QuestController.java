package dip.clever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestController {
	@PostMapping("/solve-quest")
	public String solveQuest() {
		return "quest/solve-quest";
	}

	@RequestMapping("/solve-quest/num")
	public String solveQuestDetails() {
 		return "quest/quest-detail";
	}

	@PostMapping("/edit-reply")
	public String editReply() {
		System.out.println("asdadasd");
		return "edit_forms/edit-reply";
	}
}
