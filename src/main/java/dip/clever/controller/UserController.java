package dip.clever.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dip.clever.model.User;
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

	// mypage - 프로필/계정 정보 수정
	@PostMapping("/settings-{category}")
	public String settingsProfile(@PathVariable String category) {
		if (category.equals("profile")) {
			return "mypage/settings/settings-profile";
		} else {
			return "mypage/settings/settings-account";
		}
	}

	// 프로필 설정 - 사진/이름
	@PostMapping("/settings-profile/{action}")
	public String editProfile(@PathVariable String action) {
		if (action.equals("img")) {
			return "edit_forms/edit-img";
		} else {
			return "edit_forms/edit-name";
		}
	}

	// 계정 설정 - 사진/이름
	@PostMapping("/settings-account/{action}")
	public String editAccount(@PathVariable String action) {
		if (action.equals("email")) {
			return "edit_forms/edit-email";
		} else {
			return "edit_forms/edit-password";
		}
	}

	// 계정 설정 - 회원 탈퇴 (view 리턴)
	@PostMapping("/settings-account/leave")
	public String leave() {
		return "mypage/settings/leave";
	}

	// 계정 설정 - 회원 탈퇴
	@PostMapping("/deleteAccount")
	public String deleteAccount() {
		return "edit_forms/edit-email";
	}

	// 개인정보 수정
	// 이름 수정
	@PostMapping("/update-name")
	public ResponseEntity<String> editName(User user, HttpServletRequest httpServletRequest) {
		httpServletRequest.getSession().setAttribute("user", user);
		userService.editUserName(user);
		String message = "이름이 변경되었습니다.";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// 이메일 수정
	@PostMapping("/edit-email")
	public ResponseEntity<String> editEmail(String email) {
		userService.editUserEmail(email);
		String message = "이메일이 변경되었습니다.";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
