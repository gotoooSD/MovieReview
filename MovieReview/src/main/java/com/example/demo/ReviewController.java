package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {
	@Autowired
	HttpSession session;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	ReviewRepository reviewRepository;

	/**
	  レビュー一覧画面
	 **/
	@SuppressWarnings("null")
	@RequestMapping("/reviews/{moviecode}")
	public ModelAndView users(
			@PathVariable("moviecode") int moviecode,
			ModelAndView mv) {
		//選択した映画の詳細を表示する
		List<Movie> m = movieRepository.findByMoviecode(moviecode);
		Movie movieInfo = m.get(0);//レコードを取得

		mv.addObject("movieInfo", movieInfo);

		//選択した映画の全レビューの一覧を表示
		List<Review> reviewList = reviewRepository.findByMoviecode(moviecode);
		//何件あるか
		int reviewsSize = reviewList.size();
		mv.addObject("reviewsSize", reviewsSize);

			//ユーザコードからユーザ情報を検索してその情報も追加してallreviewListとして保存
			List<Review> allreviewList = new ArrayList<>();
			//拡張for文
			for(Review review: reviewList) {
				//その項のユーザーコードを取得
				int usercode = review.getUsercode();
				//ユーザコードを指定してユーザを検索
				List<User> reviewUser = userRepository.findByUsercode(usercode);
				User userInfo = reviewUser.get(0);//レコードを取得
				String name = userInfo.getName();//ユーザ名
				String gender = userInfo.getGender();//性別
				String age = userInfo.getAge();//年代

				//ユーザ名、性別、年代を追加したもの(Review型)をallreviewListに追加
				Review allreview = new Review(review.getReviewcode(),review.getMoviecode(),review.getUsercode(),review.getEvaluation(),review.getDate(),review.getTitle(),review.getText(),name,gender,age);
				allreviewList.add(allreview);
			}


		mv.addObject("reviews", allreviewList);

		//レビュー一覧(reviews.html)を表示
		mv.setViewName("reviews");
		return mv;
	}

	/**
	  新規レビュー書き込み画面
	 **/
	///マイページのメニューから入った場合(moviecodoを指定しない場合)
	@RequestMapping("/review/wrrite")
	public ModelAndView reviewWrrite(ModelAndView mv) {
		//選択用に映画一覧リストを受け渡す
		List<Movie> movieList = movieRepository.findAll();
		mv.addObject("movies", movieList);

		//日付を受け渡す
			 // 現在日時情報で初期化されたインスタンスの生成
			 Date dateObj = new Date();
			 SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );
			 // 日時情報を指定フォーマットの文字列で取得
			 String display = format.format( dateObj );
			mv.addObject("date", display);
			mv.addObject("dateObj", dateObj);

		//新規レビュー書き込み画面を表示
		mv.setViewName("reviewWrrite");
		return mv;
	}

	///映画詳細画面から入った場合(moviecodoを指定する場合)
	@RequestMapping("/review/wrrite/{moviecode}")
	public ModelAndView reviewWrrite(
				@PathVariable("moviecode") int moviecode,
				ModelAndView mv
		) {
		//映画詳細画面から入った場合は映画のtitleを受け渡す
		List<Movie> m = movieRepository.findByMoviecode(moviecode);
		Movie movieInfo = m.get(0);//レコードを取得
		String movietitle = movieInfo.getTitle();//タイトル
		mv.addObject("movietitle", movietitle);

		//選択用に映画一覧リストを受け渡す
		List<Movie> movieList = movieRepository.findAll();
		mv.addObject("movies", movieList);

		//日付を受け渡す
		long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
		mv.addObject("date", date);

		//新規レビュー書き込み画面を表示
		mv.setViewName("reviewWrrite");
		return mv;
	}

	@PostMapping("/review/wrrite")
	public ModelAndView reviewWrrite(
			@RequestParam("movietitle") String movietitle,
			@RequestParam("evaluation") String evaluation,
			@RequestParam("date") Date date,
			@RequestParam("dateObj") Date dateObj,
			@RequestParam("title") String title,
			@RequestParam("text") String text,
			ModelAndView mv
	) {
		////入力不備がある場合(大のif)
		///1.空欄がある場合//Dateは未入力なし
		if(movietitle.equals("")||evaluation.equals("")||title.equals("")||text.equals("")) {
			//エラーメッセージを表示
			String message = "未記入の項目があります";
			mv.addObject("message",message);

			//レビュー書き込み画面(reviewWrrite.html)を表示して再度書き込ませる
			mv.setViewName("reviewWrrite");

		////入力不備がない場合(大のelse)
		}else {
			//新規レビュー入力内容確認画面(reviewWrriteKakunin,html)を表示
			mv.setViewName("reviewWrriteKakunin");

		}//大のelseの終端

		//入力内容を受け渡す//書き込み途中のものは保持
		mv.addObject("movietitle",movietitle);
		mv.addObject("evaluation",evaluation);
		mv.addObject("date",date);
		mv.addObject("dateObj",dateObj);
		mv.addObject("title",title);
		mv.addObject("text",text);

		return mv;
	}

	/**
	  新規レビュー入力内容確認画面
	 **/
	@PostMapping("/review/wrrite/kanryou")
	public ModelAndView reviewWrriteKanryou(
			@RequestParam("movietitle") String movietitle,
			@RequestParam("evaluation") int evaluation,
			@RequestParam("date") Date date,
			@RequestParam("dateObj") Date dateObj,
			@RequestParam("title") String title,
			@RequestParam("text") String text,
			ModelAndView mv
	) {
		////書き込み情報を受け取ってDBに追加
			//movieテーブルからmovietitleを指定して映画コード(moviecode)を取得
			//映画詳細画面から入った場合は映画のtitleを受け渡す
			List<Movie> m = movieRepository.findByTitle(movietitle);
			Movie movieInfo = m.get(0);//レコードを取得
			int moviecode = movieInfo.getMoviecode();//映画コード
			mv.addObject("moviecode", moviecode);

			//セッションスコープからユーザコード(usercode)を取得
			User userInfo = (User) session.getAttribute("userInfo");
			int usercode = userInfo.getUsercode();

			//reviewテーブルにレコードを追加
			Review reviewInfo = new Review(moviecode,usercode,evaluation,dateObj,title,text);
			reviewRepository.saveAndFlush(reviewInfo);

		//完了のメッセージを表示
		String message = "レビューの投稿が完了しました";
		mv.addObject("message",message);

		//入力内容を受け渡す//書き込み途中のものは保持
		mv.addObject("movietitle",movietitle);
		mv.addObject("evaluation",evaluation);
		mv.addObject("date",date);
		mv.addObject("dateObj",dateObj);
		mv.addObject("title",title);
		mv.addObject("text",text);

		//新規レビュー作成完了画面(reviewWrriteKanryou.html)を表示
		mv.setViewName("reviewWrriteKanryou");
		return mv;
	}

	/**
	  マイレビュー画面
	 **/
	@RequestMapping("/myReviews")
	public ModelAndView myReviews(ModelAndView mv) {
		//セッションスコープからユーザコードを取得
		User userInfoBefore = (User) session.getAttribute("userInfo");
		int usercode = userInfoBefore.getUsercode();

		//ログインしているユーザのの全レビューの一覧を表示
		//ユーザコード(usercode)検索
		List<Review> reviewList = reviewRepository.findByUsercode(usercode);
		//何件あるか
		int reviewsSize = reviewList.size();

			//映画コードから映画のタイトルを追加してallreviewListとして保存
			List<Review> allreviewList = new ArrayList<>();;
			//拡張for文
			for(Review review: reviewList) {
				//その項の映画コードを取得
				int moviecode = review.getMoviecode();
				//映画コードを指定して映画を検索
				List<Movie> reviewMovie = movieRepository.findByMoviecode(moviecode);
				Movie movieInfo = reviewMovie.get(0);//レコードを取得
				String movieTitle = movieInfo.getTitle();//映画タイトル

				//映画タイトルを追加したもの(Review型)をallreviewListに追加
				Review allreview = new Review(review.getReviewcode(),review.getMoviecode(),review.getUsercode(),review.getEvaluation(),review.getDate(),review.getTitle(),review.getText(),movieTitle);
				allreviewList.add(allreview);
			}

		mv.addObject("reviewsSize", reviewsSize);
		mv.addObject("reviews", allreviewList);

		//マイレビュー一覧(myReviews.html)を表示
		mv.setViewName("myReviews");
		return mv;
	}
}
