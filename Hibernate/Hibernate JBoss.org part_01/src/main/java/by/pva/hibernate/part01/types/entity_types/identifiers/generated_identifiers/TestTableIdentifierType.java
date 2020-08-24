package by.pva.hibernate.part01.types.entity_types.identifiers.generated_identifiers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.TableGenerator;

public class TestTableIdentifierType {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		Product6 product61 = new Product6();
		product61.setName("Name1");
		Product6 product62 = new Product6();
		product62.setName("Name2");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(product61);
		entityManager.persist(product62);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "Products6")
class Product6 {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, 
	                generator = "table-generator")
	@TableGenerator(name = "table-generator",
	                table = "table_identifier",
	                pkColumnName = "table_name",
	                valueColumnName = "seq_val",
	                allocationSize = 3) // https://vladmihalcea.com/hibernate-hidden-gem-the-pooled-lo-optimizer/
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
