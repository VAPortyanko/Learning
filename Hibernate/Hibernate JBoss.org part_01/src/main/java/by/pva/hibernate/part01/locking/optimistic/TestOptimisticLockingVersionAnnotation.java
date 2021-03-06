package by.pva.hibernate.part01.locking.optimistic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestOptimisticLockingVersionAnnotation extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person person = new Person();
			person.setName("Vasia");
			entityManager.persist(person); // Version value is 0.

			person.setName("Vasia Pupkin");

			entityManager.flush(); // Version value is 1.

			person.setName("Vasia Pupkin II");
			// person.setVersion(10L); - this will be ignored
			// (https://stackoverflow.com/questions/30881071/optimistic-locking-not-throwing-exception-when-manually-setting-version-field).

		});
		
		entityManagerFactory.close();

	}
}

@Entity(name = "Person36")
@Table(name = "Persons36")
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "`name`")
	private String name;
	@Version
	// Other supported types are Timestamp, Instant, int, Integer, short, Short,
	// long, Long.
	private long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", version=" + version + "]";
	}

}
