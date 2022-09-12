package dip.clever.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dip.clever.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// mypage 반환
	@RequestMapping("/mypage")
	public String mypage() {
		return "mypage/mypage";
	}

	// mypage - 개인정보 수정
	@RequestMapping("/mypage-setting")
	public String mypageSetting() {
		return "mypage/settings/mypage-setting";
	}

	// mypage - 프로필 개인정보 수정
	@PostMapping("/settings-{category}")
	public String settingsProfile(@PathVariable String category) {
		System.out.println(category);
		if (category == "profile") {
			return "mypage/settings/settings-profile";
		} else {
			return "mypage/settings/settings-account";
		}
	}

	// 개인정보 수정 - 이름 수정
	@PostMapping("/settings-account/edit-name")
	public String settingsAccount() {
		return "mypage/settings/settings-account";
	}

}
