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
	 * �ڍ׉�ʂ̏����\��
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/detail")
	public String initDetailScreen(@RequestParam("id") int id, Model model) {
		// �e�[�u�����u�^�X�N�v����ڍ׉�ʂɕ\������Ώۃ��R�[�h���擾
		Task task = this.service.findByTask(id);
		
		// �}�X�^�Q�̎擾
		List<MstPriority> priorityList = this.service.findByAllMstPriority();
		List<MstStatus> statusList = this.service.findByAllMstStatus();
		
		model.addAttribute("task", task);
		model.addAttribute("priorityList", priorityList);
		model.addAttribute("statusList", statusList);
		
		return "task/detail";
	}
	
	/**
	 * �e�[�u�����u�^�X�N�v�̍X�V�@�\
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
		// �e�[�u�����u�^�X�N�v���X�V���邽�߂ɁAEntity�ɒl��ݒ肷��
		Task task = new Task();
		task.setId(taskForm.getId());
		task.setTitle(taskForm.getTitle());
		task.setComment(taskForm.getComment());
		task.setStartTime(taskForm.getStartTime());
		task.setEndTime(taskForm.getEndTime());
		
		// �}�X�^�Q�̐ݒ�
		MstPriority mstPriority = new MstPriority();
		mstPriority.setPriority(taskForm.getPriority());
		MstStatus mstStatus = new MstStatus();
		mstStatus.setStatusId(taskForm.getStatusId());
		
		task.setMstPriority(mstPriority);
		task.setMstStatus(mstStatus);
		
		this.service.updateTask(task);
		
		// �^�X�N�ꗗ��ʂɃ��_�C���N�g���������{����
		return "redirect:/task/list";
	}
	
	@PostMapping(params = "delete")
	public String delete(TaskForm taskForm) {
		this.service.deleteTask(taskForm.getId());
		
		// �^�X�N�ꗗ��ʂɃ��_�C���N�g���������{����
		return "redirect:/task/list";
	}
}
