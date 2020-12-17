package by.pva.hibernate.part01.fetching.static_fetching.fetch_anotation;

import static by.pva.hibernate.part01._myUtils.MyUtils.doInHibernateWithDefaultPersistanceUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

public class TestHiobernateSpecificFetchAnnotataion {
	
	public static void main(String[] args) {
	
		Map<String, String> properties = Collections.singletonMap("hibernate.format_sql", "true");
		
		doInHibernateWithDefaultPersistanceUnit(entityManager -> {
			
			Query query = entityManager.createQuery("delete from Employee9");
			query.executeUpdate();
			Query query2 = entityManager.createQuery("delete from Department7");
			query2.executeUpdate();
			
			Department department = new Department();
			department.setId(1L);
			
			Employee employee1 = new Employee();
			Employee employee2 = new Employee();
			Employee employee3 = new Employee();
			Employee employee4 = new Employee();
			Employee employee5 = new Employee();
			employee1.setId(1L);
			employee1.setDepartment(department);
			employee1.setUsername("Employee1");
			employee2.setId(2L);
			employee2.setDepartment(department);
			employee2.setUsername("Employee2");
			employee3.setId(3L);
			employee3.setDepartment(department);
			employee3.setUsername("Employee3");
			employee4.setId(4L);
			employee4.setDepartment(department);
			employee4.setUsername("Employee4");
			employee5.setId(5L);
			employee5.setDepartment(department);
			employee5.setUsername("Employee5");
			department.getEmployees().add(employee1);
			department.getEmployees().add(employee2);
			department.getEmployees().add(employee3);
			department.getEmployees().add(employee4);
			department.getEmployees().add(employee5);
						
			entityManager.persist(department);
			
			entityManager.flush();
			entityManager.clear();
			
			List<Department> departments = entityManager.createQuery("select d from Department7 d", Department.class ).getResultList();
			System.out.println(departments.size());
			departments.get(0).getEmployees().stream().forEach(System.out::println);

			
		}, properties);
		
	}
}

@Entity(name = "Department7")
@Table(name = "Departments7")
class Department {

	@Id
	private Long id;
	@OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<Employee> employees = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}

@Entity(name = "Employee9")
@Table(name = "Employees9")
class Employee {

	@Id
	private Long id;
	@NaturalId
	private String username;
	@ManyToOne(fetch = FetchType.LAZY)
	private Department department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + "]";
	}

}

