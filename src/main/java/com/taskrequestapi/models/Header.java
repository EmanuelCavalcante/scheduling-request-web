package com.taskrequestapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Header {
	@Id
	@Column
	private Integer id;
	@Column
	private String key;
	@Column
	private String value;

	public Header(Integer id, String key, String value) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
	}

	public Header() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
