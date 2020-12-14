package by.pva.hibernate.part01._myUtils;

import java.util.Map;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyUtils {

	@SuppressWarnings("rawtypes")
	public static void doInHibernateWithDefaultPersistanceUnit(Consumer<EntityManager> code, Map properties) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration", properties);
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		code.accept(entityManager);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
	}
	
	public static void doInHibernateWithDefaultPersistanceUnit(Consumer<EntityManager> code) {
		doInHibernateWithDefaultPersistanceUnit(code, null);
	}
}
