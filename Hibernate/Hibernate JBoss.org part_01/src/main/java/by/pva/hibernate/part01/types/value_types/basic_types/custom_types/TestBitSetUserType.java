package by.pva.hibernate.part01.types.value_types.basic_types.custom_types;

import java.util.BitSet;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

import org.hibernate.annotations.Type;

public class TestBitSetUserType {
	public static void main(String[] args) {
		try {

			EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory("by.pva.hibernate.part01.basic");

			EntityManager entityManager = entityManagerFactory.createEntityManager();

			Product2 product2 = new Product2();
			BitSet bset = new BitSet();
			bset.set(250);
			product2.setBitSet(bset);

			entityManager.getTransaction().begin();
			entityManager.persist(product2);
			entityManager.getTransaction().commit();
			entityManager.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("End!");
	}
}

@Entity(name = "Products2")
class Product2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Type(type = "by.pva.hibernate.part01.types.value_types.basic_types.custom_types.BitSetUserType")
	private BitSet bitSet;

	public Integer getId() {
		return id;
	}

	public BitSet getBitSet() {
		return bitSet;
	}

	public void setBitSet(BitSet bitSet) {
		this.bitSet = bitSet;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
