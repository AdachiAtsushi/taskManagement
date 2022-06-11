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
	 * �����\��
	 * �e�[�u�����u�^�X�N�v�ɑ��݂���f�[�^�S�Ă��擾���A�^�X�N�ꗗ��ʂɕ\������
	 */
	@GetMapping("/list")
	public String initList(Model model) {
		List<Task> list = this.service.getTaskList();
		
		model.addAttribute("taskList", list);
		
		return "task/list";
	}
	
	/**
	 * �����@�\
	 */
	
	/**
	 * �폜�@�\
	 */

}
