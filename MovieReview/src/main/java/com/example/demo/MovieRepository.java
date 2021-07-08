package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	List<Movie> findByGenre(String genre);

	List<Movie> findByCountry(String country);

}
