package dip.clever.service;

import java.util.List;

import dip.clever.model.User;

public interface UserService {
	
	// 회원가입
	public void insertUser(User user);
	
	// 회원전체리스트 조회
	public User selectUserList(User user);
	
	//아이디로 유저 찾기
	public boolean findUserId(String userId);
	
	//이름으로 유저 찾기
	public boolean findUserNickname(String userNickname);
	
}
