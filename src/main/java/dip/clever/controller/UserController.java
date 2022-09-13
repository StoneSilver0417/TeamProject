package dip.clever.controller;

import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

	// 계정 설정 - 회원 탈퇴
	@PostMapping("/settings-account/leave")
	public String leave() {
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
}
