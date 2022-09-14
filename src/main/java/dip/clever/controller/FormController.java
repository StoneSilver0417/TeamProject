package dip.clever.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dip.clever.model.Round;
import dip.clever.model.Test;
import dip.clever.model.User;
import dip.clever.service.UserService;

// 폼 이동 컨트롤러
@Controller
@RequestMapping("")
public class FormController {
	
	@Autowired
	private UserService userService;
	
	
	// 메인 페이지로 이동
	@RequestMapping("")
	public String home() {
		return "index";
	}
	
	// 검색 폼으로 이동
	@GetMapping("search")
	public String searchForm() {
		return "searchForm";
	}
	
	//로그인 폼으로 이동
	@GetMapping("login")
	public String loginForm() {
		return "loginForm2";
	}

	//회원 가입 폼으로 이동
	@GetMapping("join")
	public String registerForm() {		
		return "joinForm";
	}
	
	//리스트 폼으로 이동
	@GetMapping("list")
	public String list() {
		return "list";
	}
	
	// 문제등록 폼으로 이동
	@PostMapping("insertQuest")
	public String questForm(Model model, Round round) {
		model.addAttribute("round", round);
		return "questForm";
	}
	
	// 회차등록 폼으로 이동
	@PostMapping("insertRound")
	public String roundForm(Model model, Test test) {
		model.addAttribute("test", test);
		return "roundForm";
	}
	
	
	// 회원가입 메소드
	@PostMapping("join")
	public String join(HttpServletRequest httpServletRequest, Model model, User user) {		
		userService.insertUser(user);
		
		return loginCheck(httpServletRequest, model, user);
	}
	
	//로그인 진행
	@PostMapping("login")
	public String loginCheck(HttpServletRequest httpServletRequest, Model model, User user) {
		user = userService.selectUser(user);
		if (user == null) {
			model.addAttribute("loginError", true);

			return "loginForm2";
		}
		
		httpServletRequest.getSession().setAttribute("user", user);		

		return "redirect:";
	}
	
	//로그아웃
	@RequestMapping("logout")
	public String logout(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession(false);

		if (httpSession != null)
			httpSession.invalidate();

		return "redirect:";
	}
	
//	@GetMapping("authority")
//	public String authorityForm() {
//		return "authority";
//	}
//
//	//로그인 진행
//	@PostMapping("login")
//	public String loginCheck(HttpServletRequest httpServletRequest, Model model, User user) {
//		user = userRepository.selectUser(user);
//		Log log;		
//		
//		if (user == null) {
//			model.addAttribute("loginError", true);
//
//			return "loginForm";
//		}
//		log = new Log(user.getId(), Command.LOGIN, null);
//				
//		logRepository.insertLog(log);
//		httpServletRequest.getSession().setAttribute("user", user);		
//
//		return "redirect:";
//	}
//	
//	//로그아웃
//	@RequestMapping("logout")
//	public String logout(HttpServletRequest httpServletRequest) {
//		HttpSession httpSession = httpServletRequest.getSession(false);
//		
//		if(httpSession != null)
//			httpSession.invalidate();
//		
//		return "redirect:";
//	}
//	
//	//게시판 생성 폼으로 이동
//	@GetMapping("create")
//	public String createForm(HttpServletRequest httpServletRequest) {
//		User user = (User)httpServletRequest.getSession().getAttribute("user");
//				
//		if(user == null || boardRepository.findByManager(user.getId()) != null)
//			return home();
//		return "createForm";
//	}
//	
//	//변경 폼으로 이동
//	@GetMapping("modify")
//	public String modifyForm(HttpServletRequest httpServletRequest) {
//		User user = (User) httpServletRequest.getSession().getAttribute("user");
//		String mode = httpServletRequest.getParameter("m");
//				
//		if(user != null) {
//			if(mode.equals("user"))
//				return "modifyUserForm";
//		}	
//		return home();
//	}
//	
//	//
//	@GetMapping("list/{id}")
//	public String board(Model model, @PathVariable(name = "id") int id) {
//		if(!boardRepository.isBoardExist(id))
//			return home();
//		model.addAttribute("id", id);
//
//		return "list";
//	}
//	
//	@PostMapping("list")
//	public String articleList(Model model, int id, int count, int num) {
//		Page page = new Page(count, num);
//		List<Article> articleList = articleRepository.selectArticleList(id, page.getNumber(), Page.SIZE);		
//		
//		model.addAttribute("articleList", articleList);
//		model.addAttribute("size", Page.SIZE);
//		
//		return "articleList";
//	}
//	
//	@PostMapping("navi")
//	public String navigation(Model model, int count, int num) {
//		Page page = new Page(count, num);
//		model.addAttribute("page",page);
//		
//		return "navigation";
//	}
//	
//	@PostMapping("form")
//	public String writeArticle(HttpServletRequest httpServletRequest, Model model, Article article) {
//		HttpSession httpSession = httpServletRequest.getSession();		
//		String type = "post";
//		
//		if(httpSession.getAttribute("user") == null) {
//			model.addAttribute("authError", true);
//			
//			return loginForm();
//		}
//		if(article.getNo() != null)
//			type = "put";
//		model.addAttribute("article", article);
//		model.addAttribute("type", type);
//			
//		return "form";
//	}
//	
//	@RequestMapping("article")
//	public String article() {
//		return "article";
//	}
//	
//	@RequestMapping("commentList")
//	public String commentList(Model model, int no) {
//		List<Comment> commentList;
//		
//		commentList = commentRepository.selectCommentList(no);
//		
//		model.addAttribute("commentList", commentList);
//		
//		return "commentList";
//	}
//	
//	@GetMapping("search")
//	public String searchForm() {
//		return "search";
//	}
//	
//	@PostMapping("search")
//	public String search(Model model, String query, int count) {
//		List<Article> articleList = articleRepository.findByArticleLike(query);
//		
//		model.addAttribute("articleList", articleList);
//		
//		return "articleList";
//	}
}