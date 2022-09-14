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

import dip.clever.model.Quest;
import dip.clever.model.Round;
import dip.clever.model.Test;
import dip.clever.service.QuestService;
import dip.clever.util.Json;

@Controller
@RequestMapping("quest2")
public class QuestController {
	@Autowired
	private QuestService questService;
	
	@PostMapping("")
	public String quest(Model model, @RequestParam HashMap<String, String> param){			
		Json json = new Json(param);

		model.addAttribute("questList",json.getObject());

		return "quest";
	}
	
	//시험 목록 반환
	@PostMapping("/select")
	public ResponseEntity<List<Quest>> questList(Round round) {
		List<Quest> questList = questService.selectQuestList(round);
		
		System.out.println(round);
		
		return new ResponseEntity<List<Quest>> (questList, HttpStatus.OK);
	}
	
	@GetMapping("/{no}")
	public String test(Model model, @PathVariable int no) {
		Quest quest = new Quest();
		
		quest.setQuestNo(no);		
		questService.selectQuest(quest);
		
		model.addAttribute("quest", quest);
		
		return "questInfo";
	}
}
