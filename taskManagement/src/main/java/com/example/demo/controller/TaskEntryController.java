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
	 * �����\��
	 * �e�[�u�����u�^�X�N�v����폜�ΏۊO�̃f�[�^�̒�����ID�̍ő�l���擾���A
	 * �}�X�^�e�[�u���u�D��x�v�̃f�[�^�S�ʂ��擾���A�o�^��ʂ̏����\�����s���B
	 */
	@GetMapping("/entry")
	public String initialDisplay(Model model) {
		// �e�[�u�����uTask�v��ID�̍ő�l+1���^�X�N�o�^��ʂ�ID�ɐݒ肷��
		TaskForm taskForm = new TaskForm();
		taskForm.setId(this.service.searchTaskMaxId() + 1);
		
		// �}�X�^�e�[�u�����u�D��x�v�A�u�X�e�[�^�X�v���A�S�f�[�^���擾����
		List<MstPriority> priorityList = this.service.findByMstPriority();
		List<MstStatus> statusList = this.service.findByMstStatus();
		
		model.addAttribute("taskForm", taskForm);
		model.addAttribute("priorityList", priorityList);
		model.addAttribute("statusList", statusList);
		
		return "task/entry";
	}
	
	/**
	 * �o�^�@�\
	 * �^�X�N�o�^��ʂ̃f�[�^����ɁA�e�[�u�����u�^�X�N�v�ɓo�^�������s���B
	 * �o�^������������A���_�C���N�g���������{���A�^�X�N�ꗗ��ʂɑJ�ڂ���B
	 */
	@PostMapping("/register")
	public String register(@Validated TaskForm taskForm, BindingResult result, Model model) {
		// �o�^��ʏ�̓��e�ɖ�肪����ꍇ�́A�o�^��ʂɖ߂�
		if (result.hasErrors()) {
			return "task/entry";
		}
		
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		Task task = mapper.map(taskForm, Task.class);
		
		// �}�X�^�Q�̐ݒ�
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
