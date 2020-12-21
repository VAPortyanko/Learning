package by.pva.hibernate.part01.fetching.static_fetching.lazycollection_annotation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestLazzyCollectionAnnotation extends BaseTest{

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Employee11");
			query.executeUpdate();
			Query query2 = entityManager.createQuery("delete from Department9");
			query2.executeUpdate();

			Department department = new Department();
			department.setId(1L);

			Employee employee1 = new Employee();
			Employee employee2 = new Employee();
			Employee employee3 = new Employee();
			employee1.setId(1L);
			employee1.setDepartment(department);
			employee1.setUsername("Employee1");
			employee2.setId(2L);
			employee2.setDepartment(department);
			employee2.setUsername("Employee2");
			employee3.setId(3L);
			employee3.setDepartment(department);
			employee3.setUsername("Employee3");
			
			department.getEmployees().add(employee1);
			department.getEmployees().add(employee2);
			department.getEmployees().add(employee3);

			entityManager.persist(department);

			entityManager.flush();
			entityManager.clear();

			department = entityManager.find(Department.class, 1L);   
			
			// Here we get an addition select for fetching collection size (without fetching the collection itself).
			int employeeCount = department.getEmployees().size();
			System.out.println("Total employees:" + employeeCount);
			// We will get 3 addition selects here.
			for(int i = 0; i < employeeCount; i++ ) { 
				System.out.printf( "Fetched employee: %s\n", department.getEmployees().get(i).getUsername());
			}
		});
		
		entityManagerFactory.close();

	}
}

@Entity(name = "Department9")
@Table(name = "Departments9")
class Department {

	@Id
	private Long id;
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	@OrderColumn(name = "order_id")
	@LazyCollection(LazyCollectionOption.EXTRA) // Using EXTRA Lazy Collections with Hibernate is a bad idea since it can lead to N+1 query issues and cause performance problems!
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

@Entity(name = "Employee11")
@Table(name = "Employees11")
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