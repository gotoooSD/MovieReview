package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired
	HttpSession session;

	@Autowired
	UserRepository userRepository;

	/**
	  画面
	 **/
	//http://localhost:8080/users
	@RequestMapping("/users")
	public ModelAndView users(ModelAndView mv) {

		//全件検索を実行して表示
		List<User> userList = userRepository.findAll();
		mv.addObject("users", userList);

		//遷移先を指定
		mv.setViewName("users");
		return mv;
	}
}
