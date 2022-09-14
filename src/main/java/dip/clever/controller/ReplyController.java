package dip.clever.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dip.clever.model.Reply;
import dip.clever.model.User;
import dip.clever.service.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	ReplyService replyservice;

	// 댓글 리스트출력
	@GetMapping("/reply")
	public String Replyinfo(Model model) {

		List<Reply> reply = replyservice.findAll();
		model.addAttribute("Replyinfo", reply);
		// System.out.println(user.toString());
		return "reply";

	} 

	@PostMapping("/insertReply")
	public ResponseEntity<String> insertReply(Reply reply) {
		String message = "댓글이 작성되었습니다.";
//		User regUser = (User)httpServletRequest.getSession().getAttribute("user");
//		System.out.println(regUser);
//		reply.setRegUser(regUser.getUserId());
//		System.out.println(reply.toString());
		
		reply.setRegUser("1");
		reply.setQuestNo(1);
		
		System.out.println("article.toString() =>" + reply.toString());
		replyservice.insertReply(reply);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
}
