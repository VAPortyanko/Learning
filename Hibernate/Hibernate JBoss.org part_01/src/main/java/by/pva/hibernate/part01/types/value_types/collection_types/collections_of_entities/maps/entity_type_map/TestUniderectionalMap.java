package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.maps.entity_type_map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MapKeyTemporal;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

public class TestUniderectionalMap {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Person30 person = new Person30();

		person.getPhoneRegister().put(new Date(), new Phone20(PhoneType.LAND_LINE, "028-234-9876"));
		person.getPhoneRegister().put(new Date(), new Phone20(PhoneType.MOBILE, "072-122-9876"));

		entityManager.persist(person);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}

}

@Entity(name = "Person30")
@Table(name = "Persons30")
class Person30 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL,
			   orphanRemoval = true)
	@JoinTable(
		name = "phone_register2",
		joinColumns = @JoinColumn(name = "phone_id"),
		inverseJoinColumns = @JoinColumn(name = "person_id"))
	@MapKey(name = "since")
	@MapKeyTemporal(TemporalType.TIMESTAMP)
	private Map<Date, Phone20> phoneRegister = new HashMap<>();

	public Map<Date, Phone20> getPhoneRegister() {
		return phoneRegister;
	}
	public void setPhoneRegister(Map<Date, Phone20> phoneRegister) {
		this.phoneRegister = phoneRegister;
	}
	public Long getId() {
		return id;
	}
	public void addPhone(Phone20 phone) {
		phoneRegister.put( phone.getSince(), phone );
	}
}

@Entity(name = "Phone20")
@Table(name = "Phones20")
class Phone20 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private PhoneType type;
	@Column(name = "`number`")
	private String number;
	private Date since;

	public Phone20(PhoneType type, String number) {
		this.type = type;
		this.number = number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}

	public Long getId() {
		return id;
	}
}

// CREATE TABLE Person (
//    id BIGINT NOT NULL ,
//    PRIMARY KEY ( id )
// )
//
// CREATE TABLE Phone (
//    id BIGINT NOT NULL ,
//    number VARCHAR(255) ,
//    since TIMESTAMP ,
//    type INTEGER ,
//    PRIMARY KEY ( id )
// )
//
// CREATE TABLE phone_register (
//    phone_id BIGINT NOT NULL ,
//    person_id BIGINT NOT NULL ,
//    PRIMARY KEY ( phone_id, person_id )
// )
//
// ALTER TABLE phone_register
// ADD CONSTRAINT FKc3jajlx41lw6clbygbw8wm65w
// FOREIGN KEY (person_id) REFERENCES Phone
//
// ALTER TABLE phone_register
// ADD CONSTRAINT FK6npoomh1rp660o1b55py9ndw4
// FOREIGN KEY (phone_id) REFERENCES Person

