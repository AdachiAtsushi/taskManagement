package com.example.demo.service;

/**
 * �e�[�u�����u�^�X�N�v�̃��R�[�h�����݂��Ȃ����ɔ�������Ǝ���O
 *
 */
public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TaskNotFoundException(String message) {
		super(message);
	}
	
}
