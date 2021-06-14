package by.pva.hibernate.part01.fetching.dynamic_fetching.via_entity_graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedAttributeNode;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestFetchingViaEntityGraph extends BaseTest{

	public static void main(String[] args) {

		Map<String, String> properties = Collections.singletonMap("hibernate.format_sql", "true");
		buildEntityManagerFactory(properties);
		
		doInJPA(entityManager -> {

			Query query1 = entityManager.createQuery("Delete from Employee5");
			Query query2 = entityManager.createQuery("Delete from Project3");
			Query query3 = entityManager.createQuery("Delete from Department4");

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
			project.setProjectName("Project");
			project.getEmployees().add(employee);

			entityManager.persist(employee);
			entityManager.persist(department);
			entityManager.persist(project);

			entityManager.flush();
			entityManager.clear();
			
			// JPA 2.1 introduced entity graph so the application developer has more control over fetch plans. It has two modes to choose from:
			// 1) Fetch graph - In this case, all attributes specified in the entity graph will be treated as FetchType.EAGER,
			//    and all attributes not specified will ALWAYS be treated as FetchType.LAZY.
			// 2) Load graph - In this case, all attributes specified in the entity graph will be treated as FetchType.EAGER,
			//  but attributes not specified use their static mapping specification.
			@SuppressWarnings("unused")
			Employee employee2 = entityManager.find(Employee.class, 1L, Collections.singletonMap("javax.persistence.fetchgraph",        // Switch to javax.persistence.loadgraph and see result.
					                                                                entityManager.getEntityGraph( "employee.projects" )
				)
			);
		});
		
		entityManagerFactory.close();
	}
}

@Entity(name = "Department4")
@Table(name = "Departments4")
class Department {

	@Id
	private Long id;
	@OneToMany(mappedBy = "department")
	private List<Employee> employees = new ArrayList<>();
	private String departmentName;

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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}

@Entity(name = "Employee5")
@Table(name = "Employees5")
@NamedEntityGraph(name = "employee.projects", 
                  attributeNodes = @NamedAttributeNode("projects")
)
class Employee {

	@Id
	private Long id;
	@NaturalId
	private String username;
	private String password;
	private int accessLevel;
	@ManyToOne(fetch = FetchType.EAGER)
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

@Entity(name = "Project3")
@Table(name = "Projects3")
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