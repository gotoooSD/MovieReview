package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="genres")
public class Genre {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private int genrecode;
	private String genre;


	//コンストラクタ
	public Genre() {

	}

	//ゲッタ＆セッタのインスタンスを生成
	public int getGenrecode() {
		return genrecode;
	}

	public void setGenre(int genrecode) {
		this.genrecode = genrecode;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
