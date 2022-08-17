package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@GetMapping("/search")
	public String searchList(@RequestParam("keyword") String keyword, Model model) {
		List<Task> list = this.service.getSearchTaskList(keyword);
		model.addAttribute("taskList", list);
		
		// 検索結果が0件の場合
		if (list.size() == 0) {
			model.addAttribute("NotFindTaskResult", true);
		} else {
			model.addAttribute("NotFindTaskResult", false);
		}
		
		return "task/list";
	}
	
	/**
	 * 削除機能
	 */
	@GetMapping("/delete")
	public String delete(RedirectAttributes redirectAttributes, @RequestParam("id") int id) {
		// 取得したIDより、テーブル名「Task」のレコードを削除する
		this.service.delete(id);
		
		// 削除成功メッセージを出力するための処理
		redirectAttributes.addFlashAttribute("deleteSuccess", true);
		return "redirect:/task/list";
	}
	
}
