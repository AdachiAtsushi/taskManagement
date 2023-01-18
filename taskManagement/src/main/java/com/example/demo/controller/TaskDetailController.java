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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String initDetailScreen(@RequestParam("id") int id, @RequestParam("screenType") String screenType, Model model) {
		// テーブル名「タスク」から詳細画面に表示する対象レコードを取得
		Task task = this.service.findByTask(id);
		
		// マスタ群の取得
		List<MstPriority> priorityList = this.service.findByAllMstPriority();
		List<MstStatus> statusList = this.service.findByAllMstStatus();
		
		model.addAttribute("task", task);
		model.addAttribute("priorityList", priorityList);
		model.addAttribute("statusList", statusList);
		model.addAttribute("screenType", screenType);
		
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
	public String update(RedirectAttributes redirectAttributes, @Validated TaskForm taskForm, BindingResult result) {
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
		
		// 削除成功メッセージを出力するための処理
		redirectAttributes.addFlashAttribute("updateSuccess", true);
		
		// タスク一覧画面にリダイレクト処理を実施する
		return "redirect:/task/list";
	}
	
	@GetMapping("/detailDelete")
	public String detailDelete(RedirectAttributes redirectAttributes, @RequestParam("id") int id) {
		this.service.deleteTask(id);
		
		// 削除成功メッセージを出力するための処理
		redirectAttributes.addFlashAttribute("deleteSuccess", true);
		
		// タスク一覧画面にリダイレクト処理を実施する
		return "redirect:/task/list";
	}
}
