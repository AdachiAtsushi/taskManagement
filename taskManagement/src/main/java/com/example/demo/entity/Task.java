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
	 * �^�C�g���i�^�X�N�̑薼�j
	 */
	@NotNull
	@Column(name = "title")
	private String title;
	
	/**
	 * �R�����g
	 */
	@NotNull
	@Column(name = "comment")
	private String comment;
	
	/**
	 * �D��x
	 */
	@OneToOne
	@JoinColumn(name = "priority", insertable = false, updatable = false)
	private MstPriority mstPriority;
	
	/**
	 * �J�n����
	 */
	@NotNull
	@Column(name = "start_time")
	private LocalDateTime startTime;
	
	/**
	 * �I������
	 */
	@NotNull
	@Column(name = "end_time")
	private LocalDateTime endTime;
	
	/**
	 * �X�e�[�^�XID
	 */
	@OneToOne
	@JoinColumn(name = "status_id", insertable = false, updatable = false)
	private MstStatus mstStatus;
	
}
