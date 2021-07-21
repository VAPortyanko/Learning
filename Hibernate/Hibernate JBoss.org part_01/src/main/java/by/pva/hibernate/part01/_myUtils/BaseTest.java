package by.pva.hibernate.part01._myUtils;

import java.util.Map;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BaseTest {
	
	protected final static String DEFAULT_PERSISTENCE_UNIT = "by.pva.hibernate.part01.basicWithTableAutoGeneration"; 
	protected static EntityManagerFactory entityManagerFactory = null;
	
	protected static void buildDefaultEntityManagerFactory () {
		entityManagerFactory = Persistence.createEntityManagerFactory(DEFAULT_PERSISTENCE_UNIT);
	}
	
	protected static void buildEntityManagerFactory(Map<String, String> properties) {
		buildEntityManagerFactory(DEFAULT_PERSISTENCE_UNIT, properties);
	}
	
	protected static void buildEntityManagerFactory(String persistenceUnit, Map<String, String> properties) {
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit, properties);
	}
	
	protected static void doInJPA(Consumer<EntityManager> code) {

		if (entityManagerFactory == null){
			buildDefaultEntityManagerFactory();
		}
		
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
		}
	}
}