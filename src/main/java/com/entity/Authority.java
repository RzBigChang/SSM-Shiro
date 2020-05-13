package com.entity;

import java.io.Serializable;

public class Authority implements  Serializable{
	private int a_id;
	private String a_name;
	private int a_parentId;
	private String a_remake;
	private int a_type;
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public int getA_parentId() {
		return a_parentId;
	}
	public void setA_parentId(int a_parentId) {
		this.a_parentId = a_parentId;
	}
	public String getA_remake() {
		return a_remake;
	}
	public void setA_remake(String a_remake) {
		this.a_remake = a_remake;
	}
	public int getA_type() {
		return a_type;
	}
	public void setA_type(int a_type) {
		this.a_type = a_type;
	}
}
