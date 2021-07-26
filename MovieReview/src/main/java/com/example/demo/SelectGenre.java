package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="selectgenres")
public class SelectGenre {

	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	private int moviecode;
	private int genrecode;

	@Transient
	private String genre;

	//コンストラクタ
	public SelectGenre() {

	}


	public SelectGenre(int moviecode,int genrecode) {//テーブル内の全て
		this.moviecode = moviecode;
		this.genrecode = genrecode;
	}

	//ゲッタ＆セッタのインスタンスを生成

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getMoviecode() {
		return moviecode;
	}

	public void setMoviecode(int moviecode) {
		this.moviecode = moviecode;
	}

	public int getGenrecode() {
		return genrecode;
	}

	public void setGenrecode(int genrecode) {
		this.genrecode = genrecode;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}



}
