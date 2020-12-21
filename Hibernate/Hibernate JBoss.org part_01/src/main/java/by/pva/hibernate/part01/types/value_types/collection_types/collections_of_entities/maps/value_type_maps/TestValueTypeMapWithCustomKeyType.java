package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.maps.value_type_maps;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.MapKeyType;
import org.hibernate.annotations.Type;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestValueTypeMapWithCustomKeyType extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person28 person = new Person28();

			person.getCallRegister().put(new Date(), 101);
			person.getCallRegister().put(new Date(), 102);

			entityManager.persist(person);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person28")
@Table(name = "Persons28")
class Person28 {

	// The TimestampEpochType allows us to map a Unix timestamp since epoch to a
	// java.util.Date.
	// But, without the @MapKeyType Hibernate annotation, it would not be possible
	// to customize the Map key type.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ElementCollection
	@CollectionTable(name = "call_register", joinColumns = @JoinColumn(name = "person_id"))
	@MapKeyType(@Type(type = "by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.maps.value_type_maps.TimestampEpochType"))
	@MapKeyColumn(name = "call_timestamp_epoch")
	@Column(name = "phone_number")
	private Map<Date, Integer> callRegister = new HashMap<>();

	public Map<Date, Integer> getCallRegister() {
		return callRegister;
	}

	public void setCallRegister(Map<Date, Integer> callRegister) {
		this.callRegister = callRegister;
	}

	public Long getId() {
		return id;
	}
}

// Unix timestamp converter https://www.unixtimestamp.com/index.php