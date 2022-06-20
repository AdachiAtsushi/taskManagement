package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskHistoryService;

@Controller
@RequestMapping("/task")
public class TaskHistoryController {
	
	@Autowired
	TaskHistoryService service;
	
	/**
	 * �^�X�N������ʂ̏����\��
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
	 * �e�[�u�����u�^�X�N�v�̃f�[�^���폜����
	 * @param id ID
	 * @return
	 */
	@GetMapping("/deleteHistory")
	public String deleteHistory(@RequestParam("id") int id) {
		this.service.deleteTaskHistory(id);
		
		return "redirect:/task/history";
	}
}
