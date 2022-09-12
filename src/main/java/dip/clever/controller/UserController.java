package dip.clever.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import dip.clever.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// mypage 반환
	@PostMapping("/mypage")
	public String mypage(Model model, Principal principal) {
		model.addAttribute("userInfo", userService.findUserById(principal.getName()));
		System.out.println(userService.findUserById(principal.getName()));
		return "mypage/mypage";
	}

	// 개인정보 수정
	@PostMapping("/mypage-setting")
	public String mypageSetting() {
		return "mypage-setting";
	}

}
