package by.pva.hibernate.part01.types.entity_types.identifiers.generated_identifiers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestSequenceIdentifierType extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Product5 product5 = new Product5();
			product5.setName("Name");

			entityManager.persist(product5);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Products5")
class Product5 {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
	@SequenceGenerator(name = "sequence-generator", sequenceName = "product5_sequence", allocationSize = 1)
	private Long id;
	@Column(name = "product_name")
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
