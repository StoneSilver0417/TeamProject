package dip.clever.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dip.clever.mapper.ReplyMapper;
import dip.clever.mapper.UserMapper;
import dip.clever.model.Reply;
import dip.clever.model.User;
import dip.clever.service.ReplyService;

@Service
public class ReplyServiceImp implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public List<Reply> findAll() {
		return replyMapper.findAll();
	}

}