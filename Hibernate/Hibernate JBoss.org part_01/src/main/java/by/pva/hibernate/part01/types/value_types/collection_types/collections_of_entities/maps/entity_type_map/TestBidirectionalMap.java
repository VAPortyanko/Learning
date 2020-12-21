package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.maps.entity_type_map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestBidirectionalMap extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person31 person = new Person31();

			person.getPhoneRegister().put(PhoneType.MOBILE, new Phone21(PhoneType.LAND_LINE, "028-234-9876"));
			person.getPhoneRegister().put(PhoneType.MOBILE, new Phone21(PhoneType.MOBILE, "072-122-9876"));

			entityManager.persist(person);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person31")
@Table(name = "Persons31")
class Person31 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	@MapKey(name = "type")
	@MapKeyEnumerated
	private Map<PhoneType, Phone21> phoneRegister = new HashMap<>();

	public Map<PhoneType, Phone21> getPhoneRegister() {
		return phoneRegister;
	}

	public void setPhoneRegister(Map<PhoneType, Phone21> phoneRegister) {
		this.phoneRegister = phoneRegister;
	}

	public Long getId() {
		return id;
	}

	public void addPhone(Phone21 phone) {
		phone.setPerson(this);
		phoneRegister.put(phone.getType(), phone);
	}
}

@Entity(name = "Phone21")
@Table(name = "Phones21")
class Phone21 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private PhoneType type;
	@Column(name = "`number`")
	private String number;
	private Date since;
	@ManyToOne
	private Person31 person;

	public Phone21(PhoneType type, String number) {
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

	public Person31 getPerson() {
		return person;
	}

	public void setPerson(Person31 person) {
		this.person = person;
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
//    person_id BIGINT ,
//    PRIMARY KEY ( id )
// )
//
// ALTER TABLE Phone
// ADD CONSTRAINT FKmw13yfsjypiiq0i1osdkaeqpg
// FOREIGN KEY (person_id) REFERENCES Person