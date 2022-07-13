package com.example.demo.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginForm {
	/**
	 * ユーザー名
	 */
	@NotNull
	private String userName;
	
	/**
	 * パスワード
	 */
	@NotNull
	private String password;
}
