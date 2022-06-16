package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "mstPriority")
public class MstPriority implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * �D��x
	 *  �E1 �F ��
	 *  �E2 �F ��
	 *  �E3 �F ��
	 */
	@Id
	@NotNull
	@Column(name = "priority")
	private String priority;
	
	/**
	 * �D��x�̒l�ɕR�Â��l
	 */
	@NotNull
	@Column(name = "priority_text")
	private String priorityText;
	
}
