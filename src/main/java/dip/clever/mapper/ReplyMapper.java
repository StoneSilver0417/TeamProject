package dip.clever.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dip.clever.model.Reply;
import dip.clever.model.User;

@Mapper
public interface ReplyMapper {

	// 전체 데이터 가져오기
	public List<Reply> findAll();

	// 회원가입
	public void insertReply(Reply reply);

	public List<HashMap<String, Object>> joinUser(int bno);
}
