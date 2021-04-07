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
import javax.persistence.NamedSubgraph;
import javax.persistence.NamedAttributeNode;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestFetchingViaEntitygraphAndSubgraph extends BaseTest{

	public static void main(String[] args) {

		Map<String, String> properties = Collections.singletonMap("hibernate.format_sql", "true");
		buildEntityManagerFactory(properties);
		
		doInJPA(entityManager -> {

			Query query1 = entityManager.createQuery("Delete from Employee6");
			Query query2 = entityManager.createQuery("Delete from Project4");
			Query query3 = entityManager.createQuery("Delete from Department5");

			query1.executeUpdate();
			query2.executeUpdate();
			query3.executeUpdate();

			Employee6 employee = new Employee6();
			Department5 department = new Department5();
			Project4 project = new Project4();

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
			
			@SuppressWarnings("unused")
			Project4 project2 = entityManager.find(Project4.class, 1L, Collections.singletonMap(
						"javax.persistence.fetchgraph",
						entityManager.getEntityGraph( "project.employees" )
					)
			);
		});
		entityManagerFactory.close();
	}
}

@Entity(name = "Department5")
@Table(name = "Departments5")
class Department5 {

	@Id
	private Long id;
	@OneToMany(mappedBy = "department")
	private List<Employee6> employees = new ArrayList<>();
	private String departmentName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Employee6> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee6> employees) {
		this.employees = employees;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}

@Entity(name = "Employee6")
@Table(name = "Employees6")
class Employee6 {

	@Id
	private Long id;
	@NaturalId
	private String username;
	private String password;
	private int accessLevel;
	@ManyToOne(fetch = FetchType.LAZY)
	private Department5 department;
	@ManyToMany(mappedBy = "employees")
	private List<Project4> projects = new ArrayList<>();

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

	public Department5 getDepartment() {
		return department;
	}

	public void setDepartment(Department5 department) {
		this.department = department;
	}

	public List<Project4> getProjects() {
		return projects;
	}

	public void setProjects(List<Project4> projects) {
		this.projects = projects;
	}

}

@Entity(name = "Project4")
@Table(name = "Projects4")
@NamedEntityGraph(name = "project.employees",
	attributeNodes = @NamedAttributeNode(
		value = "employees",
		subgraph = "project.employees.department"
	),
	subgraphs = @NamedSubgraph(
		name = "project.employees.department",
		attributeNodes = @NamedAttributeNode( "department" )
	)
)
class Project4 {

	@Id
	private Long id;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Employee6> employees = new ArrayList<>();
	private String projectName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Employee6> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee6> employees) {
		this.employees = employees;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}