package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	//ユーザコードを指定して取得（便宜上Listを使うけど取得は一つだけ）
	List<User> findByUsercode(int usercode);

	//メールアドレスを指定して取得
	List<User> findByEmail(String email);

	//ユーザ名を指定して取得
	List<User> findByName(String name);
}
