package by.pva.hibernate.part01.locking.optimistic;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLocking;

import by.pva.hibernate.part01._myUtils.BaseTest;

import org.hibernate.annotations.OptimisticLockType;

public class TestOptimisticLocking_ALL extends BaseTest{

	public static void main(String[] args) {
 
		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("Delete from Person38");
			query.executeUpdate();
			
			Person38 person = new Person38();
			person.setId(1L);
			person.setName("Peter");
			person.setCity("Minsk");
			person.setCountry("Belarus");
			person.setCreatedOn(new Timestamp(System.currentTimeMillis()));

			entityManager.persist(person);
			
			entityManager.flush();
			entityManager.clear();
			
			Person38 person2 = entityManager.find(Person38.class, 1L);
			person2.setCity( "Washington D.C." ); // All fields will be used to identify a row for update.
	
		});
		
		entityManagerFactory.close();
	
	}
}

@Entity(name = "Person38")
@Table(name = "Persons38")
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
class Person38 {

	@Id
	private Long id;
	@Column(name = "`name`")
	private String name;
	private String country;
	private String city;
	@Column(name = "created_on")
	private Timestamp createdOn;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

}