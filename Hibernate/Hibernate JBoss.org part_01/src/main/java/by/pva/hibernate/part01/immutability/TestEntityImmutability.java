package by.pva.hibernate.part01.immutability;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestEntityImmutability extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Event3");
			query.executeUpdate();

			Event event = new Event();
			event.setId(1L);
			event.setCreatedOn(new Date());
			event.setMessage("Message");

			entityManager.persist(event);

			entityManager.flush();
			entityManager.clear();

			Event event2 = entityManager.find(Event.class, 1L);
			event2.setMessage("New message"); // This change will not store to database.
			System.out.println(event2);

			entityManager.flush();
			entityManager.clear();

			Event event3 = entityManager.find(Event.class, 1L);
			System.out.println(event3);

		});

		entityManagerFactory.close();
	}

}

@Entity(name = "Event3")
@Table(name = "Events3")
@Immutable
class Event {

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

	@Override
	public String toString() {
		return "Event [id=" + id + ", createdOn=" + createdOn + ", message=" + message + "]";
	}

}
