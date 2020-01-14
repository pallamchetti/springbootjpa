package hibernate.test;

import hibernate.test.dto.foreignKeyAsso.AccountEntity;
import hibernate.test.dto.foreignKeyAsso.EmployeeEntity;

import org.hibernate.Session;

public class TestForeignKeyAssociation
{
	
	public static void main(String[] args) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
       
		AccountEntity account = new AccountEntity();
		account.setAccountNumber("123-345-65454");
		
		//Add new Employee object
		EmployeeEntity emp = new EmployeeEntity();
		emp.setEmail("demo-user@mail.com");
		emp.setFirstName("demo");
		emp.setLastName("user");
		
		//Save Account
		session.saveOrUpdate(account);
		//Save Employee
		emp.setAccount(account);
		session.saveOrUpdate(emp);
		
		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}

}
