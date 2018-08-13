package com.taskrequestapi.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task {
	@Id
	@Column
	private Integer id;

	@Column
	private Integer executeIn;

	@Column
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

	@Column
	private Timestamp startTask;

	@Column
	private Timestamp endTask;

	public Task() {
		super();
	}

	public Task(Integer id, Integer executeIn, User user, Timestamp startTask, Timestamp endTask) {
		super();
		this.id = id;
		this.executeIn = executeIn;
		this.user = user;
		this.startTask = startTask;
		this.endTask = endTask;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExecuteIn() {
		return executeIn;
	}

	public void setExecuteIn(Integer executeIn) {
		this.executeIn = executeIn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getStartTask() {
		return startTask;
	}

	public void setStartTask(Timestamp startTask) {
		this.startTask = startTask;
	}

	public Timestamp getEndTask() {
		return endTask;
	}

	public void setEndTask(Timestamp endTask) {
		this.endTask = endTask;
	}
}
