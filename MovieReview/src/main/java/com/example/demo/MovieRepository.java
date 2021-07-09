package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	List<Movie> findByTitleLike(String keyword);

	List<Movie> findByTitleLikeAndGenreAndCountry(String keyword, String genre, String country);

	List<Movie> findByGenre(String genre);

	List<Movie> findByCountry(String country);

	List<Movie> findByMoviecode(int moviecode);

}
