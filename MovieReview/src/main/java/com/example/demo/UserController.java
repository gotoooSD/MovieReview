package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired
	HttpSession session;

	@Autowired
	UserRepository userRepository;


	/**
	  トップページ
	 **/
	//http://localhost:8080/
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mv) {
		//遷移先を指定
		mv.setViewName("index");
		return mv;
	}

	/**
	  ログイン画面
	 **/
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView mv) {
		//ログイン情報入力画面(login.html)を表示
		mv.setViewName("login");
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			ModelAndView mv
		) {
		////入力不備がある場合
			//エラーメッセージを表示

			//レビュー書き込み画面(reviewWrrite.html)を表示して再度書き込ませる
			mv.setViewName("login");

		////入力不備なしの場合
		//取得したemailでユーザリストに検索をかける
			///該当ユーザがいない場合、
			//エラーメッセージを表示

			//レビュー書き込み画面(reviewWrrite.html)を表示して再度書き込ませる
			mv.setViewName("login");

			//映画詳細内のレビュー一覧画面(reviews.html)を表示
			mv.setViewName("");


		return mv;
	}

	/**
	  画面
	 **/
	//http://localhost:8080/users
	@RequestMapping("/users")
	public ModelAndView users(ModelAndView mv) {

		//ユーザ情報を表示

		//遷移先を指定
		mv.setViewName("users");
		return mv;
	}
}
