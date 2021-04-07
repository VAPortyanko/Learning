package by.pva.hibernate.part01.fetching.static_fetching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.jboss.logging.Logger;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestNoFetching extends BaseTest{

	private final static Logger log = Logger.getLogger("org.hibernate.SQL");
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Map<String, String> properties = Collections.singletonMap("hibernate.format_sql", "true");
		buildEntityManagerFactory(properties);
		
		doInJPA(entityManager -> {
		
			Query query1 = entityManager.createQuery("Delete from Employee3");
			Query query2 = entityManager.createQuery("Delete from Project");
			Query query3 = entityManager.createQuery("Delete from Department2");
			
			query1.executeUpdate();
			query2.executeUpdate();
			query3.executeUpdate();
			
			Employee employee = new Employee();
			Department department = new Department();
			Project project = new Project();
			
			employee.setId(1L);
			employee.setAccessLevel(1);
			employee.setUsername("user");
			employee.setPassword("password");
			employee.getProjects().add(project);
			employee.setDepartment(department);
			
			department.setId(1L);
			department.getEmployees().add(employee);
			
			project.setId(1L);
			project.getEmployees().add(employee);

			entityManager.persist(employee);
			entityManager.persist(department);
			entityManager.persist(project);
			
			entityManager.flush();
			entityManager.clear();
			
			log.info("The application gets the Employee data. However, because all associations from Employee "
					+ "are declared as LAZY (JPA defines the default for collections as LAZY) no other data is fetched.");
			Employee employee2 = entityManager.createQuery(
				"select e " +
				"from Employee3 e " +
				"where " +
				"	e.username = :username and " +
				"	e.password = :password",
				Employee.class)
			.setParameter( "username", "user")
			.setParameter( "password", "password")
			.getSingleResult();

			log.info("If the login process does not need access to the Employee information specifically, "
					+ "another fetching optimization here would be to limit the width of the query results");
			Integer accessLevel = entityManager.createQuery(
				"select e.accessLevel " +
				"from Employee3 e " +
				"where " +
				"	e.username = :username and " +
				"	e.password = :password",
				Integer.class)
			.setParameter( "username", "user")
			.setParameter( "password", "password")
			.getSingleResult();
			
		});
		
		entityManagerFactory.close();
		
	}
}

@Entity(name = "Department2")
@Table(name = "Departments2")
class Department {

	@Id
	private Long id;
	@OneToMany(mappedBy = "department")
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

@Entity(name = "Employee3")
@Table(name = "Employees3")
class Employee {

	@Id
	private Long id;
	@NaturalId
	private String username;
	private String password;
	private int accessLevel;
	@ManyToOne(fetch = FetchType.LAZY)
	private Department department;
	@ManyToMany(mappedBy = "employees")
	private List<Project> projects = new ArrayList<>();
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}

@Entity(name = "Project")
@Table(name = "Projects")
class Project {

	@Id
	private Long id;
	@ManyToMany
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