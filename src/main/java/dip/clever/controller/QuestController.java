package dip.clever.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import dip.clever.model.Action;
import dip.clever.model.Choice;
import dip.clever.model.Log;
import dip.clever.model.Quest;
import dip.clever.model.Round;
import dip.clever.model.User;
import dip.clever.service.LogService;
import dip.clever.service.QuestService;
import dip.clever.util.Json;
import dip.clever.util.Util;

@Controller
public class QuestController {
	@Autowired
	private QuestService questService;
	@Autowired
	private LogService logService;
	
	@PostMapping("/quest")
	public String quest(Model model, @RequestParam HashMap<String, String> param){
		model.addAttribute("questList", Json.parse(param.get("param")));
		System.out.println(param);

		return "questList";
	}
	
	//시험 목록 반환
	@PostMapping("/quest/select")
	public ResponseEntity<List<Quest>> questList(Round round) {
		return Util.resoponse(questService.selectQuestList(round));		
	}
	
	@GetMapping("/quest/{no}")
	public String quest(Model model, @PathVariable int no) {
		Quest quest = new Quest();
		
		quest.setQuestNo(no);
		
		model.addAttribute("quest", questService.selectQuest(quest));
		model.addAttribute("info", questService.selectQuestInfo(quest));
		
		return "quest";
	}	
	@PostMapping("/quest/solvedList")
	public String selectSolvedList(Model model, User user) {
		model.addAttribute("solvedList", questService.selectSolvedList(user));
		System.out.println(questService.selectSolvedList(user));
		return "/quest/solve-quest";
	}
	
	@PostMapping("/quest/uploadList")
	public String selectUploadList(Model model, User user){
		model.addAttribute("uploadList", questService.selectUploadList(user));
		
		return "/quest/upload-quest";		
	}
	
	@PostMapping("/quest/next")
	public ResponseEntity<Integer> nextQuest(Quest quest) {
		Integer no = questService.selectNextQuest(quest);		
		
		return Util.resoponse(no != null ? no : 0);
	}
	
	@PostMapping("/quest/check")
	public ResponseEntity<Integer[]> checkAnswer(HttpSession httpSession, String param){
		Integer[] solvedQuest = questService.checkAnswer(Json.parse(param));
		User user = (User)httpSession.getAttribute("user");
		Log log;
				
		if(user != null) {
			for(int sq : solvedQuest) {
				log = new Log(user.getUserId(), Action.SOLVED, sq);
				
				logService.insertLog(log);
			}
		}		
		
		return Util.resoponse(solvedQuest);
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
