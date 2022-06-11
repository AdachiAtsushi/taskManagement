package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskListService;

@Controller
@RequestMapping("/task")
public class TaskListController {
	
	@Autowired
	TaskListService service;
	
	/**
	 * 初期表示
	 * テーブル名「タスク」に存在するデータ全てを取得し、タスク一覧画面に表示する
	 */
	@GetMapping("/list")
	public String initList(Model model) {
		List<Task> list = this.service.getTaskList();
		
		model.addAttribute("taskList", list);
		
		return "task/list";
	}
	
	/**
	 * 検索機能
	 */
	
	/**
	 * 削除機能
	 */

}
