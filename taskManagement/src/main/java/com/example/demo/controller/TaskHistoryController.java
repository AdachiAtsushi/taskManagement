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
import com.example.demo.service.TaskHistoryService;

@Controller
@RequestMapping("/task")
public class TaskHistoryController {
	
	@Autowired
	TaskHistoryService service;
	
	/**
	 * タスク履歴画面の初期表示
	 * @param model
	 * @return
	 */
	@GetMapping("/history")
	public String taskHistoryList(Model model) {
		List<Task> taskList = this.service.getClosingTaskList();
		
		model.addAttribute("taskList", taskList);
		
		return "task/history";
	}
	
	/**
	 * タスク履歴画面の検索機能
	 * @param keyword 入力キーワード
	 * @param model
	 * @return
	 */
	@GetMapping("searchHistory")
	public String searchHistory(@RequestParam("keyword") String keyword, Model model) {
		List<Task> taskList = this.service.getSearctClosingTaskList(keyword);
		model.addAttribute("taskList", taskList);
		
		// 検索結果が0件の場合
		if (taskList.size() == 0) {
			model.addAttribute("NotFindTaskResult", true);
		} else {
			model.addAttribute("NotFindTaskResult", false);
		}
		
		return "task/history";
	}
	
	/**
	 * テーブル名「タスク」の指定のIDのレコードを削除する
	 * @param id 指定のID
	 * @return
	 */
	@GetMapping("/deleteHistory")
	public String delete(RedirectAttributes redirectAttributes, @RequestParam("id") Integer id) {
		this.service.deleteTaskHistory(id);
		
		redirectAttributes.addFlashAttribute("deleteSuccess", true);
		return "redirect:/task/history";
	}
}
