package com.pallam.demo.controller;

import com.pallam.demo.dao.EmployeeRepo;
import com.pallam.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class EmployeeController
{
	@Autowired
	EmployeeRepo repo;

	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	@RequestMapping("/addEmployee")
	public String addEmployee(Employee employee)
	{
		repo.save(employee);
		return "home.jsp";
	}

	@RequestMapping("/employees")
	@ResponseBody
	public String getEmployees()
	{
		return repo.findAll().toString();


	}
	@RequestMapping("/employee/{aid}")
	@ResponseBody
	public String getEmployee(@PathVariable("aid")int aid)
	{
		return repo.findById(aid).toString();


	}

	/*@RequestMapping("/getEmployee")
	public ModelAndView getEmployee(@RequestParam int aid)
	{
		ModelAndView mv=new ModelAndView("showEmployee.jsp");
		Employee employee=repo.findById(aid).orElse(new Employee());
		System.out.println(repo.findByTech("Java"));
		System.out.println(repo.findByAidGreaterThan(102));
		System.out.println(repo.findByTechSorted("Java"));
		mv.addObject(employee);
		return mv;
	}*/
}
