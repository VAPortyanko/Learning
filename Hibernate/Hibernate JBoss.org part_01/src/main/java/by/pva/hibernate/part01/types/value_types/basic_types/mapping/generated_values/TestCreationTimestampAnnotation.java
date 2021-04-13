package by.pva.hibernate.part01.types.value_types.basic_types.mapping.generated_values;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestCreationTimestampAnnotation extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Event event = new Event();
			entityManager.persist(event);
			
		});

		entityManagerFactory.close();
	}
}

@Entity(name = "Event")
@Table(name = "events")
class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "`timestamp`")
	@CreationTimestamp
	private Date timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
