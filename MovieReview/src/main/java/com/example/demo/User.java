package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	private String name;
	private String gender;
	private int age;
	private String email;
	private String password;

	//コンストラクタ
	public User() {

	}

	public User(String name, String gender, int age, String email,String password) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.password = password;
	}

	public User(int code, String name, String gender, int age, String email,String password) {
		this.code = code;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.password = password;
	}

	//ゲッタ＆セッタのインスタンスを生成
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
