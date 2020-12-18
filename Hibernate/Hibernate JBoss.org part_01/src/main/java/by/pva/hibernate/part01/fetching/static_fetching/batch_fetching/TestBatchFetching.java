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
import org.jboss.logging.Logger;

public class TestBatchFetching {

	private final static Logger log = Logger.getLogger("org.hibernate.SQL");
	
	public static void main(String[] args) {

		Map<String, String> properties = null;
		
		doInHibernateWithDefaultPersistanceUnit(entityManager -> {

			Query query = entityManager.createQuery("delete from Employee8");
			query.executeUpdate();
			Query query2 = entityManager.createQuery("delete from Department6");
			query2.executeUpdate();
			
			Department department = new Department();
			department.setId(1l);
			Department department2 = new Department();
			department2.setId(2l);
			Department department3 = new Department();
			department3.setId(3l);
			Department department4 = new Department();
			department4.setId(4l);
			
			Employee employee;
			List<Employee> employees = new ArrayList<>();
			for (long i=1; i<11; i++) {
				employee = new Employee();
				employee.setId(i);
				employee.setDepartment(department);
				employee.setName("employee" + i);
				
				employees.add(employee);
			}
			
			List<Employee> employees2 = new ArrayList<>();
			for (long i=11; i<21; i++) {
				employee = new Employee();
				employee.setId(i);
				employee.setDepartment(department2);
				employee.setName("employee" + i);
				
				employees2.add(employee);
			}
			
			List<Employee> employees3 = new ArrayList<>();
			for (long i=21; i<31; i++) {
				employee = new Employee();
				employee.setId(i);
				employee.setDepartment(department3);
				employee.setName("employee" + i);
				
				employees3.add(employee);
			}
			
			List<Employee> employees4 = new ArrayList<>();
			for (long i=31; i<41; i++) {
				employee = new Employee();
				employee.setId(i);
				employee.setDepartment(department4);
				employee.setName("employee" + i);
				
				employees4.add(employee);
			}
			
			department.setEmployees(employees);
			department2.setEmployees(employees2);
			department3.setEmployees(employees3);
			department4.setEmployees(employees4);
		
			entityManager.persist(department);
			entityManager.persist(department2);
			entityManager.persist(department3);
			entityManager.persist(department4);
			
			entityManager.flush();
			entityManager.clear();

			List<Department> departments = entityManager.createQuery(
					"select distinct d " +
					"from Department6 d " +
					"inner join d.employees e " +
					"where e.name like 'employee%'", Department.class)
				    .getResultList();
			log.info("Total departments: " + departments.size());
			for (Department dep : departments ) {
				log.info("\nDepartment " + dep.getId() + " has {" + dep.getEmployees().size() + "} employees");
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
	@BatchSize(size = 2)
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
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

}