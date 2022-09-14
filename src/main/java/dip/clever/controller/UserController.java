package dip.clever.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest; 
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

//import com.example.demo.model.Role;

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
//			User user = userService.findUserById(id);
//			user.setRole(Role.);
//			userService.saveUser(user);
			message = "매니저로 임명";
		} else if (action.equals("remove")) {
			userService.updateUser(id);
//			User user = userService.findUserById(id);
//			user.setRole(Role.ROLE_USER);
//			userService.saveUser(user);
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
	
	@PostMapping("/user/uploadTemp")
	public ResponseEntity<Boolean> uploadTemp(HttpServletRequest httpServletRequest, @RequestParam("profileImage") MultipartFile file){
		User user = (User) httpServletRequest.getSession().getAttribute("user");		
		String path = "C:\\Users\\8\\Documents\\GitHub\\TeamProject\\src\\main\\resources\\static\\imgs\\profile\\temp\\";	
		
		if (user == null || file.getSize() == 0)
			return new ResponseEntity<Boolean> (false, HttpStatus.OK);

		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 크기 : " + file.getSize());

		path += user.getUserId() + ".png";
		System.out.println(path);
		try (	FileOutputStream fos = new FileOutputStream(path); 
				InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new RuntimeException("file Save Error");
		}
		return new ResponseEntity<Boolean> (true, HttpStatus.OK);
	}
	
	@PostMapping("/user/uploadProfile")
	public ResponseEntity<Boolean> uploadProfile(HttpServletRequest httpServletRequest, @RequestParam("profileImage") MultipartFile file){
		User user = (User) httpServletRequest.getSession().getAttribute("user");		
		String path = "C:\\Users\\8\\Documents\\GitHub\\TeamProject\\src\\main\\resources\\static\\imgs\\profile\\user\\";	
		
		if (user == null || file.getSize() == 0)
			return new ResponseEntity<Boolean> (false, HttpStatus.OK);

		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 크기 : " + file.getSize());

		path += user.getUserId() + ".png";
		System.out.println(path);
		try (	FileOutputStream fos = new FileOutputStream(path); 
				InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new RuntimeException("file Save Error");
		}
		return new ResponseEntity<Boolean> (true, HttpStatus.OK);
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
