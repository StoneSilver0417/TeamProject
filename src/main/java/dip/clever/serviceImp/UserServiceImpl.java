package dip.clever.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dip.clever.mapper.UserMapper;
import dip.clever.model.User;
import dip.clever.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	// 회원조회
	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}


	@Override
	public User selectUser(User user) {
		return userMapper.selectUser(user);
	}

	//아이디 중복 체크
	@Override
	public boolean findUserId(String userId) {
		return false;
	}

	//닉네임 중복 체크
	@Override
	public boolean findUserNickname(String userNickname) {
		return false;
	}


	@Override
	public boolean findUserEmail(String userEmail) {
		return false;
	}


	
}
