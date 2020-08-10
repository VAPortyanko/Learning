package by.pva.hibernate.part01.Wtest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Template {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		//Phone phone = new Phone();
		//phone.setNumber("+375297777777");
		//phone.setType(PhoneType.MOBILE);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		//entityManager.persist(phone);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}