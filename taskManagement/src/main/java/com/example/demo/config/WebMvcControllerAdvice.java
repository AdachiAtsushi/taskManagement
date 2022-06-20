package com.example.demo.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.service.TaskNotFoundException;

/**
 * �S�Ă�Controller�̋��ʏ������`����B
 *
 */
@ControllerAdvice
public class WebMvcControllerAdvice {
	
	@ExceptionHandler(TaskNotFoundException.class)
	public String handleException(TaskNotFoundException e, Model model) {
		// �G���[���b�Z�[�W���G���[��ʂɘA�g����悤���������{����
		model.addAttribute("message", e.getMessage());
		
		return "error/taskError";
	}
	
}
