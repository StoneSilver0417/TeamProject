package dip.clever.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dip.clever.model.Category;
import dip.clever.model.Round;
import dip.clever.model.Test;
import dip.clever.service.RoundService;
import dip.clever.service.TestService;
import dip.clever.util.Json;

@Controller
@RequestMapping("round")
public class RoundController {
	@Autowired
	private RoundService roundService;
	@Autowired 
	TestService testService;
	
	@PostMapping("")
	public String round(Model model, @RequestParam HashMap<String, String> param){			
		Json json = new Json(param);

		model.addAttribute("roundList", json.getObject());

		return "round";
	}
	
	//시험 목록 반환
	@PostMapping("/list")
	public ResponseEntity<List<Round>> testList(Model model, Test test) {
		return new ResponseEntity<List<Round>> (roundService.selectRoundList(test), HttpStatus.OK);
	}
	
	@GetMapping("/{no}")
	public String test(Model model, @PathVariable int no) {
		Round round = new Round();
		
		round.setRoundNo(no);
		round = roundService.selectRound(round);		
		
		model.addAttribute("round", round);
		
		return "questList";
	}
	
	// 회차등록
	@PostMapping("/insertRound")
	public String inserRound(Model model, int testNo, Round round) {
		Test test = new Test();
		test.setTestNo(testNo);
		model.addAttribute("test", testService.selectTest(test));
		round.setTestNo(testNo);
		System.out.println(round);
		roundService.insertRound(round);

		return "roundForm";
	}
	
}
