package by.pva.hibernate.part01.immutability;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestImmutableCollection extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Batch");
			query.executeUpdate();

			Batch batch = new Batch();
			batch.setId(1L);
			batch.setName("Change request");

			Event4 event1 = new Event4();
			event1.setId(1L);
			event1.setCreatedOn(new Date());
			event1.setMessage("Update Hibernate User Guide");

			Event4 event2 = new Event4();
			event2.setId(2L);
			event2.setCreatedOn(new Date());
			event2.setMessage("Update Hibernate Getting Started Guide");

			batch.getEvents().add(event1);
			batch.getEvents().add(event2);

			entityManager.persist(batch);

			entityManager.flush();
			entityManager.clear();

			@SuppressWarnings("unused")
			Batch batch2 = entityManager.find(Batch.class, 1L);
			// batch2.getEvents().clear(); // The org.hibernate.HibernateException: changed
			// an immutable collection instance:
			// [by.pva.hibernate.part01.immutability.Batch.events#1]
			// will be thrown here.

		});

		entityManagerFactory.close();
	}

}

@Entity(name = "Batch")
@Table(name = "Batches")
class Batch {

	@Id
	private Long id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	@Immutable
	private List<Event4> events = new ArrayList<>();

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

	public List<Event4> getEvents() {
		return events;
	}

	public void setEvents(List<Event4> events) {
		this.events = events;
	}

}

@Entity(name = "Event4")
@Table(name = "Events4")
@Immutable
class Event4 {

	@Id
	private Long id;
	private Date createdOn;
	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
