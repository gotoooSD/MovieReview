package com.example.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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




	////コンストラクタ
	public Review() {//デフォルトコンストラクタ
	}

	public Review(//全部あるやつ
			int reviewcode,
			int moviecode,
			int usercode,
			int evaluation,
			Date date,
			String title,
			String text
	) {
		this.reviewcode = reviewcode;
		this.moviecode = moviecode;
		this.usercode = usercode;
		this.evaluation = evaluation;
		this.date = date;
		this.title = title;
		this.text = text;
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

}