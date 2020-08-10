package by.pva.hibernate.part01.types.value_types.basic_types.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

public class TestEnumMapping {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		Phone phone = new Phone();
		phone.setNumber("+375297777777");
		phone.setType(PhoneType.MOBILE);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(phone);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

enum PhoneType {
	LAND_LINE, MOBILE;
}

@Entity(name = "Phones")
class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "phone_number")
	private String number;

	@Enumerated(EnumType.STRING) // Store as a String value while @Enumerated(EnumType.ORDINAL) store as a Number value. 
	@Column(name = "phone_type")
	private PhoneType type;

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

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}
}
