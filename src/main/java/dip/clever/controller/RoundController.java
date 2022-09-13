package dip.clever.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dip.clever.model.Category;
import dip.clever.model.Round;
import dip.clever.model.Test;
import dip.clever.service.RoundService;
import dip.clever.service.TestService;

@Controller
@RequestMapping("round")
public class RoundController {
	@Autowired
	private RoundService roundService;
	
	//시험 목록 반환
	@PostMapping("/list")
	public String testList(Model model, Test test) {
		List<Round> roundList = roundService.selectRoundList(test);		

		System.out.println(roundList);
		model.addAttribute("roundList", roundList);
		
		return "roundList";
	}
	
	@GetMapping("/{no}")
	public String test(Model model, @PathVariable int no) {
		Round round = new Round();
		
		round.setRoundNo(no);
		round = roundService.selectRound(round);		
		
		model.addAttribute("round", round);
		
		return "round";
	}
}
