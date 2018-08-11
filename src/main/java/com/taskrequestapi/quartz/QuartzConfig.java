package com.taskrequestapi.quartz;

import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.SimpleTrigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {

	// @Value("${org.quartz.scheduler.instanceName}")
	private String instanceName = "spring-boot-quartz-demo";

	// @Value("${org.quartz.scheduler.instanceId}")
	private String instanceId = "AUTO";

	// @Value("${org.quartz.threadPool.threadCount}")
	private String threadCount = "5";

	// @Value("${job.startDelay}")
	private Long startDelay = 0l;

	// @Value("${job.repeatInterval}")
	private Long repeatInterval = 6000l;

	// @Value("${job.description}")
	private String description = "Sample job";

	// @Value("${job.key}")
	private String key = "StatisticsJob";

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

		factory.setOverwriteExistingJobs(true);
		factory.setJobFactory(jobFactory(applicationContext));

		Properties quartzProperties = new Properties();
		quartzProperties.setProperty("org.quartz.scheduler.instanceName", instanceName);
		quartzProperties.setProperty("org.quartz.scheduler.instanceId", instanceId);
		quartzProperties.setProperty("org.quartz.threadPool.threadCount", threadCount);

		factory.setDataSource(dataSource);

		factory.setQuartzProperties(quartzProperties);
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