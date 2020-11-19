package by.pva.hibernate.part01.immutability;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

public class TestEntityImmutability {
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
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
		event2.setMessage("New message");                      // This change will not store to database.
		System.out.println(event2);
		
		entityManager.flush();
		entityManager.clear();
		
		Event event3 = entityManager.find(Event.class, 1L);
		System.out.println(event3);
		
		entityManager.getTransaction().commit();
		entityManager.close();
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

