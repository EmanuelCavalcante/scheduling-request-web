package com.taskrequestapi.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TaskExecuted {

	@Column
	@Id
	private Integer id;

	@Column
	private Boolean erro;

	@Column
	private Timestamp date;

	public TaskExecuted() {
		super();
	}

	public TaskExecuted(Integer id, Boolean erro, Timestamp date) {
		super();
		this.id = id;
		this.erro = erro;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getErro() {
		return erro;
	}

	public void setErro(Boolean erro) {
		this.erro = erro;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
