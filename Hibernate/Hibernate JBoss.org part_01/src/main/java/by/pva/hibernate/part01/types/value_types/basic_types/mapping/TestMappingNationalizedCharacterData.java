package by.pva.hibernate.part01.types.value_types.basic_types.mapping;

import java.io.IOException;
import java.io.Reader;
import java.sql.NClob;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.Nationalized;
import org.hibernate.engine.jdbc.NClobProxy;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestMappingNationalizedCharacterData extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			String warrantyRussian = "Гарантия на мой продукт";
			String warrantyChinese = "我的产品的保修";

			final Product4 product4 = new Product4();
			product4.setName("Mobile phone");
			product4.setWarranty(NClobProxy.generateProxy(warrantyRussian));
			product4.setWarranty2(warrantyChinese);

			entityManager.persist(product4);

			Product4 storedProduct4 = entityManager.find(Product4.class, 1);
			try (Reader reader = storedProduct4.getWarranty().getCharacterStream()) {
				int code;
				System.out.print("Warranty1: ");
				while ((code = reader.read()) > 0) {
					System.out.print((char) code);
				}
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println("\nWarranty2: " + storedProduct4.getWarranty2());

		});

		entityManagerFactory.close();
	}
}

@Entity(name = "Products4")
class Product4 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Lob
	@Nationalized
	// Clob also works, because NClob extends Clob.
	// The database type is still NCLOB either way and handled as such.
	private NClob warranty; // https://stackoverflow.com/questions/27147567/java-sql-sqlexception-incorrect-string-value-xf0-x9f-x98-x8f-for-column-tw
	@Nationalized
	private String warranty2;

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

	public NClob getWarranty() {
		return warranty;
	}

	public void setWarranty(NClob warranty) {
		this.warranty = warranty;
	}
}
