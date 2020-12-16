package by.pva.hibernate.part01.fetching.static_fetching.batch_fetching;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.NaturalId;

public class TestBatchFetching {

	public static void main(String[] args) {

		Map<String, String> properties = Collections.singletonMap("hibernate.format_sql", "true");
		
		doInHibernateWithDefaultPersistanceUnit(entityManager -> {

			Query query = entityManager.createQuery("delete from Employee8");
			query.executeUpdate();
			Query query2 = entityManager.createQuery("delete from Department6");
			query2.executeUpdate();
			
			Department department = new Department();
			department.setId(1l);
			
			Employee employee;
			List<Employee> employees = new ArrayList<>();
			for (long i=1; i<26; i++) {
				employee = new Employee();
				employee.setId(i);
				employee.setDepartment(department);
				employee.setName("employee" + i);
				
				employees.add(employee);
			}
			
			department.setEmployees(employees);
		
			entityManager.persist(department);
			
			entityManager.flush();
			entityManager.clear();

			List<Department> departments = entityManager.createQuery(
					"select d " +
					"from Department6 d " +
					"inner join d.employees e " +
					"where e.name like 'employee%'", Department.class)
				    .getResultList();
			System.out.println(departments.size());
			for (Department dep : departments ) {
				System.out.printf("\nDepartment %d has {} employees", dep.getId(), dep.getEmployees().size());
			}
			
		}, properties);	

	}
}

@Entity(name = "Department6")
@Table(name = "Departments6")
class Department {

	@Id
	private Long id;
	@OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
	@BatchSize(size = 5)
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

@Entity(name = "Employee8")
@Table(name = "Employees8")
class Employee {

	@Id
	private Long id;
	@NaturalId
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	private Department department;
	
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

}