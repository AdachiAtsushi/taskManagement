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
	 * �X�e�[�^�XID
	 *  �E 1 : ������
	 *  �E 2 : ���蒆
	 *  �E 3 : ����
	 *  �E 4 : �ۗ�
	 */
	@Id
	@NotNull
	@Column(name = "status_id")
	private String statusId;
	
	/**
	 * �X�e�[�^�X����
	 */
	@NotNull
	@Column(name = "status_text")
	private String statusText;
}
