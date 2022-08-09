package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.MstPriority;
import com.example.demo.entity.MstStatus;
import com.example.demo.entity.Task;
import com.example.demo.form.TaskForm;
import com.example.demo.service.TaskDetailService;

@Controller
@RequestMapping("/task")
public class TaskDetailController {
	
	@Autowired
	TaskDetailService service;
	
	/**
	 * 詳細画面の初期表示
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
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
	
	/**
	 * テーブル名「タスク」の更新機能
	 * 
	 * @param taskForm
	 * @param result
	 * @return
	 */
	@PostMapping(params = "update")
	public String update(@Validated TaskForm taskForm, BindingResult result) {
		if (result.hasErrors()) {
			return "task/detail";
		}
		// テーブル名「タスク」を更新するために、Entityに値を設定する
		Task task = new Task();
		task.setId(taskForm.getId());
		task.setTitle(taskForm.getTitle());
		task.setComment(taskForm.getComment());
		task.setStartTime(taskForm.getStartTime());
		task.setEndTime(taskForm.getEndTime());
		
		// マスタ群の設定
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority(taskForm.getPriority());
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId(taskForm.getStatusId());
		
		task.setMstPriority(mstPriority);
		task.setMstStatus(mstStatus);
		
		this.service.updateTask(task);
		
		// タスク一覧画面にリダイレクト処理を実施する
		return "redirect:/task/list";
	}
	
	@PostMapping(params = "delete")
	public String delete(TaskForm taskForm) {
		this.service.deleteTask(taskForm.getId());
		
		// タスク一覧画面にリダイレクト処理を実施する
		return "redirect:/task/list";
	}
}
