package dip.clever.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dip.clever.model.TestRound;
import dip.clever.model.User;
import dip.clever.service.MangeQuestService;
import dip.clever.service.UserService;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/quest")
public class ManageQuestController {
	
	@Autowired
    private MangeQuestService mangeQuestService;
	@Autowired
	private UserService userService;
	
	
	// 회차등록
	@PostMapping("insertRound")
	public String insertRound(TestRound testRound, Model model, User user) {
		model.addAttribute("get", user);
		mangeQuestService.insertRound(testRound);
		return "redirect:/insertRound";
	}
	
	// 회차수정
	
	
	// 회차삭제
	
	// 문제등록
	
	
	// 문제수정
	
	// 문제삭제

}
