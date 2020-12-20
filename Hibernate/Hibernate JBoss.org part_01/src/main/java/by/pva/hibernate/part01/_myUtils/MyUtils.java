package by.pva.hibernate.part01._myUtils;

import java.util.Map;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MyUtils {

	@SuppressWarnings("rawtypes")
	public static void doInHibernateWithDefaultPersistanceUnit(Consumer<EntityManager> code, Map properties) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration", properties);

		EntityManager entityManager = null;
		EntityTransaction txn = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			txn = entityManager.getTransaction();
			txn.begin();

			code.accept(entityManager);

			txn.commit();
		} catch (RuntimeException e) {
			if (txn != null && txn.isActive())
				txn.rollback();
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			entityManagerFactory.close();
		}
	}

	public static void doInHibernateWithDefaultPersistanceUnit(Consumer<EntityManager> code) {
		doInHibernateWithDefaultPersistanceUnit(code, null);
	}
}
