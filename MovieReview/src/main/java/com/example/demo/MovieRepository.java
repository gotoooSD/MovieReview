package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	//項目ごとの検索をかける
	List<Movie> findByTitleLike(String keyword);

	List<Movie> findByTitleLikeAndGenreAndCountry(String keyword, String genre, String country);

	List<Movie> findByGenre(String genre);

	List<Movie> findByCountry(String country);

	List<Movie> findByMoviecode(int moviecode);

	List<Movie> findByTitleLikeAndGenre(String keyword, String genre);

	List<Movie> findByTitleLikeAndCountry(String keyword, String country);

	List<Movie> findByGenreAndCountry(String genre, String country);

	//項目ごとの検索をかけ、また最新の年を出す(year)
	List<Movie> findAllByOrderByYearDesc();

	List<Movie> findByGenreOrderByYearDesc(String genre);

	List<Movie> findByCountryOrderByYearDesc(String country);

	List<Movie> findByTitleLikeOrderByYearDesc(String keyword);

	List<Movie> findByTitleLikeAndGenreOrderByYearDesc(String keyword, String genre);

	List<Movie> findByTitleLikeAndCountryOrderByYearDesc(String keyword, String country);

	List<Movie> findByGenreAndCountryOrderByYearDesc(String genre, String country);

	List<Movie> findByTitleLikeAndGenreAndCountryOrderByYearDesc(String keyword, String genre, String country);

	List<Movie> findAllByOrderByTitle();

}
