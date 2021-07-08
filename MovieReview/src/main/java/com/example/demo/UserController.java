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
		//トップページ(index.html)を表示
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
		////空の入力がある場合
			//エラーメッセージを表示

			//ログイン画面(reviewWrrite.html)を表示して再度書き込ませる
			mv.setViewName("login");

		////空の入力なしの場合
		//取得したemailでusersテーブルに検索をかける
			///該当ユーザがいない場合
				//エラーメッセージを表示

			//ログイン画面(reviewWrrite.html)を表示して再度書き込ませる
				mv.setViewName("login");

			///該当ユーザ場合
				//そのユーザ情報（ユーザコード含）をセッションに保存

				//映画一覧画面(movies.html)を表示
				mv.setViewName("movies");

		return mv;
	}

	/**
	  ログアウト
	 **/
	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView mv) {
		//セッションを破棄

		//トップページ(index.html)を表示
		mv.setViewName("index");
		return mv;
	}

	/**
	  新規登録画面
	 **/
	@RequestMapping("/addUser")
	public ModelAndView addUser(ModelAndView mv) {
		//新規登録情報入力画面(addUser.html)を表示
		mv.setViewName("addUser");
		return mv;
	}

	@PostMapping("/addUser")
	public ModelAndView addUser(
			@RequestParam("name") String name,
			@RequestParam("gender") String gender,
			@RequestParam("age") String age,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			ModelAndView mv
		) {
		////空の入力がある場合
			//エラーメッセージを表示

			//新規登録情報入力画面(addUser.html)を表示して再度書き込ませる
			//書き込み途中のものは保持
			mv.setViewName("addUser");

		////空の入力なしの場合
			///既存ユーザとの重複がないか調べる
				//RequestParamで取得したemailでusersテーブルに検索をかける
					//emailに該当ユーザがいる場合
					//エラーメッセージを表示（そのメールアドレスは既に登録されています）

				//RequestParamで取得したnameでusersテーブルに検索をかける
					//nameに該当ユーザがいる場合
					//エラーメッセージを表示（そのユーザ名は既に登録されています）

				//新規登録情報入力画面(addUser.html)を表示して再度書き込ませる
				//書き込み途中のものは保持
				mv.setViewName("addUser");

			///emailかnameが重複するユーザがいない場合
				//そのユーザ情報をusersテーブルに登録

				//トップ画面(index.html)を表示
				//登録が完了したことをメッセージで表示
				mv.setViewName("index");

		return mv;
	}

	/**
	  ユーザ登録情報編集画面
	 **/
	@RequestMapping("/editUser")
	public ModelAndView editUser(ModelAndView mv) {
		//セッションスコープからユーザ情報を取得し表示

		//ユーザ登録情報編集画面(editUser.html)を表示
		mv.setViewName("editUser");
		return mv;
	}

	@PostMapping("/editUser")
	public ModelAndView editUser(
			@RequestParam("") String ,
			ModelAndView mv
		) {
		//セッションスコープからユーザ情報を取得し、ユーザコードも取得

		////空の入力がある場合
			//エラーメッセージを表示

			//ユーザ登録情報編集画面(editUser.html)を表示して再度書き込ませる
			//書き込み途中のものは保持
			mv.setViewName("addUser");

		////空の入力なしの場合
			///既存ユーザとの重複がないか調べる
			//RequestParamで取得したemailとセッションのemailが異なる場合
				//RequestParamで取得したemailでusersテーブルに検索をかける
					//該当ユーザがいる場合
					//エラーメッセージを表示（そのメールアドレスは既に登録されています）
					//ユーザ登録情報編集画面(editUser.html)を表示して再度書き込ませる
					//書き込み途中のものは保持
					mv.setViewName("addUser");
			//RequestParamで取得したnameとセッションのnameが異なる場合
				//RequestParamで取得したnameでusersテーブルに検索をかける
					//該当ユーザがいる場合
					//エラーメッセージを表示（そのユーザ名は既に登録されています）
					//ユーザ登録情報編集画面(editUser.html)を表示して再度書き込ませる
					//書き込み途中のものは保持
					mv.setViewName("addUser");

			///emailかnameが重複するユーザがいない場合
				//usersテーブルでユーザコードを指定してユーザ情報を変更

				//セッションスコープにも変更後のユーザ情報を更新

				//マイページ画面(myPage.html)を表示
				//変更が完了したことをメッセージで表示
				mv.setViewName("myPage");

		return mv;
	}

}
