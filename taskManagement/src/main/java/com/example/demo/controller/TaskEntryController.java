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

import com.example.demo.entity.MstPriority;
import com.example.demo.entity.MstStatus;
import com.example.demo.entity.Task;
import com.example.demo.form.TaskForm;
import com.example.demo.service.TaskEntryService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

@Controller
@RequestMapping("/task")
public class TaskEntryController {
	
	@Autowired
	private TaskEntryService service;
	
	/**
	 * 初期表示
	 * テーブル名「タスク」から削除対象外のデータの中からIDの最大値を取得し、
	 * マスタテーブル「優先度」のデータ全量を取得し、登録画面の初期表示を行う。
	 */
	@GetMapping("/entry")
	public String initialDisplay(Model model) {
		// テーブル名「Task」のIDの最大値+1をタスク登録画面のIDに設定する
		TaskForm taskForm = new TaskForm();
		taskForm.setId(this.service.searchTaskMaxId() + 1);
		
		// マスタテーブル名「優先度」、「ステータス」より、全データを取得する
		List<MstPriority> priorityList = this.service.findByMstPriority();
		List<MstStatus> statusList = this.service.findByMstStatus();
		
		model.addAttribute("taskForm", taskForm);
		model.addAttribute("priorityList", priorityList);
		model.addAttribute("statusList", statusList);
		
		return "task/entry";
	}
	
	/**
	 * 登録機能
	 * タスク登録画面のデータを基に、テーブル名「タスク」に登録処理を行う。
	 * 登録処理が完了後、リダイレクト処理を実施し、タスク一覧画面に遷移する。
	 */
	@PostMapping("/register")
	public String register(@Validated TaskForm taskForm, BindingResult result, Model model) {
		// 登録画面上の内容に問題がある場合は、登録画面に戻る
		if (result.hasErrors()) {
			return "task/entry";
		}
		
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		Task task = mapper.map(taskForm, Task.class);
		
		// マスタ群の設定
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority(taskForm.getPriority());
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId(taskForm.getStatusId());
		
		task.setMstPriority(mstPriority);
		task.setMstStatus(mstStatus);
		
		this.service.insertTask(task);
		
		return "redirect:/task/list";
	}
}
