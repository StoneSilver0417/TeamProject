package dip.clever.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import dip.clever.model.Reply;
import dip.clever.model.User;
import dip.clever.service.MangeQuestService;
import dip.clever.service.ReplyService;
import dip.clever.service.UserService;

@Controller
public class ReplyController {

	@Autowired
	ReplyService replyservice;
	@Autowired
	UserService userservice;
	@Autowired
	MangeQuestService managequestservice;



	// 댓글 리스트출력
	@GetMapping("reply")
	public String Replyinfo(Model model) {
		
		int choiceNo = 11;
		List<HashMap<String, Object>> quest=managequestservice.joinQuest(choiceNo);
		model.addAttribute("quest",quest);
		System.out.println(quest);
		
		//model.addAttribute("user",userservice.findAll());
		//User regUser = (User)httpServletRequest.getSession().getAttribute("user");
		//System.out.println(regUser);
		//reply.setRegUser(regUser.getUserId());
		//List<Reply> user = replyservice.findAll();
		
		int questnum=1;
		List<HashMap<String, Object>> user=replyservice.joinUser(questnum);
		model.addAttribute("user",user);
		System.out.println(user);
		
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
	// 댓글 삭제 method
		@PostMapping("/delete-reply/{id}")
		public ResponseEntity<String> deleteReply(@PathVariable String id) {
			String message = "삭제완료";
			System.out.println(id);
			// User user = userService.findSearchResult(id); 
			// articleService.deleteAllArticleByUser(user);
			// commentService.deleteCommentByUser(user);
			replyservice.deleteReply(id);

			System.out.println("삭제완료");
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
	
	@PostMapping("/modifyReply/{id}")
	public ResponseEntity<String> modifyReply(@PathVariable int id, @RequestBody Reply reply) {
		ResponseEntity<String> entity = null;
		 try {
			 reply.setReplyNo(id);
			 replyservice.modifyReply(reply);
	            entity = new ResponseEntity<String>("modSuccess", HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	        return entity;
	}
	
}
