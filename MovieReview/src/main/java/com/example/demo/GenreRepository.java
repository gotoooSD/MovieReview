package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>{
	//ジャンルコードを指定して取得（便宜上Listを使うけど取得は一つだけ）
	List<Genre> findByGenrecode(int genrecode);

	//ジャンル名を指定して取得（便宜上Listを使うけど取得は一つだけ）
	List<Genre> findByGenre(String genre);
}
