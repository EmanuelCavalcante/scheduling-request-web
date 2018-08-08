package com.taskrequestapi.quartz;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class MainJobService {

	public void execute() {

		System.out.println("Executed in " + new Date());
	}

}
