package com.pallam.demo.dao;

import com.pallam.demo.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee,Integer>
{
    List<Employee> findByTech(String tech);

    List<Employee> findByAidGreaterThan(int aid);

    @Query("from Employee where tech=?1 order by aname")
    List<Employee>findByTechSorted(String tech);
}
