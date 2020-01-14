package com.example.Runner;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.example.TestSuite.TestSuite;

public class TestSuiteRunner {

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/service-context-test.xml");
	JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
	
	public static void main(String[] args) {

		TestSuiteRunner testRunner = new TestSuiteRunner();
		testRunner.setupData();
		
		Result result = JUnitCore.runClasses(TestSuite.class);
		
		System.out.println("Total run count : " + result.getRunCount());
		System.out.println("Failure count : " + result.getFailureCount());
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("Success count : " + (result.getRunCount() - result.getFailureCount()));
	}

	private void setupData() {
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			File rootDir = new File(".");
			File folder = new File(rootDir.getCanonicalPath().concat("/src/test/resources/"));

			for (File file : folder.listFiles()) {
				ScriptUtils.executeSqlScript(connection, new EncodedResource(new FileSystemResource(file.getAbsolutePath()), StandardCharsets.UTF_8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} 