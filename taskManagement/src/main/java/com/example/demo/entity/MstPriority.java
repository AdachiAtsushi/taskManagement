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
	 * 優先度
	 *  ・1 ： 高
	 *  ・2 ： 中
	 *  ・3 ： 低
	 */
	@Id
	@NotNull
	@Column(name = "priority")
	private String priority;
	
	/**
	 * 優先度の値に紐づく値
	 */
	@NotNull
	@Column(name = "priority_text")
	private String priorityText;
	
}
