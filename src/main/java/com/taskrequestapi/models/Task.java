package com.taskrequestapi.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Task {
	@Id
	@Column
	private Integer id;

	@Column
	private Integer executeIn;

	@Column
	private Boolean active;

	@Column
	private Timestamp startTask;

	@Column
	private Timestamp endTask;

	@Column
	private String url;

	@Column
	private String method;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_header", nullable = false)
	private List<Header> header = new ArrayList<Header>();

	public Task() {
	}

	public Task(Integer id, String url, String method, List<Header> header, Integer executeIn, User user,
			Timestamp startTask, Timestamp endTask) {
		this.id = id;
		this.executeIn = executeIn;
		this.user = user;
		this.startTask = startTask;
		this.endTask = endTask;
		this.url = url;
		this.method = method;
		this.header = header;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<Header> getHeader() {
		return header;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setHeader(List<Header> header) {
		this.header = header;
	}
}
