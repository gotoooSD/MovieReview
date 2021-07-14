package com.example.demo;

import java.util.ArrayList;
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

	@Autowired
	GenreRepository genreRepository;

	/**
	  全映画の一覧画面
	 **/
	//http://localhost:8080/movies
	//全映画の一覧を表示する
	@RequestMapping("/movies")

	public ModelAndView movies(ModelAndView mv) {
		//全件検索を実行して表示
		List<Movie> _movieList = movieRepository.findAll();
		//何件あるかを表示
		int moviesSize = _movieList.size();
		mv.addObject("moviesSize", moviesSize);

			//genrecordをgenre名に置き換える作業
			List<Movie> movieList = new ArrayList<>();
			//拡張for文
			for(Movie movie: _movieList) {
				//その項のgenreコードを取得
				int genrecode = movie.getGenrecode();
				//ジャンルコードを指定してジャンルを検索
				List<Genre> genreList = genreRepository.findByGenrecode(genrecode);
				Genre _genreInfo = genreList.get(0);//レコードを取得
				String genre = _genreInfo.getGenre();//ジャンル名

				//genrecodeをgenreに置き換えたもの(Movie型)をmovieListに追加
				Movie movieInfo = new Movie(movie.getMoviecode(),movie.getTitle(),genre,movie.getTime(),movie.getCountry(),movie.getYear(),movie.getTotalEvaluation());
				movieList.add(movieInfo);
			}
		mv.addObject("movies", movieList);

		mv.addObject("keyword", "");
		mv.addObject("genre", "");
		mv.addObject("country", "");

		//セッションにジャンル一覧を保存(検索条件を選択できるように)
		List<Genre> genres = genreRepository.findAll();
		session.setAttribute("genres", genres);

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

		int genrecode = 0;

		if(!genre.equals("")) {
			//取得したgenreからgenrecordを割り出す
			List<Genre> genrecodehoshii = genreRepository.findByGenre(genre);
			Genre genreInfo = genrecodehoshii.get(0);
			genrecode = genreInfo.getGenrecode();
		}
		List<Movie> _movieList = null;

		if(keyword.equals("") && genre.equals("") && country.equals("")) {
			if(sort.equals("")) {
				//全部入力無いときで検索
				_movieList = movieRepository.findAll();
			}else if(sort.equals("year")){
				//全部入力無いときで検索・最新の制作年で表示
				_movieList = movieRepository.findAllByOrderByYearDesc();
			}else if(sort.equals("title")){
				//全部入力無いときで検索・タイトル五十音順で表示
				_movieList = movieRepository.findAllByOrderByTitle();
			}

		}else if(keyword.equals("") && !genre.equals("") && country.equals("")) {
			if(sort.equals("")) {
			//ジャンルのみで検索
			_movieList = movieRepository.findByGenrecode(genrecode);
			}else if(sort.equals("year")) {
			//ジャンルのみで検索・最新の制作年で表示
			 _movieList = movieRepository.findByGenrecodeOrderByYearDesc(genrecode);
			}else if(sort.equals("title")){
			//ジャンルのみで検索・タイトル五十音順で表示
			_movieList = movieRepository.findByGenrecodeOrderByTitle(genrecode);
			}

		}else if(keyword.equals("") && genre.equals("") && !country.equals("")) {
			if(sort.equals("")) {
			//国のみで検索
			_movieList = movieRepository.findByCountry(country);
			}else if(sort.equals("year")) {
			//国のみで検索・最新の制作年で表示
			_movieList = movieRepository.findByCountryOrderByYearDesc(country);
			}else if(sort.equals("title")){
			//国のみで検索・タイトル五十音順で表示
			_movieList = movieRepository.findByCountryOrderByTitle(country);
			}

		}else if(!keyword.equals("") && genre.equals("") && country.equals("")) {
			if(sort.equals("")) {
			//キーワードのみで探す
			 _movieList = movieRepository.findByTitleLike("%" + keyword + "%");
			}else if(sort.equals("year")) {
			//キーワードのみで探す・最新の制作年で表示
			 _movieList = movieRepository.findByTitleLikeOrderByYearDesc("%" + keyword + "%");
			}else if(sort.equals("title")) {
			//キーワードのみで探す・タイトル五十音順で表示
			_movieList = movieRepository.findByTitleLikeOrderByTitle("%" + keyword + "%");
			}

		}else if(!keyword.equals("") && !genre.equals("") && country.equals("")) {
			if(sort.equals("")) {
				//キーワードとジャンルで検索
				 _movieList = movieRepository.findByTitleLikeAndGenrecode("%" + keyword + "%",genrecode);
			}else if(sort.equals("year")) {
				//キーワードとジャンルで検索・最新の制作年で表示
				 _movieList = movieRepository.findByTitleLikeAndGenrecodeOrderByYearDesc("%" + keyword + "%",genrecode);
			}else if(sort.equals("title")) {
				//キーワードとジャンルで検索・タイトル五十音順で表示
				 _movieList = movieRepository.findByTitleLikeAndGenrecodeOrderByTitle("%" + keyword + "%",genrecode);
			}

		}else if(!keyword.equals("") && genre.equals("") && !country.equals("")) {
			if(sort.equals("")) {
				//キーワードと国で検索
				 _movieList = movieRepository.findByTitleLikeAndCountry("%" + keyword + "%",country);
			}else if(sort.equals("year")) {
				//キーワードと国で検索・最新の制作年で表示
				 _movieList = movieRepository.findByTitleLikeAndCountryOrderByYearDesc("%" + keyword + "%",country);
			}else if(sort.equals("title")) {
				//キーワードとジャンルで検索・タイトル五十音順で表示
				 _movieList = movieRepository.findByTitleLikeAndCountryOrderByTitle("%" + keyword + "%",country);
			}


		}else if(keyword.equals("") && !genre.equals("") && !country.equals("")) {
			if(sort.equals("")) {
				//ジャンルと国で検索
				_movieList = movieRepository.findByGenrecodeAndCountry(genrecode,country);
			}else if(sort.equals("year")) {
				//ジャンルと国で検索・最新の制作年で表示
				_movieList = movieRepository.findByGenrecodeAndCountryOrderByYearDesc(genrecode,country);
			}else if(sort.equals("title")) {
				//ジャンルと国で検索・タイトル五十音順で表示
				_movieList = movieRepository.findByGenrecodeAndCountryOrderByTitle(genrecode,country);
			}

		}else if(!keyword.equals("") && !genre.equals("") && !country.equals("")) {
			if(sort.equals("")) {
				//全部で検索
				 _movieList = movieRepository.findByTitleLikeAndGenrecodeAndCountry("%" + keyword + "%", genrecode, country);
			}else if(sort.equals("year")) {
				//全部で検索・最新の制作年で表示
				 _movieList = movieRepository.findByTitleLikeAndGenrecodeAndCountryOrderByYearDesc("%" + keyword + "%", genrecode, country);
			}else if(sort.equals("title")) {
				//全部で検索・タイトル五十音順で表示
				 _movieList = movieRepository.findByTitleLikeAndGenrecodeAndCountryOrderByTitle("%" + keyword + "%", genrecode, country);
			}
		}

			//genrecordをgenre名に置き換える作業
			List<Movie> movieList = new ArrayList<>();
			//拡張for文
			for(Movie movie: _movieList) {
				//その項のgenreコードを取得
				int _genrecode = movie.getGenrecode();
				//ジャンルコードを指定してジャンルを検索
				List<Genre> genreName = genreRepository.findByGenrecode(_genrecode);
				Genre _movieInfo = genreName.get(0);//レコードを取得
				String _genre = _movieInfo.getGenre();//ジャンル名

				//genrecodeをgenreに置き換えたもの(Movie型)をmovieListに追加
				Movie movieInfo = new Movie(movie.getMoviecode(),movie.getTitle(),_genre,movie.getTime(),movie.getCountry(),movie.getYear(),movie.getTotalEvaluation());
				movieList.add(movieInfo);
			}

		//検索結果が何件あるかを表示
		int moviesSize = _movieList.size();
		mv.addObject("moviesSize", moviesSize);

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
