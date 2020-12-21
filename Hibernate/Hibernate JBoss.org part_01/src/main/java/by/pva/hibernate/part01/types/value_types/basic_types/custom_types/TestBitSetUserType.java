package by.pva.hibernate.part01.types.value_types.basic_types.custom_types;

import java.util.BitSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestBitSetUserType extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Product2 product2 = new Product2();
			BitSet bset = new BitSet();
			bset.set(250);
			product2.setBitSet(bset);

			entityManager.persist(product2);
		});
		entityManagerFactory.close();

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
