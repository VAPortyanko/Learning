package by.pva.hibernate.part01.Wtest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Template {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		// Phone phone = new Phone();
		// phone.setNumber("+375297777777");
		// phone.setType(PhoneType.MOBILE);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// entityManager.persist(phone);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

		Query query = entityManager.createQuery("delete from SystemUsers2");
		Query query2 = entityManager.createQuery("delete from Subsystems");
		query.executeUpdate();
		query2.executeUpdate();
		 
	}
}
// Examples - https://github.com/hibernate/hibernate-orm/blob/ceaeb81e3362ff187004ea3479b2afeeba5aa8a6/documentation/src/test/java/org/hibernate/userguide/mapping/identifier/IdClassGeneratedValueTest.java#L77