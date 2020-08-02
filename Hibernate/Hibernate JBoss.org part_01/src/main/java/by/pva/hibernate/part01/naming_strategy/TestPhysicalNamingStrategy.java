package by.pva.hibernate.part01.naming_strategy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestPhysicalNamingStrategy {
	public static void main(String[] args) {

		try {

			EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory("by.pva.hibernate.part01.namingstrategy.physical_naming_strategy");

			Customer customer = new Customer();
			customer.setEmailAddress("Some@mail.com");
			customer.setFirstName("FirstName");
			customer.setLastName("LastName");

			EntityManager entityManager = entityManagerFactory.createEntityManager();

			entityManager.getTransaction().begin();
			entityManager.persist(customer);
			entityManager.getTransaction().commit();
			entityManager.close();

			entityManagerFactory.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("End!");
	}
}
