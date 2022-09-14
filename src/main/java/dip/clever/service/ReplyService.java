package dip.clever.service;

import java.util.List;

import dip.clever.model.Reply;
import dip.clever.model.User;

public interface ReplyService {

	public List<Reply> findAll();

	// 회원가입
	public void insertReply(Reply reply);
}
