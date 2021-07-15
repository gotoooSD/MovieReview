package com.example.demo;

import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	MovieRepository	movieRepository;

	@Autowired
	ReviewRepository reviewRepository;

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
		////空の入力がある場合(大のif)
		if(email.equals("") ||password.equals("")) {
			//エラーメッセージを表示
			mv.addObject("message", "未入力の項目があります");
			//書き込んだものは保持
			mv.addObject("email", email);
			mv.addObject("password", password);
			//ログイン画面(reviewWrrite.html)を表示して再度書き込ませる
			mv.setViewName("login");

		////空の入力なしの場合(大のelse)
		}else {
		//取得したemailでusersテーブルに検索をかける
		List<User> loginUser = userRepository.findByEmail(email);

			///該当ユーザがいない場合(中のif)
			if(loginUser.size() == 0){
				//エラーメッセージを表示
				mv.addObject("message", "メールアドレスまたはパスワードが間違っています");
				//書き込んだものは保持
				mv.addObject("email", email);
				mv.addObject("password", password);
				//ログイン画面(reviewWrrite.html)を表示して再度書き込ませ
				mv.setViewName("login");

			///該当ユーザがいた場合(中のelse)
			}else {
				//入力されたパスワードと登録してあるパスワードを比較
				User userInfo = loginUser.get(0);//レコードを取得
				String _password = userInfo.getPassword();//登録してあるパスワード

				///パスワードが不一致(小のif)
				if(!(password.equals(_password))){
					//エラーメッセージを表示
					mv.addObject("message", "メールアドレスまたはパスワードが間違っています");
					//書き込んだものは保持
					mv.addObject("email", email);
					mv.addObject("password", password);
					//ログイン画面(reviewWrrite.html)を表示して再度書き込ませ
					mv.setViewName("login");

				///パスワードが一致(小のelse)
				//ログインできるので、次の画面に移行するための処理を行う
				}else {
					//そのユーザ情報（ユーザコード含）をセッションに保存
					session.setAttribute("userInfo", userInfo);

					//マイページ表示用
					//ユーザコード(usercode)検索
					List<Review> myreviewList = reviewRepository.findByUsercode(userInfo.getUsercode());
					session.setAttribute("reviewsSize", myreviewList.size());


					//映画一覧画面(movies.html)を表示
					mv.setViewName("redirect:/movies");
				}///小のelseの終端
			}///中のelseの終端
		}////大のelseの終端
		return mv;
	}

	/**
	  ログアウト
	 **/
	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView mv) {
		//セッションを破棄
		session.invalidate();

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
			@RequestParam(name = "gender",defaultValue = "") String gender,
			@RequestParam("age") String age,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			ModelAndView mv
		) {
		////空の入力がある場合
		if(name.equals("")||email.equals("")||password.equals("")||gender.equals("")||age.equals("")) {
			//エラーメッセージを表示
			mv.addObject("message", "未入力の項目があります");
			//新規登録情報入力画面(addUser.html)を表示して再度書き込ませる
			mv.setViewName("addUser");
		}

		////既存ユーザとの重複がないか調べる
			//RequestParamで取得したemailでusersテーブルに検索をかける
			List<User> addUser1 = userRepository.findByEmail(email);
			//RequestParamで取得したnameでusersテーブルに検索をかける
			List<User> addUser2 = userRepository.findByName(name);

			///1.emailが重複するユーザがいる場合
			if(addUser1.size() != 0) {
				//エラーメッセージを表示
				mv.addObject("message1", "そのメールアドレスは既に登録されています");
				//新規登録情報入力画面(addUser.html)を表示して再度書き込ませる
				mv.setViewName("addUser");
			}

			///2.nameが重複するユーザがいる場合(中のelse if)
			if(addUser2.size() != 0) {
				//エラーメッセージを表示
				mv.addObject("message2", "そのユーザ名は既に登録されています");
				//新規登録情報入力画面(addUser.html)を表示して再度書き込ませる
				mv.setViewName("addUser");
			}

		////空の入力がなく、emailもnameも重複するユーザもいない場合
		if(!(name.equals("")||email.equals("")||password.equals("")||gender.equals("")||age.equals("")) && addUser1.size() == 0 && addUser2.size() == 0){
			//そのユーザ情報をusersテーブルに登録
			User userInfo = new User(name,gender,age,email,password);
			userRepository.saveAndFlush(userInfo);

			//登録が完了したことをメッセージで表示
			mv.addObject("message", "新規ユーザ登録が完了しました");
			//トップ画面(index.html)を表示
			mv.setViewName("index");
		}



		//書き込み途中のものは保持して表示
		mv.addObject("name",name);
		mv.addObject("gender",gender);
		mv.addObject("age",age);
		mv.addObject("email",email);
		mv.addObject("password",password);

		return mv;
	}

	/**
	  ユーザ登録情報編集画面
	 **/
	@RequestMapping("/editUser")
	public ModelAndView editUser(ModelAndView mv) {
		//セッションスコープからユーザ情報を取得し表示
		User userInfoBefore = (User) session.getAttribute("userInfo");
		mv.addObject("userInfo", userInfoBefore);

		//表示用に値をセット
		mv.addObject("name",userInfoBefore.getName());
		mv.addObject("gender",userInfoBefore.getGender());
		mv.addObject("age",userInfoBefore.getAge());
		mv.addObject("email",userInfoBefore.getEmail());
		mv.addObject("password",userInfoBefore.getPassword());

		//ユーザ登録情報編集画面(editUser.html)を表示
		mv.setViewName("editUser");
		return mv;
	}

	@PostMapping("/editUser")
	public ModelAndView editUser(
			@RequestParam("name") String name,
			@RequestParam("gender") String gender,
			@RequestParam("age") String age,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			ModelAndView mv
	) {
		//セッションスコープからユーザ情報を取得
		User userInfoBefore = (User) session.getAttribute("userInfo");

		////空の入力がある場合
		if(name.equals("")||email.equals("")||password.equals("")||gender.equals("")||age.equals("")) {
			//エラーメッセージを表示
			mv.addObject("message", "未入力の項目があります");

			//ユーザ登録情報編集画面(editUser.html)を表示して再度書き込ませる
			mv.setViewName("editUser");
		}

		////既存ユーザとの重複がないか調べる
			List<User> addUser1 = new ArrayList<>();;
			List<User> addUser2 = new ArrayList<>();;

			//RequestParamで取得したemailとセッションのemailが異なる場合
			if(!(email.equals(userInfoBefore.getEmail()))) {
				//RequestParamで取得したemailでusersテーブルに検索をかける
				addUser1 = userRepository.findByEmail(email);
			}
			//RequestParamで取得したnameとセッションのnameが異なる場合
			if(!(name.equals(userInfoBefore.getName()))) {
				//RequestParamで取得したnameでusersテーブルに検索をかける
				addUser2 = userRepository.findByName(name);
			}

			///1.emailが重複するユーザがいる場合
			if(addUser1.size() != 0) {
				//エラーメッセージを表示
				mv.addObject("message1", "そのメールアドレスは既に登録されています");
				//ユーザ登録情報編集画面(editUser.html)を表示して再度書き込ませる
				mv.setViewName("editUser");
			}

			///2.nameが重複するユーザがいる場合
			if(addUser2.size() != 0) {
				//エラーメッセージを表示
				mv.addObject("message2", "そのユーザ名は既に登録されています");
				//ユーザ登録情報編集画面(editUser.html)を表示して再度書き込ませる
				mv.setViewName("editUser");
			}

		////空の入力がなく、emailもnameも重複するユーザもいない場合
		if(!(name.equals("")||email.equals("")||password.equals("")||gender.equals("")||age.equals("")) && addUser1.size() == 0 && addUser2.size() == 0) {
			//usersテーブルでユーザコードを指定してユーザ情報を変更
			User userInfo = new User(userInfoBefore.getUsercode(),name,gender,age,email,password);
			userRepository.saveAndFlush(userInfo);

			//セッションスコープにも変更後のユーザ情報を更新
			session.setAttribute("userInfo", userInfo);

			//変更が完了したことをメッセージで表示
			mv.addObject("message", "変更が完了しました");

			//ユーザ登録情報編集画面(editUser.html)を表示
			mv.setViewName("editUser");
		}

		//書き込み途中のものは保持して表示
		mv.addObject("name",name);
		mv.addObject("gender",gender);
		mv.addObject("age",age);
		mv.addObject("email",email);
		mv.addObject("password",password);

		return mv;
	}

	/**
	  退会（ユーザ）削除
	 **/
		@RequestMapping("/deleteUser")
		public ModelAndView deleteUser(
				ModelAndView mv
			) {
			//セッションスコープからユーザ情報を取得
			User userInfo = (User) session.getAttribute("userInfo");

			//レビュー件数も表示したい
				//ユーザコード(usercode)検索
				List<Review> reviewList = reviewRepository.findByUsercode(userInfo.getUsercode());
				//何件あるか
				int reviewsSize = reviewList.size();

				mv.addObject("reviewsSize",reviewsSize);

			//マイレビュー削除確認画面を表示
			mv.setViewName("deleteUserKakunin");
			return mv;
		}


		@RequestMapping("/deleteUser/kanryou")
		public ModelAndView deketeUserKanryou(
				ModelAndView mv
		) {
			//セッションスコープからユーザ情報を取得
			User userInfo = (User) session.getAttribute("userInfo");


			////そのユーザーが投稿したレビューを削除
			//レビューのユーザコード(usercode)検索
			List<Review> reviewList = reviewRepository.findByUsercode(userInfo.getUsercode());
				//レビューが一件もなければ何もしない
				if(reviewList.size()==0) {
				}else {
					for(Review review: reviewList) {
						//レビューコードを指定してレビューを順番に削除
						reviewRepository.deleteById(review.getReviewcode());

						////movieテーブルに評価を平均して記録する処理
							//movieテーブルからmovietitleを指定して映画コード(moviecode)を取得
							List<Movie> m = movieRepository.findByMoviecode(review.getMoviecode());
							Movie _movieInfo = m.get(0);//レコードを取得

							//作成した映画のmoviecodeの全レビューをreviewテーブルからリストを取得
							List<Review> evaluationList = reviewRepository.findByMoviecode(_movieInfo.getMoviecode());

							//その映画のレビューが0になると評価には0を入力
							double totalEvaluation = 0;
							if(evaluationList.size() != 0) {
								//これらのレビューのevaluationを取り出して平均を求める(totalEvaluation)
								int total =0;
								for(Review e:evaluationList) {
									total += e.getEvaluation();
								}
								totalEvaluation =((double)Math.round(( total / evaluationList.size())* 10))/10;//小数点第2位を四捨五入
							}

							//totalEvaluationを変更してmovieテーブルのレコードを更新
							Movie movieInfo = new Movie(_movieInfo.getMoviecode(),_movieInfo.getTitle(),_movieInfo.getGenrecode(),_movieInfo.getTime(),_movieInfo.getCountry(),_movieInfo.getYear(),totalEvaluation);
							movieRepository.saveAndFlush(movieInfo);
					}
				}

			////そのユーザーを削除
			userRepository.deleteById(userInfo.getUsercode());

			//完了のメッセージを表示
			String message = "退会処理が完了しました";
			mv.addObject("message",message);

			//マイレビュー削除完了画面(deleteUserKanryou.html)を表示
			mv.setViewName("deleteUserKanryou");
			return mv;
		}

	/**
	  マイページ
	 **/
	@RequestMapping("/myPage")
	public ModelAndView myPage(ModelAndView mv) {
		//マイページ(myPage.html)を表示
		mv.setViewName("myPage");
		return mv;
	}

}
