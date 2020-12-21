package by.pva.hibernate.part01.types.entity_types.identifiers.generated_identifiers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestTableIdentifierType extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Product6 product61 = new Product6();
			product61.setName("Name1");
			Product6 product62 = new Product6();
			product62.setName("Name2");

			entityManager.persist(product61);
			entityManager.persist(product62);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Products6")
class Product6 {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "table-generator")
	@TableGenerator(name = "table-generator", table = "table_identifier", pkColumnName = "table_name", valueColumnName = "seq_val", allocationSize = 3) // https://vladmihalcea.com/hibernate-hidden-gem-the-pooled-lo-optimizer/
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
