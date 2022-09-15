package dip.clever.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dip.clever.model.User;
import dip.clever.service.UserService;

@RestController
@RequestMapping("/check")
public class CheckController {	
	@Autowired
	private UserService userService;

	// 패스워드 체크
	@PostMapping("checkPassword")
	public boolean checkPassword(User user) {
		return userService.selectUser(user) != null;
	}
//	
//	// 이메일 체크
//	@PostMapping("checkEmail")
//	public boolean checkEmail(String userEmail) {
//		return !userService.findUserEmail(userEmail);
//	}
	
}
