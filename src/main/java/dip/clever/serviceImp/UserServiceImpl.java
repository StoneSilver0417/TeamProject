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
	
	
	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}


	@Override
	public User selectUserList(User user) {
		return userMapper.selectUserList(user);
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
	public List<User> findAll() {
		return userMapper.findAll();
	}
	
	@Override
	public List<User> findSearchResult(String keyword) {
 		return userMapper.findSearchResult(keyword);
	}


	@Override
	public void deleteUser(String id) {
		userMapper.deleteUser(id);
		
	}
	
}
