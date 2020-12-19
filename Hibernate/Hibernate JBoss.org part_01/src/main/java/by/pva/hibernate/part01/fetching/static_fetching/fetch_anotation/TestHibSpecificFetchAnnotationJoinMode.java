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

public class TestHibSpecificFetchAnnotationJoinMode {
	
	public static void main(String[] args) {
	
		Map<String, String> properties = Collections.singletonMap("hibernate.format_sql", "false");
		
		doInHibernateWithDefaultPersistanceUnit(entityManager -> {
			
			Query query = entityManager.createQuery("delete from Employee10");
			query.executeUpdate();
			Query query2 = entityManager.createQuery("delete from Department8");
			query2.executeUpdate();
			
			Department8 department1 = new Department8();
			department1.setId(1L);
			Department8 department2 = new Department8();
			department2.setId(2L);
			Department8 department3 = new Department8();
			department3.setId(3L);
			
			Employee10 employee1 = new Employee10();
			Employee10 employee2 = new Employee10();
			Employee10 employee3 = new Employee10();
			Employee10 employee4 = new Employee10();
			Employee10 employee5 = new Employee10();
			Employee10 employee6 = new Employee10();
			employee1.setId(1L);
			employee1.setDepartment(department1);
			employee1.setUsername("Employee1");
			employee2.setId(2L);
			employee2.setDepartment(department1);
			employee2.setUsername("Employee2");
			employee3.setId(3L);
			employee3.setDepartment(department2);
			employee3.setUsername("Employee3");
			employee4.setId(4L);
			employee4.setDepartment(department2);
			employee4.setUsername("Employee4");
			employee5.setId(5L);
			employee5.setDepartment(department3);
			employee5.setUsername("Employee5");
			employee6.setId(6L);
			employee6.setDepartment(department3);
			employee6.setUsername("Employee6");
			department1.getEmployees().add(employee1);
			department1.getEmployees().add(employee2);
			department2.getEmployees().add(employee3);
			department2.getEmployees().add(employee4);
			department3.getEmployees().add(employee5);
			department3.getEmployees().add(employee6);
						
			entityManager.persist(department1);
			entityManager.persist(department2);
			entityManager.persist(department3);
			
			entityManager.flush();
			entityManager.clear();

			@SuppressWarnings("unused")
			Department department = entityManager.find(Department.class, 1L); // There will be no addition selects.
			
//          The reason why we are not using a JPQL query to fetch multiple Department entities is because the FetchMode.JOIN strategy would be overridden by the query fetching directive.
//          To fetch multiple relationships with a JPQL query, the JOIN FETCH directive must be used instead.
//          Therefore, FetchMode.JOIN is useful for when entities are fetched directly, via their identifier or natural-id.
//          Also, the FetchMode.JOIN acts as a FetchType.EAGER strategy. Even if we mark the association as FetchType.LAZY, the FetchMode.JOIN will load the association eagerly.
			
//			List<Department8> departments = entityManager.createQuery("select d from Department8 d", Department8.class ).getResultList();
//			System.out.println("Total departments = " + departments.size());
//			for(Department8 dep:departments) {
//				dep.getEmployees().forEach(System.out::println);
//			}
			
		}, properties);
		
	}
}

@Entity(name = "Department8")
@Table(name = "Departments8")
class Department8 {

	@Id
	private Long id;
	@OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
	private List<Employee10> employees = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Employee10> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee10> employees) {
		this.employees = employees;
	}

}

@Entity(name = "Employee10")
@Table(name = "Employees10")
class Employee10 {

	@Id
	private Long id;
	@NaturalId
	private String username;
	@ManyToOne(fetch = FetchType.LAZY)
	private Department8 department;

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

	public Department8 getDepartment() {
		return department;
	}

	public void setDepartment(Department8 department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + "]";
	}

}