package com.example.TestCases;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.model.Student;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/service-context-test.xml" })
@DatabaseSetup(value = { "file:src/main/webapp/WEB-INF/student_dataset.xml" }, type = DatabaseOperation.INSERT)
@DatabaseTearDown(value = { "file:src/main/webapp/WEB-INF/student_dataset.xml" }, type = DatabaseOperation.DELETE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class StudentTableTest
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void testStudent() {
		System.out.println("Hello Worldaaa!");
		jdbcTemplate.update("UPDATE STUDENT SET NAME = 'MOHAN1' WHERE ID = 1");
		String sql = "SELECT * FROM STUDENT WHERE ID = 1";
		Student student = (Student) jdbcTemplate.queryForObject(sql, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rownumber) throws SQLException {
				Student e = new Student();
				e.setID(rs.getInt(1));
				e.setNAME(rs.getString(2));
				e.setAGE(rs.getInt(3));
				return e;
			}
		});

		assertEquals("MOHAN1", student.getNAME());
		
	}
}