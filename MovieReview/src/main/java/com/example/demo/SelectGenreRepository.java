package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectGenreRepository extends JpaRepository<SelectGenre, Integer>{

	List<SelectGenre> findByMoviecode(int moviecode);

}
