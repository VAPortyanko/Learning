package by.pva.hibernate.part01.types.entity_types.associations.one_to_one;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestOneToOneBiderectional extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			PhoneDetails3_2 details = new PhoneDetails3_2();
			details.setProvider("Provider");
			details.setTechnology("Technology");

			Phone6_2 phone = new Phone6_2();
			phone.setNumber("+375(29) 457-85-74");
			phone.addDetails(details);

			entityManager.persist(phone);

			entityManager.flush();
			entityManager.clear();

//* commit from here
			// When using a bidirectional @OneToOne association, Hibernate enforces the
			// unique constraint upon fetching the child-side. If there are more than
			// one children associated with the same parent, Hibernate will throw a
			// org.hibernate.exception.ConstraintViolationException.
			// Continuing the previous example, when adding another PhoneDetails, Hibernate
			// validates the uniqueness constraint when reloading the Phone object.

			PhoneDetails3_2 otherDetails = new PhoneDetails3_2();
			otherDetails.setProvider("Provider2");
			otherDetails.setTechnology("Technologiy2");
			otherDetails.setPhone(phone);
			entityManager.persist(otherDetails);
			entityManager.flush();
			entityManager.clear();

			// throws javax.persistence.PersistenceException:
			// org.hibernate.HibernateException:
			// More than one row with the given identifier was found: 1
			Phone6_2 somePhone = entityManager.find(Phone6_2.class, phone.getId());
			System.out.println(somePhone.getNumber());
//* to here
		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Phone6")
@Table(name = "Phones6")
class Phone6_2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "`number`")
	private String number;
	@OneToOne(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private PhoneDetails3_2 details;

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

	public PhoneDetails3_2 getDetails() {
		return details;
	}

	public void setDetails(PhoneDetails3_2 details) {
		this.details = details;
	}

	public void addDetails(PhoneDetails3_2 details) {
		details.setPhone(this);
		this.details = details;
	}

	public void removeDetails() {
		if (details != null) {
			details.setPhone(null);
			this.details = null;
		}
	}
}

@Entity(name = "PhoneDetail3")
@Table(name = "PhoneDetails3")
class PhoneDetails3_2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String provider;
	private String technology;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phone_id")
	private Phone6_2 phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Phone6_2 getPhone() {
		return phone;
	}

	public void setPhone(Phone6_2 phone) {
		this.phone = phone;
	}

}

// CREATE TABLE Phone (
//     id BIGINT NOT NULL ,
//     number VARCHAR(255) ,
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE PhoneDetails (
//     id BIGINT NOT NULL ,
//     provider VARCHAR(255) ,
//     technology VARCHAR(255) ,
//     phone_id BIGINT ,
//     PRIMARY KEY ( id )
// )
//
// ALTER TABLE PhoneDetails
// ADD CONSTRAINT FKeotuev8ja8v0sdh29dynqj05p
// FOREIGN KEY (phone_id) REFERENCES Phone
