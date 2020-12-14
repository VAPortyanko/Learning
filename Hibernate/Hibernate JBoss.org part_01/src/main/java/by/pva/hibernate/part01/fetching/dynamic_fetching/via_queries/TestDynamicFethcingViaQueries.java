package by.pva.hibernate.part01.fetching.dynamic_fetching.via_queries;

import static by.pva.hibernate.part01._myUtils.MyUtils.doInHibernateWithDefaultPersistanceUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.annotations.NaturalId;
import org.jboss.logging.Logger;

public class TestDynamicFethcingViaQueries {

	private final static Logger log = Logger.getLogger("org.hibernate.SQL");
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		doInHibernateWithDefaultPersistanceUnit(entityManager -> {
			
			Query query1 = entityManager.createQuery("Delete from Employee4");
			Query query2 = entityManager.createQuery("Delete from Project2");
			Query query3 = entityManager.createQuery("Delete from Department3");
			
			query1.executeUpdate();
			query2.executeUpdate();
			query3.executeUpdate();
			
			Employee4 employee = new Employee4();
			Department3 department = new Department3();
			Project2 project = new Project2();
			
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
			
			log.info("\n\nIn this example we have an Employee and their Projects"
					+ "\nloaded in a single query shown both as an HQL query and"
					+ "\na JPA Criteria query. In both cases, this resolves to "
					+ "\nexactly one database query to get all that information.\n");

			Employee4 employee2 = entityManager.createQuery(
				"select e " +
				"from Employee4 e " +
				"left join fetch e.projects " +
				"where " +
				"	e.username = :username and " +
				"	e.password = :password",
				Employee4.class)
			.setParameter( "username", "user")
			.setParameter( "password", "password")
			.getSingleResult();
			
			entityManager.flush();
			entityManager.clear();
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee4> query = builder.createQuery(Employee4.class);
			Root<Employee4> root = query.from(Employee4.class);
			root.fetch("projects", JoinType.LEFT);
			query.select(root).where(
				builder.and(
					builder.equal(root.get("username"), "user"),
					builder.equal(root.get("password"), "password")
				)
			);
			Employee4 employee3 = entityManager.createQuery(query).getSingleResult();

		}, Collections.singletonMap("hibernate.format_sql", "true"));

	}

}

@Entity(name = "Department3")
@Table(name = "Departments3")
class Department3 {

	@Id
	private Long id;
	@OneToMany(mappedBy = "department")
	private List<Employee4> employees = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Employee4> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee4> employees) {
		this.employees = employees;
	}

}

@Entity(name = "Employee4")
@Table(name = "Employees4")
class Employee4 {

	@Id
	private Long id;
	@NaturalId
	private String username;
	private String password;
	private int accessLevel;
	@ManyToOne(fetch = FetchType.LAZY)
	private Department3 department;
	@ManyToMany(mappedBy = "employees")
	private List<Project2> projects = new ArrayList<>();
	
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
	public Department3 getDepartment() {
		return department;
	}
	public void setDepartment(Department3 department) {
		this.department = department;
	}
	public List<Project2> getProjects() {
		return projects;
	}
	public void setProjects(List<Project2> projects) {
		this.projects = projects;
	}

}

@Entity(name = "Project2")
@Table(name = "Projects2")
class Project2 {

	@Id
	private Long id;
	@ManyToMany
	private List<Employee4> employees = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Employee4> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee4> employees) {
		this.employees = employees;
	}

}