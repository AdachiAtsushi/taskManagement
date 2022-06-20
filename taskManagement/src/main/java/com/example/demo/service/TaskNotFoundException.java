package com.example.demo.service;

/**
 * テーブル名「タスク」のレコードが存在しない時に発生する独自例外
 *
 */
public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TaskNotFoundException(String message) {
		super(message);
	}
	
}
