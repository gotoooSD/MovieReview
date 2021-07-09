package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

	//映画コード検索
	List<Review> findByMoviecode(int moviecode);

	//ユーザコード検索
	List<Review> findByUsercode(int usercode);

}
