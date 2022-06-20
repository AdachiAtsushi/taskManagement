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
@Table(name = "mstStatus")
public class MstStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ステータスID
	 *  ・ 1 : 未着手
	 *  ・ 2 : 着手中
	 *  ・ 3 : 完了
	 *  ・ 4 : 保留
	 */
	@Id
	@NotNull
	@Column(name = "status_id")
	private String statusId;
	
	/**
	 * ステータス文言
	 */
	@NotNull
	@Column(name = "status_text")
	private String statusText;
}
