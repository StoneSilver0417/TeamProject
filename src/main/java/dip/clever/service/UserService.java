package dip.clever.service;

import dip.clever.model.User;

public interface UserService {
	
	// 회원가입
	public void insertUser(User user);
	
	// 회원조회
	public User selectUser(User user);
	
	//아이디로 유저 찾기
	public boolean findUserId(String userId);
	
	//이름으로 유저 찾기
	public boolean findUserNickname(String userNickname);
	
	//메일로 유저 찾기
	public boolean findUserEmail(String userEmail);
}
