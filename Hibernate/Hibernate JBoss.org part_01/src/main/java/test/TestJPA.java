package test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJPA {
	public static void main(String[] args) {
//
//		EntityManagerFactory entityManagerFactory;
//
//		// like discussed with regards to SessionFactory, an EntityManagerFactory is set
//		// up once for an application
//		// IMPORTANT: notice how the name here matches the name we gave the
//		// persistence-unit in persistence.xml!
//		entityManagerFactory = Persistence.createEntityManagerFactory("by.pva.hibernate.part01");
//
//		// create a couple of events...
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.persist(new Event2("Our very first event!", new Date()));
//		entityManager.persist(new Event2("A follow up event", new Date()));
//		entityManager.getTransaction().commit();
//		entityManager.close();
//
//		// now lets pull events from the database and list them
//		entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		List<Event2> result = entityManager.createQuery("from Event2", Event2.class).getResultList();
//		for (Event2 event : result) {
//			System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
//		}
//		entityManager.getTransaction().commit();
//		entityManager.close();
//
//		entityManagerFactory.close();

	}
}
