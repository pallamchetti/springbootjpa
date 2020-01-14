package com.example.TestSuite;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.example.TestCases.StudentTableTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ StudentTableTest.class })
public class TestSuite {

	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void setup() {
		Connection connection = null;
		try {
			JdbcTemplate jdbcTemplate = (JdbcTemplate) (new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/service-context-test.xml")).getBean("jdbcTemplate");
			connection = jdbcTemplate.getDataSource().getConnection();
			File rootDir = new File(".");
			File folder = new File(rootDir.getCanonicalPath().concat("/src/test/resources/"));

			for (File file : folder.listFiles()) {
				ScriptUtils.executeSqlScript(connection, new EncodedResource(new FileSystemResource(file.getAbsolutePath()), StandardCharsets.UTF_8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}


