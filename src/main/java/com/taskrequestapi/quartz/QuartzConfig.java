package com.taskrequestapi.quartz;

import javax.sql.DataSource;

import org.quartz.SimpleTrigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {

	private Long startDelay = 0l;

	private Long repeatInterval = 60000l;

	private String description = "Job principal";

	private String key = "jobPrincipal";

	@Autowired
	private DataSource dataSource;

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {

		QuartzJobFactory sampleJobFactory = new QuartzJobFactory();
		sampleJobFactory.setApplicationContext(applicationContext);
		return sampleJobFactory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) {

		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setConfigLocation(new ClassPathResource("quartz.properties"));
		factory.setOverwriteExistingJobs(true);
		factory.setJobFactory(jobFactory(applicationContext));
		factory.setDataSource(dataSource);

		factory.setTriggers(emailJobTrigger().getObject());

		return factory;
	}

	@Bean(name = "mainJobTrigger")
	public SimpleTriggerFactoryBean emailJobTrigger() {

		SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
		factoryBean.setJobDetail(emailJobDetails().getObject());
		factoryBean.setStartDelay(startDelay);
		factoryBean.setRepeatInterval(repeatInterval);
		factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
		return factoryBean;
	}

	@Bean(name = "mainJobDetails")
	public JobDetailFactoryBean emailJobDetails() {

		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(MainJob.class);
		jobDetailFactoryBean.setDescription(description);
		jobDetailFactoryBean.setDurability(true);
		jobDetailFactoryBean.setName(key);

		return jobDetailFactoryBean;
	}
}