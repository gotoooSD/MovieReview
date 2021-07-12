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

		mv.addObject("keyword", "");
		mv.addObject("genre", "");
		mv.addObject("country", "");

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
			@RequestParam(name = "sort",defaultValue = "") String sort,
			ModelAndView mv) {
		List<Movie> movieList = null;

		if(keyword.equals("") && genre.equals("") && country.equals("")) {
			if(sort.equals("")) {
				//全部入力無いときで検索
				movieList = movieRepository.findAll();
			}else if(sort.equals("year")){
				//全部入力無いときで検索・最新の制作年で表示
				movieList = movieRepository.findAllByOrderByYearDesc();
			}else if(sort.equals("title")){
				//全部入力無いときで検索・タイトル五十音順で表示
				movieList = movieRepository.findAllByOrderByTitle();
			}

		}else if(keyword.equals("") && !genre.equals("") && country.equals("")) {
			if(sort.equals("")) {
			//ジャンルのみで検索
			movieList = movieRepository.findByGenre(genre);
			}else if(sort.equals("year")) {
			//ジャンルのみで検索・最新の制作年で表示
			 movieList = movieRepository.findByGenreOrderByYearDesc(genre);
			}else if(sort.equals("title")){
			//ジャンルのみで検索・タイトル五十音順で表示
			movieList = movieRepository.findByGenreOrderByTitle(genre);
			}

		}else if(keyword.equals("") && genre.equals("") && !country.equals("")) {
			if(sort.equals("")) {
			//国のみで検索
			movieList = movieRepository.findByCountry(country);
			}else if(sort.equals("year")) {
			//国のみで検索・最新の制作年で表示
			movieList = movieRepository.findByCountryOrderByYearDesc(country);
			}else if(sort.equals("title")){
			//ジャンルのみで検索・タイトル五十音順で表示
			movieList = movieRepository.findByGenreOrderByTitle(country);
			}

		}else if(!keyword.equals("") && genre.equals("") && country.equals("")) {
			if(sort.equals("")) {
			//キーワードのみで探す
			 movieList = movieRepository.findByTitleLike("%" + keyword + "%");
			}else if(sort.equals("year")) {
			//キーワードのみで探す・最新の制作年で表示
			 movieList = movieRepository.findByTitleLikeOrderByYearDesc("%" + keyword + "%");
			}else if(sort.equals("title")) {
			//キーワードのみで探す・タイトル五十音順で表示
			movieList = movieRepository.findByTitleLikeOrderByTitle("%" + keyword + "%");
			}

		}else if(!keyword.equals("") && !genre.equals("") && country.equals("")) {
			if(sort.equals("")) {
				//キーワードとジャンルで検索
				 movieList = movieRepository.findByTitleLikeAndGenre("%" + keyword + "%",genre);
			}else if(sort.equals("year")) {
				//キーワードとジャンルで検索・最新の制作年で表示
				 movieList = movieRepository.findByTitleLikeAndGenreOrderByYearDesc("%" + keyword + "%",genre);
			}else if(sort.equals("title")) {
				//キーワードとジャンルで検索・タイトル五十音順で表示
				 movieList = movieRepository.findByTitleLikeAndGenreOrderByTitle("%" + keyword + "%",genre);
			}

		}else if(!keyword.equals("") && genre.equals("") && !country.equals("")) {
			if(sort.equals("")) {
				//キーワードと国で検索
				 movieList = movieRepository.findByTitleLikeAndCountry("%" + keyword + "%",country);
			}else if(sort.equals("year")) {
				//キーワードと国で検索・最新の制作年で表示
				 movieList = movieRepository.findByTitleLikeAndCountryOrderByYearDesc("%" + keyword + "%",country);
			}else if(sort.equals("title")) {
				//キーワードとジャンルで検索・タイトル五十音順で表示
				 movieList = movieRepository.findByTitleLikeAndCountryOrderByTitle("%" + keyword + "%",country);
			}


		}else if(keyword.equals("") && !genre.equals("") && !country.equals("")) {
			if(sort.equals("")) {
				//ジャンルと国で検索
				movieList = movieRepository.findByGenreAndCountry(genre,country);
			}else if(sort.equals("year")) {
				//ジャンルと国で検索・最新の制作年で表示
				movieList = movieRepository.findByGenreAndCountryOrderByYearDesc(genre,country);
			}else if(sort.equals("title")) {
				//ジャンルと国で検索・タイトル五十音順で表示
				movieList = movieRepository.findByGenreAndCountryOrderByTitle(genre,country);
			}

		}else if(!keyword.equals("") && !genre.equals("") && !country.equals("")) {
			if(sort.equals("")) {
				//全部で検索
				 movieList = movieRepository.findByTitleLikeAndGenreAndCountry("%" + keyword + "%", genre, country);
			}else if(sort.equals("year")) {
				//全部で検索・最新の制作年で表示
				 movieList = movieRepository.findByTitleLikeAndGenreAndCountryOrderByYearDesc("%" + keyword + "%", genre, country);
			}else if(sort.equals("title")) {
				//全部で検索・タイトル五十音順で表示
				 movieList = movieRepository.findByTitleLikeAndGenreAndCountryOrderByTitle("%" + keyword + "%", genre, country);
			}
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

}
