package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id
	@NotNull
	@Column(name = "id")
	private Integer id;
	
	/**
	 * タイトル（タスクの題名）
	 */
	@NotNull
	@Column(name = "title")
	private String title;
	
	/**
	 * コメント
	 */
	@NotNull
	@Column(name = "comment")
	private String comment;
	
	/**
	 * 優先度
	 */
	@OneToOne
	@JoinColumn(name = "priority", insertable = false, updatable = false)
	private MstPriority mstPriority;
	
	/**
	 * 開始日時
	 */
	@NotNull
	@Column(name = "start_time")
	private LocalDateTime startTime;
	
	/**
	 * 終了日時
	 */
	@NotNull
	@Column(name = "end_time")
	private LocalDateTime endTime;
	
	/**
	 * ステータスID
	 */
	@OneToOne
	@JoinColumn(name = "status_id", insertable = false, updatable = false)
	private MstStatus mstStatus;
	
}
