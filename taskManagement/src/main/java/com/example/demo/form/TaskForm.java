package com.example.demo.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TaskForm {
	
	/**
	 * ID
	 */
	@NotNull
	private int id;
	
	/**
	 * �^�C�g��
	 */
	@NotNull
	private String title;
	
	/**
	 * �^�C�g���̏ڍ�
	 */
	@NotNull
	private String comment;
	
	/**
	 * �D��x
	 */
	@NotNull
	private String priority;
	
	/**
	 * �J�n����
	 */
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startTime;
	
	/**
	 * �I������
	 */
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endTime;
	
	/**
	 * �X�e�[�^�XID
	 */
	@NotNull
	private String statusId;

}
