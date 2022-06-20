package com.example.demo.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.service.TaskNotFoundException;

/**
 * 全てのControllerの共通処理を定義する。
 *
 */
@ControllerAdvice
public class WebMvcControllerAdvice {
	
	@ExceptionHandler(TaskNotFoundException.class)
	public String handleException(TaskNotFoundException e, Model model) {
		// エラーメッセージをエラー画面に連携するよう処理を実施する
		model.addAttribute("message", e.getMessage());
		
		return "error/taskError";
	}
	
}
