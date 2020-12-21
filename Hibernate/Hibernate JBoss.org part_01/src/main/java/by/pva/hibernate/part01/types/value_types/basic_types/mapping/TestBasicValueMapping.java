package by.pva.hibernate.part01.types.value_types.basic_types.mapping;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestBasicValueMapping extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			BasicTypesAggregator bta = new BasicTypesAggregator();
			bta.setName("Name");

			entityManager.persist(bta);
		});

		entityManagerFactory.close();

	}
}

// @Basic is annotation by default.
// FetchType.LAZY - for large objects.

@Entity(name = "Basic_types")
class BasicTypesAggregator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Basic(optional = false, fetch = FetchType.LAZY) // Play with "optional" parameter and null/not null field in the
														// database.
	@org.hibernate.annotations.Type(type = "org.hibernate.type.MaterializedClobType") // An explicit specified hibernate
																						// type.
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}