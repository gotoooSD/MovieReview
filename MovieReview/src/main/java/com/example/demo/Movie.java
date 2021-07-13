package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="movies")
public class Movie {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private int moviecode;
	private String title;
	private int genrecode;
	private int time;
	private String country;
	private int year;
	@Column(name="evaluation")
	private double totalEvaluation;

	@Transient
	private String genre;

	//コンストラクタ
	public Movie() {

	}

	//DBからのデータ取得の時に使う
	public Movie(int moviecode,String title, int genrecode, int time, String country,int year,double totalEvaluation) {//テーブル内の全て
		this.moviecode = moviecode;
		this.title = title;
		this.genrecode = genrecode;
		this.time = time;
		this.country = country;
		this.year = year;
		this.totalEvaluation = totalEvaluation;
	}

//	public Movie(String title, int genrecode, int time, String country,int year) {//テーブル内のmoviecode以外
//		this.title = title;
//		this.genrecode = genrecode;
//		this.time = time;
//		this.country = country;
//		this.year = year;
//	}

//	public Movie(String title, String genre, int time, String country,int year) {//テーブル内のmoviecode以外、genrecodeの代わりにgenreを使用
//		this.title = title;
//		this.genre = genre;
//		this.time = time;
//		this.country = country;
//		this.year = year;
//	}

	//htmlに表示するときに使う
	public Movie(int moviecode, String title, String genre, int time, String country,int year,double totalEvaluation) {//テーブル内の全て,genrecodeをgenreに置き換え
		this.moviecode = moviecode;
		this.title = title;
		this.genre = genre;
		this.time = time;
		this.country = country;
		this.year = year;
		this.totalEvaluation = totalEvaluation;
	}

	//ゲッタ＆セッタのインスタンスを生成
	public int getMoviecode() {
		return moviecode;
	}

	public String getTitle() {
		return title;
	}

	public int getGenrecode() {
		return genrecode;
	}

	public int getTime() {
		return time;
	}

	public String getCountry() {
		return country;
	}

	public int getYear() {
		return year;
	}

	public String getGenre() {
		return genre;
	}

	public double getTotalEvaluation() {
		return totalEvaluation;
	}



}
