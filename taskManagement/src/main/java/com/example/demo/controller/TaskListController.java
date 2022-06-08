package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskListController {
	
	/**
	 * 初期表示
	 * テーブル名「タスク」に存在するデータ全てを取得し、タスク一覧画面に表示する
	 */
	@GetMapping("/list")
	public String initList(Model model) {
		// FIXME テーブル名「タスク」より全件取得する
		
		// FIXME 取得データをリストに格納
		
		// FIXME 取得データをModelに設定
		
		return "task/list";
	}
	
	/**
	 * 検索機能
	 */
	
	/**
	 * 削除機能
	 */

}
