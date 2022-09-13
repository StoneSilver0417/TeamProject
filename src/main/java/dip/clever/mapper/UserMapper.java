package dip.clever.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dip.clever.model.User;

@Mapper
public interface UserMapper {

	// 회원가입
	public void insertUser(User user);
	
	// 회원전체리스트 조회
	public User selectUserList(User user);
	
	
	public List<User> findAll();
	
	public List<User> findSearchResult(String keyword);

	public void deleteUser(String id);
	
	public void updateManager(String id);
	
	public void updateUser(String id);
	

	// 회원조회
	public User selectUser(User user);

	// 아이디로 유저 찾기
	public String findUserId(String userId);

	// 이름으로 유저 찾기
	public String findUserNickname(String userNickname);

	// 메일로 유저 찾기
	public String findUserEmail(String userEmail);

	// 아이디로 유저 찾기
	public User findUserById(String id);
}
