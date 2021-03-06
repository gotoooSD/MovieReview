package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToiawaseController {

	@Autowired
	HttpSession session;

	@Autowired
	InquiryRepository toiawaseRepository;

	/**
	  お問い合わせ
	 **/
	//http://localhost:8080/toiawase
	@RequestMapping("/toiawase")
	public ModelAndView toiawase(ModelAndView mv) {

		//お問い合わせページ(toiawase.html)を表示
		mv.setViewName("toiawase");
		return mv;
	}

	//http://localhost:8080/toiawase
	@PostMapping("/toiawase")
	public ModelAndView toiawase(
			@RequestParam("title") String title,
			@RequestParam("text") String text,
			ModelAndView mv
		) {
		//入力内容を受け渡す//書き込み途中のものは保持
		mv.addObject("title",title);
		mv.addObject("text",text);

		//お問い合わせページ(toiawase.html)を表示
		mv.setViewName("toiawase");
		return mv;
	}

	/**
	  お問い合わせ・内容を書く
	 **/
	//http://localhost:8080/toiawase
	@PostMapping("/toiawase/gudge")
	public ModelAndView toiawaseGudge(
			@RequestParam("title") String title,
			@RequestParam("text") String text,
			ModelAndView mv
	) {
		////入力不備がある場合(大のif)
		///1.空欄がある場合//Dateは未入力なし
		if(title.equals("")||text.equals("")) {
			//エラーメッセージを表示
			String message = "未記入の項目があります";
			mv.addObject("message",message);

			//書き込み画面(toiawase.html)を表示して再度書き込ませる
			mv.setViewName("toiawase");

		////入力不備がない場合
		}else {
			//確認画面(toiawaseKakunin,html)を表示
			mv.setViewName("toiawaseKakunin");

		}

		//入力内容を受け渡す//書き込み途中のものは保持
		mv.addObject("title",title);
		mv.addObject("text",text);

		return mv;

}
	/**
	  お問い合わせ内容完了画面
	 **/
	@PostMapping("/toiawaseKanryou")
	public ModelAndView toiawaseKanryou(
			@RequestParam("title") String title,
			@RequestParam("text") String text,
			ModelAndView mv
	) {
		////書き込み情報を受け取ってDBに追加

			//inquiriesテーブルにレコードを追加

			Inquiry toiawaseInfo = new Inquiry(title,text);
			toiawaseRepository.saveAndFlush(toiawaseInfo);

			//完了のメッセージを表示

			String message1 = "メッセージ送信が完了しました!";
			mv.addObject("message1",message1);

			String message2 = "ご意見ありがとうございます！";
			mv.addObject("message2",message2);

			//入力内容を受け渡す//書き込み途中のものは保持

			mv.addObject("title",title);
			mv.addObject("text",text);

			//新規レビュー作成完了画面(reviewWrriteKanryou.html)を表示
			mv.setViewName("toiawaseKanryou");
			return mv;
		}

}
