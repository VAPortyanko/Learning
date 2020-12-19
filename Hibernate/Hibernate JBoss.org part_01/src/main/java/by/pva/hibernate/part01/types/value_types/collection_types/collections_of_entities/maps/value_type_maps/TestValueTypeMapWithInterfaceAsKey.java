package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.maps.value_type_maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyColumn;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

public class TestValueTypeMapWithInterfaceAsKey {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Person29 person = new Person29();

		person.getCallRegister().put(new MobilePhone("01", "234", "567"), 101);
		person.getCallRegister().put(new MobilePhone("01", "234", "789"), 102);
				
		entityManager.persist(person);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

interface PhoneNumber {
	String get();
}

@Embeddable
class MobilePhone implements PhoneNumber {

	static PhoneNumber fromString(String phoneNumber) {
		String[] tokens = phoneNumber.split("-");
		if (tokens.length != 3) {
			throw new IllegalArgumentException("invalid phone number: " + phoneNumber);
		}
		int i = 0;
		return new MobilePhone(tokens[i++], tokens[i++], tokens[i]);
	}

	@SuppressWarnings("unused")
	private MobilePhone() {
	}

	public MobilePhone(String countryCode, String operatorCode, String subscriberCode) {
		this.countryCode = countryCode;
		this.operatorCode = operatorCode;
		this.subscriberCode = subscriberCode;
	}

	@Column(name = "country_code")
	private String countryCode;
	@Column(name = "operator_code")
	private String operatorCode;
	@Column(name = "subscriber_code")
	private String subscriberCode;

	@Override
	public String get() {
		return String.format("%s-%s-%s", countryCode, operatorCode, subscriberCode);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MobilePhone that = (MobilePhone) o;
		return Objects.equals(countryCode, that.countryCode) && Objects.equals(operatorCode, that.operatorCode)
				&& Objects.equals(subscriberCode, that.subscriberCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryCode, operatorCode, subscriberCode);
	}
}



@Entity(name = "Person29")
@Table(name = "Persons29")
class Person29 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ElementCollection
	@CollectionTable(
		name = "call_register2",
		joinColumns = @JoinColumn(name = "person_id")
	)
	@MapKeyColumn(name = "call_timestamp_epoch")
	@MapKeyClass(MobilePhone.class)
	@Column(name = "call_register")
	private Map<PhoneNumber, Integer> callRegister = new HashMap<>();

	public Map<PhoneNumber, Integer> getCallRegister() {
		return callRegister;
	}
	public void setCallRegister(Map<PhoneNumber, Integer> callRegister) {
		this.callRegister = callRegister;
	}
	public Long getId() {
		return id;
	}
}

// create table person (
//    id bigint not null,
//    primary key (id)
// )
//
// create table call_register (
//    person_id bigint not null,
//    call_register integer,
//    country_code varchar(255) not null,
//    operator_code varchar(255) not null,
//    subscriber_code varchar(255) not null,
//    primary key (person_id, country_code, operator_code, subscriber_code)
// )
//
// alter table call_register
//    add constraint FKqyj2at6ik010jqckeaw23jtv2
//    foreign key (person_id)
//    references person