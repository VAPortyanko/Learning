package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.maps.value_type_maps;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import by.pva.hibernate.part01._myUtils.BaseTest;

// About map keys - https://www.baeldung.com/hibernate-persisting-maps

public class TestValueTypeMap extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person person = new Person();

			person.getPhoneRegister().put(new Phone(PhoneType.LAND_LINE, "028-234-9876"), new Date());
			person.getPhoneRegister().put(new Phone(PhoneType.MOBILE, "072-122-9876"), new Date());

			entityManager.persist(person);

		});

		entityManagerFactory.close();
	}
}

enum PhoneType {
	LAND_LINE, MOBILE
}

@Entity(name = "Person27")
@Table(name = "Persons27")
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@ElementCollection
	@CollectionTable(name = "phone_register")
	@Column(name = "since")
	private Map<Phone, Date> phoneRegister = new HashMap<>();

	public Long getId() {
		return id;
	}

	public Map<Phone, Date> getPhoneRegister() {
		return phoneRegister;
	}

	public void setPhoneRegister(Map<Phone, Date> phoneRegister) {
		this.phoneRegister = phoneRegister;
	}

}

@Embeddable
class Phone {

	private PhoneType type;
	@Column(name = "`number`")
	private String number;

	public Phone(PhoneType type, String number) {
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
}

//CREATE TABLE Person (
//    id BIGINT NOT NULL ,
//    PRIMARY KEY ( id )
//)
//
//CREATE TABLE phone_register (
//    Person_id BIGINT NOT NULL ,
//    since TIMESTAMP ,
//    number VARCHAR(255) NOT NULL ,
//    type INTEGER NOT NULL ,
//    PRIMARY KEY ( Person_id, number, type )
//)
//
//ALTER TABLE phone_register
//ADD CONSTRAINT FKrmcsa34hr68of2rq8qf526mlk
//FOREIGN KEY (Person_id) REFERENCES Person
