package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
		mv.addObject("movie", movieList);

		//遷移先を指定
		mv.setViewName("movie");
		return mv;
	}

	/**
	  ジャンルで映画一覧画面
	 **/
	//http://localhost:8080/movies
	//ジャンルで映画一覧を表示する
	@RequestMapping("/movies/{genre}")
	public ModelAndView moviesgenre(
			@RequestParam("genru") String genru,
			ModelAndView mv) {

		//検索を実行して表示
		List<Movie> movieList = movieRepository.findByGenru(genru);
		mv.addObject("movie", movieList);

		//Thymeleafで表示する準備
		mv.addObject("movie", movieList);

		//遷移先を指定
		mv.setViewName("movies");
		return mv;
	}

	/**
	  国で映画一覧画面
	 **/
	//http://localhost:8080/movies
	//国(洋画/邦画)で映画一覧を表示する
	@RequestMapping("/movies/{country}")
	public ModelAndView moviesCountry(
			@RequestParam("country") String country,
			ModelAndView mv) {

		//検索を実行して表示
		List<Movie> movieList = movieRepository.findByCountry(country);
		mv.addObject("movie", movieList);

		//Thymeleafで表示する準備
		mv.addObject("movie", movieList);

		//遷移先を指定
		mv.setViewName("movies");
		return mv;
	}

	/**
	  映画の検索結果の一覧画面
	 **/
	//http://localhost:8080/movies/kensaku
	//映画の検索結果を一覧で表示する
	@PostMapping("/movies/kensaku")
	public ModelAndView moviesKensaku(ModelAndView mv) {


		//遷移先を指定
		mv.setViewName("movies");
		return mv;
	}

	/**
	  映画の検索結果の一覧画面
	 **/
	//http://localhost:8080/movies/sort
	//映画の並び替えをして一覧で表示する
	@PostMapping("/movies/sort")
	public ModelAndView moviesSort(ModelAndView mv) {
		//全件検索を実行して表示
				List<Movie> movieList = movieRepository.findAll();
				mv.addObject("movie", movieList);


		//遷移先を指定
		mv.setViewName("movies");
		return mv;
	}

}
