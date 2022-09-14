package dip.clever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuestController {
	@PostMapping("/solve-quest")
	public String solveQuest() {
		return "quest/solve-quest";
	}
	
	@PostMapping("/solve-quest/num")
	public String solveQuestDetails() {
		System.out.println("asdadasd");
		return "quest/quest-detail";
	}
}
