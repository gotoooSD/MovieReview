package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	//項目ごとの検索をかける
	List<Movie> findByTitleLike(String keyword);

	List<Movie> findByTitleLikeAndGenrecodeAndCountry(String keyword, int genrecode, String country);


	List<Movie> findByGenrecode(int genrecode);

	List<Movie> findByCountry(String country);

	List<Movie> findByMoviecode(int moviecode);

	List<Movie> findByTitleLikeAndGenrecode(String keyword, int genrecode);

	List<Movie> findByTitleLikeAndCountry(String keyword, String country);

	List<Movie> findByGenrecodeAndCountry(int genrecode, String country);///

	//項目ごとの検索をかけ、また最新の年を出す(year)
	List<Movie> findAllByOrderByYearDesc();

	List<Movie> findByGenrecodeOrderByYearDesc(int genrecode);

	List<Movie> findByCountryOrderByYearDesc(String country);

	List<Movie> findByTitleLikeOrderByYearDesc(String keyword);

	List<Movie> findByTitleLikeAndGenrecodeOrderByYearDesc(String keyword, int genrecode);

	List<Movie> findByTitleLikeAndCountryOrderByYearDesc(String keyword, String country);

	List<Movie> findByGenrecodeAndCountryOrderByYearDesc(int genrecode, String country);

	List<Movie> findByTitleLikeAndGenrecodeAndCountryOrderByYearDesc(String keyword, int genrecode, String country);

	//項目ごとの検索をかけ、また五十音順で並び替え(title)
	List<Movie> findAllByOrderByTitle();

	List<Movie> findByGenrecodeOrderByTitle(int genrecode);

	List<Movie> findByTitleLikeOrderByTitle(String keyword);

	List<Movie> findByTitleLikeAndGenrecodeOrderByTitle(String keyword, int genrecode);

	List<Movie> findByTitleLikeAndCountryOrderByTitle(String keyword, String country);

	List<Movie> findByGenrecodeAndCountryOrderByTitle(int genrecode, String country);

	List<Movie> findByTitleLikeAndGenrecodeAndCountryOrderByTitle(String keyword, int genrecode, String country);

	List<Movie> findByCountryOrderByTitle(String country);

	////項目ごとの検索をかけ、また評価の高い順で並び替え(evaluation)
	List<Movie> findAllByOrderByTotalEvaluationDesc();

	List<Movie> findByGenrecodeOrderByTotalEvaluationDesc(int genrecode);

	List<Movie> findByCountryOrderByTotalEvaluationDesc(String country);

	List<Movie> findByTitleLikeOrderByTotalEvaluationDesc(String keyword);

	List<Movie> findByTitleLikeAndGenrecodeOrderByTotalEvaluationDesc(String keyword, int genrecode);

	List<Movie> findByTitleLikeAndCountryOrderByTotalEvaluationDesc(String keyword, String country);

	List<Movie> findByGenrecodeAndCountryOrderByTotalEvaluationDesc(int genrecode, String country);

	List<Movie> findByTitleLikeAndGenrecodeAndCountryOrderByTotalEvaluationDesc(String keyword, int genrecode,
			String country);

	//レビュー用に
	List<Movie> findByTitle(String movietitle);


}
