package dip.clever.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		return "mypage.setting/mypage-setting";
	}

}
