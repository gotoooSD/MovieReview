package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	//項目ごとの検索をかける
	List<Movie> findByTitleLike(String keyword);

//	List<Movie> findByTitleLikeAndGenreAndCountry(String keyword, String genre, String country);
	List<Movie> findByTitleLikeAndGenrecodeAndCountry(String keyword, int genrecode, String country);


//	List<Movie> findByGenre(String genre);
	List<Movie> findByGenrecode(int genrecode);

	List<Movie> findByCountry(String country);

	List<Movie> findByMoviecode(int moviecode);

//	List<Movie> findByTitleLikeAndGenre(String keyword, String genre);
	List<Movie> findByTitleLikeAndGenrecode(String keyword, int genrecode);

	List<Movie> findByTitleLikeAndCountry(String keyword, String country);

//	List<Movie> findByGenreAndCountry(String genre, String country);
	List<Movie> findByGenrecodeAndCountry(int genrecode, String country);///

	//項目ごとの検索をかけ、また最新の年を出す(year)
	List<Movie> findAllByOrderByYearDesc();

//	List<Movie> findByGenreOrderByYearDesc(String genre);
	List<Movie> findByGenrecodeOrderByYearDesc(int genrecode);

	List<Movie> findByCountryOrderByYearDesc(String country);

	List<Movie> findByTitleLikeOrderByYearDesc(String keyword);

//	List<Movie> findByTitleLikeAndGenreOrderByYearDesc(String keyword, String genre);
	List<Movie> findByTitleLikeAndGenrecodeOrderByYearDesc(String keyword, int genrecode);

	List<Movie> findByTitleLikeAndCountryOrderByYearDesc(String keyword, String country);

//	List<Movie> findByGenreAndCountryOrderByYearDesc(String genre, String country);
	List<Movie> findByGenrecodeAndCountryOrderByYearDesc(int genrecode, String country);

//	List<Movie> findByTitleLikeAndGenreAndCountryOrderByYearDesc(String keyword, String genre, String country);
	List<Movie> findByTitleLikeAndGenrecodeAndCountryOrderByYearDesc(String keyword, int genrecode, String country);

	//項目ごとの検索をかけ、また五十音順で並び替え(title)
	List<Movie> findAllByOrderByTitle();

	List<Movie> findByTitle(String movietitle);

//	List<Movie> findByGenreOrderByTitle(String genre);
	List<Movie> findByGenrecodeOrderByTitle(int genrecode);

	List<Movie> findByTitleLikeOrderByTitle(String keyword);

//	List<Movie> findByTitleLikeAndGenreOrderByTitle(String keyword, String genre);
	List<Movie> findByTitleLikeAndGenrecodeOrderByTitle(String keyword, int genrecode);

	List<Movie> findByTitleLikeAndCountryOrderByTitle(String keyword, String country);

//	List<Movie> findByGenreAndCountryOrderByTitle(String genre, String country);
	List<Movie> findByGenrecodeAndCountryOrderByTitle(int genrecode, String country);

//	List<Movie> findByTitleLikeAndGenreAndCountryOrderByTitle(String keyword, String genre, String country);
	List<Movie> findByTitleLikeAndGenrecodeAndCountryOrderByTitle(String keyword, int genrecode, String country);

	List<Movie> findByCountryOrderByTitle(String country);


}
