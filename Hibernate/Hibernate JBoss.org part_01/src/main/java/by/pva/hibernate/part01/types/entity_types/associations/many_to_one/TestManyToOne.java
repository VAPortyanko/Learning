package by.pva.hibernate.part01.types.entity_types.associations.many_to_one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;

public class TestManyToOne {

	public static void main(String[] args) {

			EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");
	
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Person person = new Person();
		person.setName("Person");
		entityManager.persist( person );
		
		Phone phone = new Phone( "123-456-7890" );
		phone.setPerson( person );
		entityManager.persist( phone );
		
		entityManager.flush();
		//phone.setPerson( null );
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "persons6")
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

@Entity
@Table(name = "Phones2")
class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "`number`")
	private String number;

	@ManyToOne
	@JoinColumn(name = "person_id", 
	            foreignKey = @ForeignKey(name = "PERSON_ID_FK"))
	private Person person;

	public Phone(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}

// This is the equivalent of this

// CREATE TABLE Person (
//     id BIGINT NOT NULL ,
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE Phone (
//     id BIGINT NOT NULL ,
//     number VARCHAR(255) ,
//     person_id BIGINT ,
//     PRIMARY KEY ( id )
// )
//
// ALTER TABLE Phone
// ADD CONSTRAINT PERSON_ID_FK
// FOREIGN KEY (person_id) REFERENCES Person



