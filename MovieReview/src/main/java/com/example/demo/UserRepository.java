package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	//コード指定したやつだけ取得（便宜上Listを使うけど取得は一つだけ）
//	List<User> findByUserode(int usercode);

	//メースアドレスを指定して取得
	List<User> findByEmail(String email);
}
