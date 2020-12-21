package by.pva.hibernate.part01.types.value_types.basic_types.custom_types;

import java.util.BitSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.TypeDef;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestBitSetType extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Product product = new Product();
			BitSet bset = new BitSet();
			bset.set(250);
			product.setBitSet(bset);

			entityManager.persist(product);
		});
		
		entityManagerFactory.close();

	}
}

@Entity(name = "Products")
@TypeDef(name = "bitset", defaultForType = BitSet.class, typeClass = BitSetType.class)
// or you can specify the org.hibernate.annotations.Type with a FQN for the corresponding field (see *1)
class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// @Type( type =
	// "by.pva.hibernate.part01.types.value_types.basic_types.custom_types.BitSetType"
	// ) (*1) // In addition we can register our new basic type and specify only its
	// name.
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
