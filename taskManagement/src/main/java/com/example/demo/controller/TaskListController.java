package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskListController {
	
	/**
	 * �����\��
	 * �e�[�u�����u�^�X�N�v�ɑ��݂���f�[�^�S�Ă��擾���A�^�X�N�ꗗ��ʂɕ\������
	 */
	@GetMapping("/list")
	public String initList(Model model) {
		// FIXME �e�[�u�����u�^�X�N�v���S���擾����
		
		// FIXME �擾�f�[�^�����X�g�Ɋi�[
		
		// FIXME �擾�f�[�^��Model�ɐݒ�
		
		return "task/list";
	}
	
	/**
	 * �����@�\
	 */
	
	/**
	 * �폜�@�\
	 */

}
