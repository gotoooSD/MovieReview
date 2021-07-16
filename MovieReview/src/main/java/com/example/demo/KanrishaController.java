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
	@PostMapping("/klogin1")
	public ModelAndView klogin1(
			@RequestParam("password1") String password1,
			ModelAndView mv
		) {
		//パスワード１を渡して次の画面へ遷移
		mv.addObject("password1", "password1");
		mv.setViewName("klogin2");
		return mv;
	}
	@PostMapping("/klogin2")
	public ModelAndView klogin(
			@RequestParam("password1") String password1,
			@RequestParam("password2") String password2,
			ModelAndView mv
		) {

		if(password1.equals("himitu") && password2.equals("himitu")) {
			//セッションにジャンル一覧を保存(選択できるように)
			List<Genre> genres = genreRepository.findAll();
			session.setAttribute("genres", genres);

			//管理者ページ(kpage.html)を表示
			mv.setViewName("kpage");

		}else {
			//エラーメッセージを表示
			mv.addObject("message", "どちらかまたは両方のパスワードが間違っています");

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


	/**
	  映画登録
	 **/
	//映画登録情報記入画面を表示する
	@RequestMapping("/kaddmovie")
	public ModelAndView kaddmovie(ModelAndView mv) {
		//セッションにジャンル一覧を保存(選択できるように)
		List<Genre> genres = genreRepository.findAll();
		session.setAttribute("genres", genres);

		//遷移先を指定
		mv.setViewName("kaddmovie");
		return mv;
	}

	//記入内容を判定し映画を登録
	@PostMapping("/kaddmovie")
	public ModelAndView kaddmovie(
			@RequestParam("title") String title,
			@RequestParam("genre") String genre,
			@RequestParam("time") String _time,
			@RequestParam("country") String country,
			@RequestParam("year") String _year,
			ModelAndView mv
		) {
		////空の入力がある場合
		if(title.equals("")||genre.equals("")||_time.equals("")||country.equals("")||_year.equals("")) {
			//エラーメッセージを表示
			mv.addObject("message", "未入力の項目があります");
			//新規登録情報入力画面(kaddmovie.html)を表示して再度書き込ませる
			mv.setViewName("kaddmovie");
		}

		////既存映画タイトルとの重複がないか調べる
			//RequestParamで取得したtitleでmoviesテーブルに検索をかける
			List<Movie> addMovie = movieRepository.findByTitle(title);
			///titleが重複するユーザがいる場合
			if(addMovie.size() != 0) {
				//エラーメッセージを表示
				mv.addObject("message1", "その映画タイトルは既に登録されています");
				//新規登録情報入力画面(kaddmovie.html)を表示して再度書き込ませる
				mv.setViewName("kaddmovie");
			}

		////空の入力がなく、emailもnameも重複するユーザもいない場合
		if(!(title.equals("")||genre.equals("")||_time.equals("")||country.equals("")||_year.equals("")) && addMovie.size() == 0){
			////その映画情報をmoviesテーブルに登録
			//ジャンル名をジャンルコードに変換
			List<Genre> genreList = genreRepository.findByGenre(genre);
			Genre _genreInfo = genreList.get(0);//レコードを取得
			int genrecode = _genreInfo.getGenrecode();//ジャンル名

			//Stringをintに変換（yearとtime）
			int year = Integer.parseInt(_year);
			int time = Integer.parseInt(_time);

			Movie movieInfo = new Movie(title, genrecode, time, country, year, 0);
			movieRepository.saveAndFlush(movieInfo);

			//登録が完了したことをメッセージで表示
			mv.addObject("message", "新規映画の追加が完了しました");
			//映画一覧画面(kmovies.html)を表示
			mv.setViewName("redirect:/kmovies");
		}

		//書き込み途中のものは保持して表示
		mv.addObject("title",title);
		mv.addObject("genre",genre);
		mv.addObject("time",_time);
		mv.addObject("country",country);
		mv.addObject("year",_year);

		return mv;
		}

	/**
	  ジャンル登録
	 **/
	//ジャンル登録情報記入画面を表示する
	@RequestMapping("/kaddgenre")
	public ModelAndView kaddgenre(ModelAndView mv) {
		//セッションにジャンル一覧を保存(一覧を見れるように)
		List<Genre> genres = genreRepository.findAll();
		session.setAttribute("genres", genres);

		//遷移先を指定
		mv.setViewName("kaddgenre");
		return mv;
	}

	//記入内容を判定しジャンルを登録
	@PostMapping("/kaddgenre")
	public ModelAndView kaddgenre(
			@RequestParam("genre") String genre,
			ModelAndView mv
		) {



		////空の入力がある場合
		if(genre.equals("")) {
			//エラーメッセージを表示
			mv.addObject("message", "未入力の項目があります");
			//新規登録情報入力画面(kaddgenre.html)を表示して再度書き込ませる
			mv.setViewName("kaddgenre");
		}

		////既存ジャンルとの重複がないか調べる
			//RequestParamで取得したgenreでgenresテーブルに検索をかける
			List<Genre> addGenre = genreRepository.findByGenre(genre);
			///titleが重複するユーザがいる場合
			if(addGenre.size() != 0) {
				//エラーメッセージを表示
				mv.addObject("message", "そのジャンルは既に登録されています");
				//新規登録情報入力画面(kaddgenre.html)を表示して再度書き込ませる
				mv.setViewName("kaddgenre");
			}

		////空の入力がなく、ジャンルも重複しない場合
		if(!(genre.equals("")) && addGenre.size() == 0){
			////その映画情報をgenresテーブルに登録
			Genre _genre = new Genre(genre);
			genreRepository.saveAndFlush(_genre);

			//登録が完了したことをメッセージで表示
			mv.addObject("message", "新規ジャンルの追加が完了しました");
			//kaddgenre.htmlを表示
			mv.setViewName("kaddgenre");
		}

		//セッションにジャンル一覧を保存(一覧を見れるように)
		List<Genre> genres = genreRepository.findAll();
		session.setAttribute("genres", genres);

		return mv;
		}

}
