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
	private Integer reviewcode;

	private Integer moviecode;
	private Integer usercode;

	private Integer evaluation;
	private Date date;
	private String title;
	private String text;

	////コンストラクタ
	public Review() {//デフォルトコンストラクタ
	}

	public Review(//全部あるやつ
			Integer reviewcode,
			Integer moviecode,
			Integer usercode,
			Integer evaluation,
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

	//::必要に応じてコンストラクタは追加する:://


	////ゲッター
	public Integer getReviewcode() {
		return reviewcode;
	}

	public Integer getMoviecode() {
		return moviecode;
	}

	public Integer getUsercode() {
		return usercode;
	}

	public Integer getEvaluation() {
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