package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelpController {
	@Autowired
	HttpSession session;

	@Autowired
	HelpRepository helpRepository;


	/**
	  ヘルプ
	 **/
	//http://localhost:8080/help
	@RequestMapping("/help")
	public ModelAndView help(ModelAndView mv) {
		//ヘルプページ(help.html)を表示
		mv.setViewName("help");
		return mv;
	}

	/**
	  ヘルプアンサー
	 **/
	//http://localhost:8080/helpAnswer
	@RequestMapping("/helpAnswer")
	public ModelAndView helpAnswer(ModelAndView mv) {


		//ヘルプページ(helpAnswer.html)を表示
		mv.setViewName("helpAnswer");
		return mv;
	}
}
