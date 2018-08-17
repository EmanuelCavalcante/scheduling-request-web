package com.taskrequestapi.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task_executed")
public class TaskExecuted {

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private Boolean erro;

	@Column
	private Timestamp date;

	@Column
	private String result;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_task", nullable = false)
	private Task task;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TaskExecuted [id=" + id + ", erro=" + erro + ", date=" + date + ", result=" + result + ", user=" + user
				+ ", task=" + task + "]";
	}

}
