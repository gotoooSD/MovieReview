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

	public Review(//テーブル内全部＋ユーザ名・性別・年代//映画詳細画面用
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

	public Review(//テーブル内全部＋映画タイトル//マイレビュー画面用
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

	public Review(//テーブル内全部
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

	public Review(//テーブル内全部＋映画タイトル・ユーザ名//管理者ページのレビュー一覧用
			int reviewcode,
			int moviecode,
			int usercode,
			int evaluation,
			Date date,
			String title,
			String text,
			String movieTitle,
			String name
	) {
		this.reviewcode = reviewcode;
		this.moviecode = moviecode;
		this.usercode = usercode;
		this.evaluation = evaluation;
		this.date = date;
		this.title = title;
		this.text = text;
		this.movieTitle = movieTitle;
		this.name = name;
	}

	public int getReviewcode() {
		return reviewcode;
	}

	public void setReviewcode(int reviewcode) {
		this.reviewcode = reviewcode;
	}

	public int getMoviecode() {
		return moviecode;
	}

	public void setMoviecode(int moviecode) {
		this.moviecode = moviecode;
	}

	public int getUsercode() {
		return usercode;
	}

	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

}