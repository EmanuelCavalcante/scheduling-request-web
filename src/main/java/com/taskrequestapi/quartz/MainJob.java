package com.taskrequestapi.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class MainJob implements Job {

	@Autowired
	private MainJobService cronService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		cronService.execute();
	}
}