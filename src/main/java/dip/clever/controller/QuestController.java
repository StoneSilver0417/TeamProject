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

import dip.clever.model.Choice;
import dip.clever.model.Quest;
import dip.clever.model.Round;
import dip.clever.model.User;
import dip.clever.service.QuestService;
import dip.clever.util.Json;
import dip.clever.util.Util;

@Controller
public class QuestController {
	@Autowired
	private QuestService questService;
	
	@PostMapping("/quest")
	public String quest(Model model, @RequestParam HashMap<String, String> param){
		model.addAttribute("questList", Json.parse(param.get("param")));
		
		System.out.println(Json.parse(param.get("param")));

		return "questList";
	}
	
	//시험 목록 반환
	@PostMapping("/quest/select")
	public ResponseEntity<List<Quest>> questList(Round round) {
		List<Quest> questList = questService.selectQuestList(round);
		
		//questList.stream().forEach(t -> t.setChoice(null));
		
		return Util.resoponse(questService.selectQuestList(round));		
	}
	
	@GetMapping("/quest/{no}")
	public String test(Model model, @PathVariable int no) {
		Quest quest = new Quest();
		
		quest.setQuestNo(no);		
		questService.selectQuest(quest);
		
		model.addAttribute("quest", quest);
		
		return "questInfo";
	}	

	@PostMapping("/quest/solvedList")
	public ResponseEntity<List<Quest>> selectSolvedList(User user) {
		return Util.resoponse(questService.selectSolvedList(user));
	}
	
	@PostMapping("/quest/uploadList")
	public ResponseEntity<List<Quest>> selectUploadList(User user){
		return Util.resoponse(questService.selectUploadList(user));
	}
	
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
	
	private Choice selectChoice(Quest quest) {
		return questService.selectChoice(quest);
	}
}
