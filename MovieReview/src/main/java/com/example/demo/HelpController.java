package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

		List<Help> quetionlist = null;

		quetionlist = helpRepository.findAll();

		mv.addObject("quetionlist",quetionlist);

		//ヘルプページ(help.html)を表示
		mv.setViewName("help");
		return mv;
	}

	/**
	  ヘルプアンサー
	 **/
	//http://localhost:8080/helpAnswer
	@RequestMapping("/helpAnswer/{code}")
	public ModelAndView helpAnswer(
			@PathVariable("code") int code,
			ModelAndView mv) {

		//選択した映画の詳細を表示する
		List<Help> m = helpRepository.findByCode(code);
		Help HelpInfo = m.get(0);//レコードを取得

		mv.addObject("HelpInfo", HelpInfo);

		//ヘルプページ(helpAnswer.html)を表示
		mv.setViewName("helpAnswer");
		return mv;
	}


}
