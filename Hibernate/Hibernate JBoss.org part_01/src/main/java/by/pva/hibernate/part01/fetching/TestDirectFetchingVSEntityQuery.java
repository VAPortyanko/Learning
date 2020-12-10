package by.pva.hibernate.part01.fetching;

import static by.pva.hibernate.part01._myUtils.MyUtils.doInHibernateWithDefaultPersistanceUnit;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.jboss.logging.Logger;

public class TestDirectFetchingVSEntityQuery {

	private final static Logger log = Logger.getLogger("org.hibernate.SQL");
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		doInHibernateWithDefaultPersistanceUnit(entityManager -> {

			Query query = entityManager.createQuery("delete from Employee2");
			query.executeUpdate();
			Query query2 = entityManager.createQuery("delete from Department");
			query2.executeUpdate();

			Department department = new Department();
			department.setId(1L);
			department.setName("Department");

			Employee employee = new Employee();
			employee.setId(1L);
			employee.setDepartment(department);
			employee.setUsername("User");

			entityManager.persist(employee);

			entityManager.flush();
			entityManager.clear();
			
			log.info("Entitymanager.find() method produces one sql select (Eager fetching is used).");
			
			// Entitymanager.find() method produces one sql select (Eager fetching is used).
			Employee employee2 = entityManager.find(Employee.class, 1L);
			
			entityManager.flush();
			entityManager.clear();
			
			log.info("EntityManager.createQuery() method produces the additional sql select (Eager fetching is used\");");
			
			// EntityManager.createQuery() method produces the additional sql select (Eager fetching is used");
			Employee employee3 = entityManager.createQuery(
					"select e " +
					"from Employee2 e " +
					"where e.id = :id", Employee.class)
			.setParameter( "id", 1L )
			.getSingleResult();
			
		});
	}
}

@Entity(name = "Department")
@Table(name = "Departments")
class Department {

	@Id
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

@Entity(name = "Employee2")
@Table(name = "Employees2")
class Employee {

	@Id
	private Long id;
	@NaturalId
	private String username;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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
}
