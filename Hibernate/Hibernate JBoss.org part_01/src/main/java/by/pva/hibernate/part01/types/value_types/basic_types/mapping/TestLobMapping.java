package by.pva.hibernate.part01.types.value_types.basic_types.mapping;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Persistence;

import org.hibernate.engine.jdbc.ClobProxy;

public class TestLobMapping {
	public static void main(String[] args) throws IOException, SQLException {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		String warranty = "My product warranty";

		final Product3 product3 = new Product3();
		product3.setName("Mobile phone");
		product3.setWarranty(ClobProxy.generateProxy(warranty));
		product3.setWarranty2(warranty);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(product3);
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Product3 storedProduct3 = entityManager.find(Product3.class, 1);
		try (Reader reader = storedProduct3.getWarranty().getCharacterStream()) {
			int code;
			System.out.print("Warranty1: ");
			while ((code = reader.read()) > 0) {
				System.out.print((char) code);
			}
		}
		System.out.println("\nWarranty2: " + storedProduct3.getWarranty2());
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManagerFactory.close();
	}
}

@Entity(name = "Products3")
class Product3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Lob
	private Clob warranty;  // JDBC LOB locator types.
	@Lob
	private String warranty2; // Type that materializing the LOB data.

	public String getWarranty2() {
		return warranty2;
	}

	public void setWarranty2(String warranty2) {
		this.warranty2 = warranty2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Clob getWarranty() {
		return warranty;
	}

	public void setWarranty(Clob warranty) {
		this.warranty = warranty;
	}
}
