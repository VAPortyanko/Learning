package by.pva.hibernate.part01.fetching.static_fetching.fetch_anotation;

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

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestHibSpecificFetchAnnotataionSelectAndSubselectMode extends BaseTest{
	
	public static void main(String[] args) {
	
		Map<String, String> properties = Collections.singletonMap("hibernate.format_sql", "false");
		buildEntityManagerFactory(properties);
		
		doInJPA(entityManager -> {
			
			Query query = entityManager.createQuery("delete from Employee9");
			query.executeUpdate();
			Query query2 = entityManager.createQuery("delete from Department7");
			query2.executeUpdate();
			
			Department department1 = new Department();
			department1.setId(1L);
			Department department2 = new Department();
			department2.setId(2L);
			Department department3 = new Department();
			department3.setId(3L);
			
			Employee employee1 = new Employee();
			Employee employee2 = new Employee();
			Employee employee3 = new Employee();
			Employee employee4 = new Employee();
			Employee employee5 = new Employee();
			Employee employee6 = new Employee();
			
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

			List<Department> departments = entityManager.createQuery("select d from Department7 d", Department.class ).getResultList();
			System.out.println("Total departments = " + departments.size());
			for(Department dep:departments) {
				dep.getEmployees().forEach(System.out::println);
			}

		});
		
		entityManagerFactory.close();
		
	}
}

@Entity(name = "Department7")
@Table(name = "Departments7")
class Department {

	@Id
	private Long id;
	@OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT) // Lead to 3 addition selects.
	// @Fetch(FetchMode.SUBSELECT) // Lead to 1 addition select.
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

// SELECT -    The association is going to be fetched lazily using a secondary select for each individual entity,
//             collection, or join load. It’s equivalent to JPA FetchType.LAZY fetching strategy.
// SUBSELECT - Available for collections only. When accessing a non-initialized collection, 
//             this fetch mode will trigger loading all elements of all collections of the same role for all owners 
//             associated with the persistence context using a single secondary select.

