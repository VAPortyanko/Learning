package by.pva.hibernate.introduce.envers;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import by.pva.hibernate.introduce.entities.Event3;

public class TestEnvers {
	public static void main(String[] args) {
		
		// like discussed with regards to SessionFactory, an EntityManagerFactory is set
		// up once for an application
		// IMPORTANT: notice how the name here matches the name we gave the
		// persistence-unit in persistence.xml!
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.envers");

		// create a couple of events
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(new Event3("Our very first event!", new Date()));
		entityManager.persist(new Event3("A follow up event", new Date()));
		entityManager.getTransaction().commit();
		entityManager.close();

		// now lets pull events from the database and list them
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Event3> result = entityManager.createQuery("from Event3", Event3.class).getResultList();
		for (Event3 event : result) {
			System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
		}
		entityManager.getTransaction().commit();
		entityManager.close();

		// so far the code is the same as we have seen in previous tutorials. Now lets
		// leverage Envers...

		// first lets create some revisions
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Event3 myEvent = entityManager.find(Event3.class, 2L); // we are using the increment generator, so we know 2 is a
																// valid id
		myEvent.setDate(new Date());
		myEvent.setTitle(myEvent.getTitle() + " (rescheduled)");
		entityManager.getTransaction().commit();
		entityManager.close();

		// and then use an AuditReader to look back through history
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		myEvent = entityManager.find(Event3.class, 2L);
		System.out.println("A follow up event (rescheduled)".equals(myEvent.getTitle()));
		
		AuditReader reader = AuditReaderFactory.get(entityManager);
		Event3 firstRevision = reader.find(Event3.class, 2L, 1);
		System.out.println(firstRevision.getTitle().equals(myEvent.getTitle())); // false
		System.out.println(firstRevision.getDate().equals(myEvent.getDate()));// false
		
		Event3 secondRevision = reader.find(Event3.class, 2L, 2);
		System.out.println(secondRevision.getTitle().equals(myEvent.getTitle()));// true
		System.out.println(secondRevision.getDate().equals(myEvent.getDate()));// true
		
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManagerFactory.close();
	}
}
