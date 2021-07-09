package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {
	@Autowired
	HttpSession session;

	@Autowired
	MovieRepository movieRepository;


	/**
	  全映画の一覧画面
	 **/
	//http://localhost:8080/movies
	//全映画の一覧を表示する
	@RequestMapping("/movies")
	public ModelAndView movies(ModelAndView mv) {

		//全件検索を実行して表示
		List<Movie> movieList = movieRepository.findAll();
		mv.addObject("movies", movieList);

		//遷移先を指定
		mv.setViewName("movies");
		return mv;
	}

	/**
	  キーワードで映画一覧画面
	 **/
	//http://localhost:8080/movies
	//キーワードで映画一覧を表示する
	@RequestMapping("/movies/search")
	public ModelAndView moviesSearch(
			@RequestParam("keyword") String keyword,
			@RequestParam("genre") String genre,
			@RequestParam("country") String country,
			ModelAndView mv) {
		List<Movie> movieList = null;

		if(keyword.equals("") && genre.equals("") && country.equals("")) {
			//全部入力無いとき
			movieList = movieRepository.findAll();
		}else if(keyword.equals("") && !genre.equals("") && country.equals("")) {
			//ジャンルのみで検索
			 movieList = movieRepository.findByGenre(genre);
		}else if(keyword.equals("") && genre.equals("") && !country.equals("")) {
			//国のみで検索
			movieList = movieRepository.findByCountry(country);
		}else if(!keyword.equals("") && genre.equals("") && country.equals("")) {
			//キーワードのみで探す
			 movieList = movieRepository.findByTitleLike("%" + keyword + "%");
		}else if(!keyword.equals("") && !genre.equals("") && country.equals("")) {
			//キーワードとジャンルで検索
			 movieList = movieRepository.findByTitleLikeAndGenre("%" + keyword + "%",genre);
		}else if(!keyword.equals("") && genre.equals("") && !country.equals("")) {
			//キーワードと国で検索
			 movieList = movieRepository.findByTitleLikeAndCountry("%" + keyword + "%",country);
		}else if(keyword.equals("") && !genre.equals("") && !country.equals("")) {
			//ジャンルと国で検索
			movieList = movieRepository.findByGenreAndCountry(genre,country);
		}else if(!keyword.equals("") && !genre.equals("") && !country.equals("")) {
			//全部で検索
			 movieList = movieRepository.findByTitleLikeAndGenreAndCountry("%" + keyword + "%", genre, country);
		}

		//検索を実行して表示
		mv.addObject("movies", movieList);

		//検索内容を保持
		mv.addObject("keyword", keyword);
		mv.addObject("genre", genre);
		mv.addObject("country", country);

		//遷移先を指定
		mv.setViewName("movies");
		return mv;
	}


//
//	/**
//	  映画の検索結果の一覧画面
//	 **/
//	//http://localhost:8080/movies/kensaku
//	//映画の検索結果を一覧で表示する
//	@PostMapping("/movies/kensaku")
//	public ModelAndView moviesKensaku(ModelAndView mv) {
//
//
//		//遷移先を指定
//		mv.setViewName("movies");
//		return mv;
//	}
//
//	/**
//	  映画の並び替えの一覧画面
//	 **/
//	//http://localhost:8080/movies/sort
//	//映画の並び替えをして一覧で表示する
//	@PostMapping("/movies/sort")
//	public ModelAndView moviesSort(ModelAndView mv) {
//		//全件検索を実行して表示
//				List<Movie> movieList = movieRepository.findAll();
//				mv.addObject("movie", movieList);
//
//
//		//遷移先を指定
//		mv.setViewName("movies");
//		return mv;
//	}
//
//	/**
//	  全映画の一覧画面
//	 **/
//	//http://localhost:8080/movies
//	//全映画の一覧を表示する
//	@RequestMapping("/movies/{moviecode}")
//	public ModelAndView moviesCode(ModelAndView mv) {
//
//		//全件検索を実行して表示
//		List<Movie> movieList = movieRepository.findAll();
//		mv.addObject("movie", movieList);
//
//		//遷移先を指定
//		mv.setViewName("movies");
//		return mv;
//	}

}
