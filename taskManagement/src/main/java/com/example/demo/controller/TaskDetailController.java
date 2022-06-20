package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.MstPriority;
import com.example.demo.entity.MstStatus;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskDetailService;

@Controller
@RequestMapping("/task")
public class TaskDetailController {
	
	@Autowired
	TaskDetailService service;
	
	@GetMapping("/detail")
	public String initDetailScreen(@RequestParam("id") int id, Model model) {
		// テーブル名「タスク」から詳細画面に表示する対象レコードを取得
		Task task = this.service.findByTask(id);
		
		// マスタ群の取得
		List<MstPriority> priorityList = this.service.findByAllMstPriority();
		List<MstStatus> statusList = this.service.findByAllMstStatus();
		
		model.addAttribute("task", task);
		model.addAttribute("priorityList", priorityList);
		model.addAttribute("statusList", statusList);
		
		return "task/detail";
	}
}
