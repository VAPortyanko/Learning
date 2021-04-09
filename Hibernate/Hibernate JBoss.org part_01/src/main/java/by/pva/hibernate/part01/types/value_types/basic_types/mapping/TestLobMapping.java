package by.pva.hibernate.part01.types.value_types.basic_types.mapping;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.engine.jdbc.ClobProxy;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestLobMapping extends BaseTest{
	
	public static void main(String[] args){
		
		doInJPA(entityManager -> {

			final String WARRANTY = "My product warranty";
	
			Product3 product3 = new Product3();
			
			product3.setName("Mobile phone");
			product3.setWarranty(ClobProxy.generateProxy(WARRANTY));
			product3.setWarranty2(WARRANTY);
	
			entityManager.persist(product3);
			
			entityManager.flush();
			entityManager.clear();
			
			Product3 storedProduct3 = entityManager.find(Product3.class, 1);
			
			try (Reader reader = storedProduct3.getWarranty().getCharacterStream()) {
				int code;
				System.out.print("Warranty1: ");
				while ((code = reader.read()) > 0) {
					System.out.print((char) code);
				}
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println("\nWarranty2: " + storedProduct3.getWarranty2());
		});

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
