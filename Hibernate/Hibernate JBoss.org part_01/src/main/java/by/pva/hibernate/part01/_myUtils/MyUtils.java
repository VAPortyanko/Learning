package by.pva.hibernate.part01._myUtils;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyUtils {

	public static void doInHibernateWithDefaultPersistanceUnit(Consumer<EntityManager> code) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		code.accept(entityManager);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
	}
}
