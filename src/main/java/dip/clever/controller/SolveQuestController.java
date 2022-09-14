package dip.clever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SolveQuestController {
	@PostMapping("/solve-quest")
	public String solveQuest() {
		return "quest/solve-quest";
	}
}
