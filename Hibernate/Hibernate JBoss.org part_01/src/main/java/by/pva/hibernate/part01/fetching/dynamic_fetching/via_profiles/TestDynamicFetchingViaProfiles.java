package by.pva.hibernate.part01.fetching.dynamic_fetching.via_profiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.FetchProfile;
import org.hibernate.Session;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestDynamicFetchingViaProfiles extends BaseTest{
	
	public static void main(String[] args) {
		
		Map<String, String> properties = Collections.singletonMap("hibernate.format_sql", "true");
		buildEntityManagerFactory(properties);
		
		doInJPA(entityManager -> {

			Query query1 = entityManager.createQuery("Delete from Employee7");
			Query query2 = entityManager.createQuery("Delete from Project5");
			
			query1.executeUpdate();
			query2.executeUpdate();
			
			Employee employee = new Employee();
			Project project = new Project();

			employee.setId(1L);
			employee.setUsername("user");
			employee.setPassword("password");
			employee.getProjects().add(project);

			project.setId(1L);
			project.setProjectName("Project");
			project.getEmployees().add(employee);

			entityManager.persist(employee);
			entityManager.persist(project);

			entityManager.flush();
			entityManager.clear();
			
			Session session = entityManager.unwrap(Session.class);
			session.enableFetchProfile("employee.projects"); // Comment this line and look at the result.

			Employee employee2 = session.bySimpleNaturalId( Employee.class ).load("user");

			System.out.println(employee2.getProjects().get(0).getProjectName());
			
		});
		
		entityManagerFactory.close();
	}
}

@Entity(name = "Employee7")
@Table(name = "Employees7")
@FetchProfile(
	name = "employee.projects",
	fetchOverrides = {
		@FetchProfile.FetchOverride(
			entity = Employee.class,
			association = "projects",
			mode = FetchMode.JOIN
		)
	}
)
class Employee {

	@Id
	private Long id;
	@NaturalId
	private String username;
	private String password;
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
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}

@Entity(name = "Project5")
@Table(name = "Projects5")
class Project {

	@Id
	private Long id;
	@ManyToMany
	private List<Employee> employees = new ArrayList<>();
	private String projectName;

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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}