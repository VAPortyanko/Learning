package by.pva.hibernate.part01.types.entity_types.associations.one_to_one;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;

public class TestOneToOneUnidirectional {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		PhoneDetails phoneDetails = new PhoneDetails();
		phoneDetails.setProvider("Provider");
		phoneDetails.setTechnology("Technology");
		
		Phone phone = new Phone();
		phone.setNumber("+375297777777");
		phone.setDetails(phoneDetails);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(phone);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "Phone5")
@Table(name = "Phones5")
class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "`number`")
	private String number;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "details_id")
	private PhoneDetails details;

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

	public PhoneDetails getDetails() {
		return details;
	}

	public void setDetails(PhoneDetails details) {
		this.details = details;
	}

}

@Entity(name = "PhoneDetail2")
@Table(name = "PhoneDetails2")
class PhoneDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String provider;
	private String technology;

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

}

// CREATE TABLE Phone (
//     id BIGINT NOT NULL ,
//     number VARCHAR(255) ,
//     details_id BIGINT ,
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE PhoneDetails (
//     id BIGINT NOT NULL ,
//     provider VARCHAR(255) ,
//     technology VARCHAR(255) ,
//     PRIMARY KEY ( id )
// )
//
// ALTER TABLE Phone
// ADD CONSTRAINT FKnoj7cj83ppfqbnvqqa5kolub7
// FOREIGN KEY (details_id) REFERENCES PhoneDetails
