package by.pva.hibernate.introduce.annotations;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import by.pva.hibernate.introduce.entities.Event2;

public class TestAnnotations {
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = null;

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernateUsingAnnotations.cfg.xml") // configures settings
																									// from
																									// hibernateUsingAnnotations.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}

		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(new Event2("Our very first event!", new Date()));
		session.save(new Event2("A follow up event", new Date()));
		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Event2> result = session.createQuery("from Event2").list();
		for (Event2 event : (List<Event2>) result) {
			System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
		}
		session.getTransaction().commit();

		if (sessionFactory != null) {
			sessionFactory.close();
		}

	}
}
