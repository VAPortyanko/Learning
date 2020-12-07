package by.pva.hibernate.part01.locking.optimistic;

import static by.pva.hibernate.part01._myUtils.MyUtils.doInHibernateWithDefaultPersistanceUnit;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

public class TestOptimisticLocking_DIRTY {
	public static void main(String[] args) {
		
		doInHibernateWithDefaultPersistanceUnit(entityManager -> {

			Query query = entityManager.createQuery("Delete from Person39");
			query.executeUpdate();
			
			Person39 person = new Person39();
			person.setId(1L);
			person.setName("Peter");
			person.setCity("Minsk");
			person.setCountry("Belarus");
			person.setCreatedOn(new Timestamp(System.currentTimeMillis()));

			entityManager.persist(person);
			
			entityManager.flush();
			entityManager.clear();
			
			Person39 person2 = entityManager.find(Person39.class, 1L);
			person2.setCountry("USA");
			person2.setCity( "Washington D.C." ); // Only changed fields will be used to identify a row for update.
	
		});
		
	}
}

@Entity(name = "Person39")
@Table(name = "Persons39")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
class Person39 {

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