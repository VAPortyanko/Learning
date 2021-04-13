package by.pva.hibernate.part01.types.value_types.basic_types.mapping.generated_values;

import java.util.Collections;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestValueGenerationType extends BaseTest {

	public static void main(String[] args) {

		buildEntityManagerFactory(Collections.singletonMap("hibernate.format_sql", "true"));
		
		doInJPA(entityManager -> {

			Event2 event2 = new Event2();
			entityManager.persist(event2);
			
		});

		entityManagerFactory.close();
	}
}

@Entity(name = "event2")
@Table(name = "events2")
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
