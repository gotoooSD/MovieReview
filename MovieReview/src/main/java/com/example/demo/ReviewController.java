package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {
	@Autowired
	HttpSession session;

	@Autowired
	ReviewRepository reviewRepository;

	/**
	  レビュー一覧画面
	 **/
	@RequestMapping("/review/{moviecode}")
	public ModelAndView users(ModelAndView mv) {
		//選択した映画の詳細を表示する

		//選択した映画の全レビューの一覧を表示
		List<Review> reviewList = reviewRepository.findAll();
		mv.addObject("reviews", reviewList);

		//遷移先を指定
		mv.setViewName("reviews");
		return mv;
	}

	/**
	  新規レビュー書き込み画面
	 **/
	@RequestMapping("/review/wrrite")
	public ModelAndView reviewWrrite(ModelAndView mv) {
		//新規レビュー書き込み画面を表示
		mv.setViewName("reviewWrrite");
		return mv;
	}

	@PostMapping("/review/wrrite")
	public ModelAndView reviewWrrite(
			@RequestParam("") String ,
			ModelAndView mv
	) {
		//書き込み情報を受け取ってDBに追加

		////マイページからの書き込みの場合
			//ユーザーコード(usercode)検索で出た値を取得してリストに追加

			//マイレビュー一覧画面(myReviews.html)を表示
			mv.setViewName("myReviews");

		////映画詳細画面からの書き込みの場合
			//映画コード(moviecode)検索で出た値を取得してリストに追加

			//映画詳細内のレビュー一覧画面(reviews.html)を表示
			mv.setViewName("revies");

		////入力不備がある場合
			//レビュー書き込み画面(reviewWrrite.html)を表示して再度書き込ませる
			mv.setViewName("reviewWrrite");


		return mv;
	}

	/**
	  マイレビュー画面
	 **/
	@RequestMapping("/myReviews")
	public ModelAndView reviewWrrite(ModelAndView mv) {
		//ユーザーコード(usercode)検索で出た値を取得してリストに追加

		//マイレビュー一覧(myReviews.html)を表示
		mv.setViewName("myReviews");
		return mv;
	}
}
