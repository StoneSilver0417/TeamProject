package dip.clever.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dip.clever.mapper.UserMapper;
import dip.clever.model.User;
import dip.clever.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

  // ** 로그인/회원가입 ** 
	// 회원가입
	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	// 회원조회
	@Override
	public User selectUser(User user) {
		return userMapper.selectUser(user);
	}

	// 아이디 중복 체크
	@Override
	public boolean findUserId(String userId) {
		return userMapper.findUserId(userId) != null;
	}

	// 닉네임 중복 체크
	@Override
	public boolean findUserNickname(String userNickname) {
		return userMapper.findUserNickname(userNickname) != null;
	}

	@Override
	public boolean findUserEmail(String userEmail) {
		return userMapper.findUserEmail(userEmail) != null;
	}

  // ** 유저 관리 ** 
  //
	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}
	
  //
	@Override
	public List<User> findSearchResult(String keyword) {
 		return userMapper.findSearchResult(keyword);
	}

  //
	@Override
	public void deleteUser(String id) {
		userMapper.deleteUser(id);
		
	}
	
  // ** 마이페이지 ** 
	// 아이디로 유저 찾기
	@Override
	public User findUserById(String userId) {
		return userMapper.findUserById(userId);
	}

  // 이름 수정
	@Override
	public void editUserName(User user) {
		userMapper.editUserName(user);
	}

	// 이메일 수정
	@Override
	public void editUserEmail(String email) {
		userMapper.editUserEmail(email);
	}


}
