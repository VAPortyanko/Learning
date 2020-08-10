package by.pva.hibernate.part01.types.value_types.basic_types.mapping.generatedValues;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

public class TestValueGenerationType {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		Event2 event2 = new Event2();
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(event2);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

@Entity(name = "events2")
class Event2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "ts_gen_by_db")
	@FunctionCreationTimestampByDataBase
	private Date timestampGeneratedByDatabase;
	@Column(name = "ts_gen_in_memory")
	@FunctionCreationTimestampInMemory
	private Date timestampGeneratedInMemory;
}
