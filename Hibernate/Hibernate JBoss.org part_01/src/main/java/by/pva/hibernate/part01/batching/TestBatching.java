package by.pva.hibernate.part01.batching;

import java.util.Collections;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

//To enable batch loging you need to add "BatchingBatch" logger to your logging provider.
// org.hibernate.engine.jdbc.batch.internal.BatchingBatch
// Than you will be able to see in the logs something like:
// 2018-02-20 17:33:41.279 DEBUG 6280 --- [           main] o.h.e.jdbc.batch.internal.BatchingBatch  : Executing batch size: 20
// Unless you see this message,batching is not working.
// https://stackoverflow.com/questions/42002249/hibernate-how-to-verify-if-batch-insert-is-really-performed


// Since version 5.2, Hibernate allows overriding the global JDBC batch size
// given by the hibernate.jdbc.batch_size configuration property on a per Session basis:
// entityManager.unwrap(Session.class)
//              .setJdbcBatchSize(10);


// ! Uncomment the Log4j dependency in the pom.xml to see the log.
public class TestBatching extends BaseTest{

	public static void main(String[] args) {
		
		// In order to be optimal you have to define your jdbc.batch_size and your flushing parameter identical.
		final int BATCH_SIZE = 20;
		
		Map<String, String> properties = Collections.singletonMap("hibernate.jdbc.batch_size", String.valueOf(BATCH_SIZE));
		buildEntityManagerFactory(properties);
		
		doInJPA(entityManager -> {
			
			Query query = entityManager.createQuery("delete from Person40");
			query.executeUpdate();
			
			for (int i = 0; i < 100; i++ ) {
				if ( i > 0 && i % BATCH_SIZE == 0 ) {
					//flush a batch of inserts and release memory
					entityManager.flush();
					entityManager.clear();
					// The flush method forces Hibernate to save person entities to the database
					// but without batching it will be done in 20 conversations to the database.
					// With batching it will be done only one conversation to the database.
					// https://stackoverflow.com/questions/45670583/how-hibernate-batch-insert-works 
				}

				Person Person = new Person(i, String.format( "Person %d", i ));
				entityManager.persist(Person);
			}
			
		});

		entityManagerFactory.close();
		
	}
}

@Entity(name = "Person40")
@Table(name = "Persons40")
class Person {
	
	@Id
	private long id;
	private String name;

	public Person() {
	}
	public Person(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}