package dip.clever.service;

import java.util.List;

import dip.clever.model.User;

public interface UserService {

	// 회원가입
	public void insertUser(User user);

	// 회원조회
	public User selectUser(User user);

	// 아이디로 유저 찾기
	public boolean findUserId(String userId);

	// 이름으로 유저 찾기
	public boolean findUserNickname(String userNickname);

	// 유저리스트 반환
	public List<User> findAll();

	// 유저서치
	List<User> findSearchResult(String keyword);

	// 유저삭제
	public void deleteUser(String id);

	// 유저 > 매니저로 만들기
	public void updateManager(String id);

	// 매니저 >
	public void updateUser(String id);

	// 메일로 유저 찾기
	public boolean findUserEmail(String userEmail);

	// ** mypage **
	// 아이디로 유저 찾기
	public User findUserById(String id);
}
