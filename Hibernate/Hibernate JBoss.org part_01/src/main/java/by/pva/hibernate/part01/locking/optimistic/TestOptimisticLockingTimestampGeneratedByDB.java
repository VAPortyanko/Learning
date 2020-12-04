package by.pva.hibernate.part01.locking.optimistic;

import static by.pva.hibernate.part01._myUtils.MyUtils.doInHibernateWithDefaultPersistanceUnit;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Source;
import org.hibernate.annotations.SourceType;

public class TestOptimisticLockingTimestampGeneratedByDB {

	public static void main(String[] args) {
		 
		doInHibernateWithDefaultPersistanceUnit(entityManager -> {
			
			Person37 person = new Person37();
			person.setFirstName("Vasia");
			person.setLastName("Pupkin");
			
			entityManager.persist(person);

			// it will lead to (MySQL db) ...
			// Hibernate: select now()
			// Hibernate: insert into Persons37 (firstName, lastName, version) values (?, ?, ?)
		});

	}

}

@Entity(name = "Person37")
@Table(name = "Persons37")
class Person37 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;

	@Version
	@Source(value = SourceType.DB)
	private Date version;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getVersion() {
		return version;
	}
	public void setVersion(Date version) {
		this.version = version;
	}
	
}

