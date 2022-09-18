package dip.clever.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dip.clever.model.Role;
import dip.clever.model.User;
import dip.clever.service.UserService;
import dip.clever.util.Util;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	// 아이디 중복 체크
	@PostMapping("/user/checkId")
	public ResponseEntity<Boolean> checkId(User user) {
		return Util.resoponse(!userService.findUserId(user));
	}

	// 닉네임 중복 체크
	@PostMapping("/user/checkName")
	public ResponseEntity<Boolean> checkName(User user) {
		return Util.resoponse(!userService.findUserNickname(user));
	}

	// 이메일 체크
	@PostMapping("/user/checkEmail")
	public ResponseEntity<Boolean> checkEmail(User user) {
		return Util.resoponse(!userService.findUserEmail(user));
	}

	// 유저 리스트출력
	@GetMapping("/authority")
	public String checkAll(Model model) {

		List<User> user = userService.findAll();
		model.addAttribute("checkAll", user);
		// System.out.println(user.toString());
		return "Authority";

	}

	// 관리자- 유저 검색 method
	@PostMapping("/manageUser/search-user/{keyword}")
	public String searchUser(@PathVariable String keyword, Model model) {
		System.out.println(keyword);

		System.out.println(userService.findSearchResult(keyword));
		model.addAttribute("userList", userService.findSearchResult(keyword));
		return "AuthoritySearchResult";

	}

	// 유저 영구 삭제 method
	@PostMapping("/manageUser/delete-user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		String message = "삭제완료";
		System.out.println(id);
		// User user = userService.findSearchResult(id);
		// articleService.deleteAllArticleByUser(user);
		// commentService.deleteCommentByUser(user);
		userService.deleteUser(id);

		System.out.println("삭제완료");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// 매니저 권한 부여, 박탈 method
	@PutMapping("/manageUser/{action}-manager/{id}")
	public ResponseEntity<String> addAndRemoveManager(@PathVariable String action, @PathVariable String id) {
		String message = "";
		System.out.println(action);
		System.out.println(id);
		if (action.equals("add")) {
			userService.updateManager(id);
			User user = userService.findUserById(id);
			user.setUserRole(Role.MANAGER);
			userService.insertUser(user);
			message = "매니저로 임명";
		} else if (action.equals("remove")) {
			userService.updateUser(id);
			User user = userService.findUserById(id);
			user.setUserRole(Role.USER);
			userService.insertUser(user);
			message = "유저로강등";
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

//	@ResponseBody
//	@PostMapping("/manageUser/delete-user/{id}")
//	public String deleteUser(@PathVariable String id) {
//		String data = "";
//		if(id != null) {
//			userService.deleteUser(id);
//			data = "ok";
//		}
//		System.out.println(id);
//		return data;
//	}
	// 프로필 설정 - 사진/이름
	@PostMapping("/settings-profile/{action}")
	public String editProfile(@PathVariable String action) {
		if (action.equals("img")) {
			return "edit_forms/edit-img";
		} else {
			return "edit_forms/edit-name";
		}
	}

	// 계정 설정 - 이메일/패스워드
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
	public String deleteAccount(User user, HttpServletRequest httpServletRequest) {
		user = (User) httpServletRequest.getSession().getAttribute("user");
		System.out.println(user);
		userService.deleteAccount(user);

		HttpSession httpSession = httpServletRequest.getSession(false);
		if (httpSession != null)
			httpSession.invalidate();
		return "redirect:";
	}

	@PostMapping("/user/uploadTemp")
	public ResponseEntity<Boolean> uploadTemp(HttpSession httpSession,
			@RequestParam("profileImage") MultipartFile file) {
		final String path = "C:\\Users\\8\\Documents\\GitHub\\TeamProject\\src\\main\\resources\\static\\imgs\\profile\\temp\\";

		return Util.resoponse(uploadImage(httpSession, file, path));
	}

	@PostMapping("/user/uploadProfile")
	public ResponseEntity<Boolean> uploadProfile(HttpSession httpSession,
			@RequestParam("profileImage") MultipartFile file) {
		final String path = "C:\\Users\\8\\Documents\\GitHub\\TeamProject\\src\\main\\resources\\static\\imgs\\profile\\user\\";

		return Util.resoponse(uploadImage(httpSession, file, path));
	}

	private boolean uploadImage(HttpSession httpSession, MultipartFile file, String path) {
		User user = (User) httpSession.getAttribute("user");

		if (user == null || file.getSize() == 0)
			return false;

		path += user.getUserId() + ".png";

		return Util.uploadFile(getInputStream(file), path);
	}

	private InputStream getInputStream(MultipartFile file) {
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return inputStream;
	}

	// 개인정보 수정
	// 이름 수정
	@PostMapping("/update-name")
	public ResponseEntity<String> editName(User user, HttpServletRequest httpServletRequest) {
		String name = user.getUserNickname();
		user = (User) httpServletRequest.getSession().getAttribute("user");
		user.setUserNickname(name);
		userService.editUserName(user);
		String message = "이름이 변경되었습니다.";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// 이메일 수정
	@PostMapping("/edit-email")
	public ResponseEntity<String> editEmail(User user, HttpServletRequest httpServletRequest) {
		String email = user.getUserEmail();
		user = (User) httpServletRequest.getSession().getAttribute("user");
		user.setUserEmail(email);
		userService.editUserEmail(user);
		String message = "이메일이 변경되었습니다.";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// 비밀번호 수정
	@PostMapping("/edit-pwd")
	public ResponseEntity<String> editPwd(User user, HttpServletRequest httpServletRequest) {
		userService.editUserPwd(user);
		String message = "비밀번호가 변경되었습니다.";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
