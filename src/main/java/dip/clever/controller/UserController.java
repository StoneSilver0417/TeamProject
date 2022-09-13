package dip.clever.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dip.clever.model.User;
import dip.clever.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

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

	// 유저 리스트출력
	@GetMapping("/authority")
	public String checkAll(Model model) {

		List<User> user = userService.findAll();
		model.addAttribute("checkAll", user);
		//System.out.println(user.toString());
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
		String message = "Delete the account successfully";
		System.out.println(id);
		//User user = userService.findSearchResult(id);
		//articleService.deleteAllArticleByUser(user);
		//commentService.deleteCommentByUser(user);
		userService.deleteUser(id); 

		System.out.println("delete complete");
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

}
