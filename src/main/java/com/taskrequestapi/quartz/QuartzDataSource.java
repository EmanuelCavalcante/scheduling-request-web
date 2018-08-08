package com.taskrequestapi.quartz;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzDataSource {

	private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

	@Bean
	public DataSource dataSource() {

		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:" + TMP_DIR + "/test");

		return ds;
	}

}