package com.example.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="reviews")
public class Review {
	////フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private int reviewcode;

	private int moviecode;
	private int usercode;
	private int evaluation;
	private Date date;
	private String title;
	private String text;

	@Transient
	private String name;
	@Transient
	private String gender;
	@Transient
	private String age;

	@Transient
	private String movieTitle;

	////コンストラクタ
	public Review() {//デフォルトコンストラクタ
	}

	public Review(//テーブル内全部＋ユーザ名・性別・年代
			int reviewcode,
			int moviecode,
			int usercode,
			int evaluation,
			Date date,
			String title,
			String text,
			String name,
			String gender,
			String age
	) {
		this.reviewcode = reviewcode;
		this.moviecode = moviecode;
		this.usercode = usercode;
		this.evaluation = evaluation;
		this.date = date;
		this.title = title;
		this.text = text;
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public Review(//テーブル内全部 レビューコードなし
			int moviecode,
			int usercode,
			int evaluation,
			Date date,
			String title,
			String text
	) {
		this.moviecode = moviecode;
		this.usercode = usercode;
		this.evaluation = evaluation;
		this.date = date;
		this.title = title;
		this.text = text;
	}

	public Review(//テーブル内全部＋映画タイトル
			int reviewcode,
			int moviecode,
			int usercode,
			int evaluation,
			Date date,
			String title,
			String text,
			String movieTitle
	) {
		this.reviewcode = reviewcode;
		this.moviecode = moviecode;
		this.usercode = usercode;
		this.evaluation = evaluation;
		this.date = date;
		this.title = title;
		this.text = text;
		this.movieTitle = movieTitle;
	}

	public Review(//評価、日付、タイトル、テキスト
			int evaluation,
			Date date,
			String title,
			String text
	) {
		this.evaluation = evaluation;
		this.date = date;
		this.title = title;
		this.text = text;
	}



	//::必要に応じてコンストラクタは追加する:://


	////ゲッター
	public int getReviewcode() {
		return reviewcode;
	}
	public int getMoviecode() {
		return moviecode;
	}
	public int getUsercode() {
		return usercode;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public Date getDate() {
		return date;
	}
	public String getTitle() {
		return title;
	}
	public String getText() {
		return text;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getAge() {
		return age;
	}
	public String getMovieTitle() {
		return movieTitle;
	}



}