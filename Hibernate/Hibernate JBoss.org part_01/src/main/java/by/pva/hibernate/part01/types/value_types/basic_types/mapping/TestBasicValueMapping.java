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
			bta.setFirstName("firstName");
			bta.setLastName("lastName");

			entityManager.persist(bta);

		});

		entityManagerFactory.close();

	}
}

// @Basic is the default annotation.
@Entity(name = "Basic_types")
class BasicTypesAggregator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Basic(optional = false, fetch = FetchType.LAZY) // Hibernate ignores the "fetch" setting for basic types unless you are using bytecode enhancement.
	                                                 // (https://stackoverflow.com/questions/2112508/basicfetch-fetchtype-lazy-does-not-work).
	@org.hibernate.annotations.Type(type = "org.hibernate.type.MaterializedClobType") // An explicit specified hibernate type (LONGTEXT in mysql).
	private String firstName;
	private String lastName; // Will be persist as VARCHAR (255) in mysql;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}