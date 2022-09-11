package dip.clever.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dip.clever.model.User;
import dip.clever.service.UserService;

@RestController
@RequestMapping("/user")
public class CheckController {
	
	@Autowired
	private UserService userService;
	
	// 아이디 중복 체크
	@PostMapping("checkId")
	public boolean checkId(String id) {
		return !userService.findUserId(id);
	}

	// 닉네임 중복 체크
	@PostMapping("checkNickname")
	public boolean checkName(String name) {
		return !userService.findUserNickname(name);
	}

	// 패스워드 체크
	@PostMapping("checkPassword")
	public boolean checkPassword(User user) {
		return userService.selectUserList(user) != null;
	}
	
	// 이메일 체크
	@PostMapping("checkEmail")
	public boolean checkEmail(String email) {
		return !userService.findUserEmail(email);
	}
	
}
