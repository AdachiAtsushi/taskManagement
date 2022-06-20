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
	 * タイトル
	 */
	@NotNull
	private String title;
	
	/**
	 * タイトルの詳細
	 */
	@NotNull
	private String comment;
	
	/**
	 * 優先度
	 */
	@NotNull
	private String priority;
	
	/**
	 * 開始日時
	 */
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startTime;
	
	/**
	 * 終了日時
	 */
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endTime;
	
	/**
	 * ステータスID
	 */
	@NotNull
	private String statusId;

}
