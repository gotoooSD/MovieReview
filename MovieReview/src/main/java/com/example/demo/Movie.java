package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class Movie {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private int moviecode;
	private String title;
	private String genre;
	private int time;
	private String country;
	private int year;

	//コンストラクタ
	public Movie() {

	}

	public Movie(String title, String genre, int time, String country,int year) {
		this.title = title;
		this.genre = genre;
		this.time = time;
		this.country = country;
		this.year = year;
	}

	public Movie(int moviecode, String title, String genre, int time, String country,int year) {
		this.moviecode = moviecode;
		this.title = title;
		this.genre = genre;
		this.time = time;
		this.country = country;
		this.year = year;
	}

	//ゲッタ＆セッタのインスタンスを生成
	public int getMoviecode() {
		return moviecode;
	}

	public void setMoivecode(int moviecode) {
		this.moviecode = moviecode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


}
