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
public class KanrishaController {
	@Autowired
	HttpSession session;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MovieRepository	movieRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	InquiryRepository inquiryRepository;

	/**
	  管理者ログイン画面
	 **/
	@RequestMapping("/klogin")
	public ModelAndView klogin(ModelAndView mv) {
		//管理者ログイン情報入力画面(klogin.html)を表示
		mv.setViewName("klogin");
		return mv;
	}

	@PostMapping("/klogin")
	public ModelAndView klogin(
			@RequestParam("password") String password,
			ModelAndView mv
		) {

		int passtime = 1;
		if(password.equals("himitu")) {
			//セッションにジャンル一覧を保存(検索条件を選択できるように)
			List<Genre> genres = genreRepository.findAll();
			session.setAttribute("genres", genres);

			//管理者ページ(kpage.html)を表示
			mv.setViewName("kpage");

		}else {
			passtime ++;
			session.setAttribute("passtime", passtime);

			//エラーメッセージを表示
			mv.addObject("message", "パスワードが間違っています");

			//管理者ログイン情報入力画面(klogin.html)を表示
			mv.setViewName("klogin");

		}


		return mv;
	}

	/**
	  映画一覧
	 **/
	//全映画の一覧を表示する
	@RequestMapping("/kmovies")
	public ModelAndView kmovies(ModelAndView mv) {
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
				Movie movieInfo = new Movie(movie.getMoviecode(),movie.getTitle(),movie.getGenrecode(),genre,movie.getTime(),movie.getCountry(),movie.getYear(),movie.getTotalEvaluation());
				movieList.add(movieInfo);
			}
		mv.addObject("movies", movieList);

		//遷移先を指定
		mv.setViewName("kmovies");
		return mv;
	}

	/**
	  ユーザ一覧
	 **/
	//ユーザの一覧を表示する
	@RequestMapping("/kusers")
	public ModelAndView kusers(ModelAndView mv) {
		//全件検索を実行して表示
		List<User> userList = userRepository.findAll();
		//何件あるかを表示
		int usersSize = userList.size();
		mv.addObject("usersSize", usersSize);
		mv.addObject("users", userList);

		//遷移先を指定
		mv.setViewName("kusers");
		return mv;
	}

	/**
	  問い合わせ一覧
	 **/
	//問い合わせの一覧を表示する
	@RequestMapping("/kinquiries")
	public ModelAndView kinquiries(ModelAndView mv) {
		//全件検索を実行して表示
		List<Inquiry> inquiryList = inquiryRepository.findAll();
		//何件あるかを表示
		int inquiriesSize = inquiryList.size();
		mv.addObject("inquiriesSize", inquiriesSize);
		mv.addObject("inquiries", inquiryList);

		//遷移先を指定
		mv.setViewName("kinquiries");
		return mv;
	}

}
