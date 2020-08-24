package by.pva.hibernate.part01.types.entity_types.identifiers.custom_generator;

// https://www.baeldung.com/hibernate-identifiers

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

public class TestCustomIdentifierGenerator {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		Product product = new Product();
		product.setName("Name");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(product);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

@Entity
@Table(name = "products7")
class Product {

	@Id
	@GeneratedValue(generator = "prod-generator")
	@GenericGenerator(name = "prod-generator",
	                  parameters = @Parameter(name = "prefix", value = "prod"), 
	                  strategy = "by.pva.hibernate.part01.types.entity_types.identifiers.custom_generator.MyGenerator")
	private String prodId;
	private String name;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

