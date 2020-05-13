package com.entity;

import java.io.Serializable;
import java.util.List;

public class User implements  Serializable{
	private int id;
	private String username;
	private String saltPassword;
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSaltPassword() {
		return saltPassword;
	}
	public void setSaltPassword(String saltPassword) {
		this.saltPassword = saltPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
