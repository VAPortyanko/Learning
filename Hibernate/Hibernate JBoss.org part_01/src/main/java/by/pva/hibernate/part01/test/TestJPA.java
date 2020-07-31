package by.pva.hibernate.part01.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SuppressWarnings("unused")
public class TestJPA {
	public static void main(String[] args) throws MalformedURLException {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("by.pva.hibernate.part01.test");

		Contact.Name name = new Contact.Name();
		name.setFirst("Vasia");
		name.setLast("Pupkin");
		name.setMiddle("Grigorievich");
		
		Contact contact = new Contact();
		contact.setId(3);
		contact.setName(name);
		contact.setNotes("Notes");
		contact.setStarred(false);
		contact.setWebsite(new URL("https://stackoverflow.com"));
		
		// create a couple of events...
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(contact);
		entityManager.getTransaction().commit();
		entityManager.close();

		// now lets pull events from the database and list them
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Contact> result = entityManager.createQuery("from Contact", Contact.class).getResultList();
		for (Contact event : result) {
			System.out.println("Event (" + event.getId() + ") : " + event.getName());
		}
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManagerFactory.close();

	}
}
