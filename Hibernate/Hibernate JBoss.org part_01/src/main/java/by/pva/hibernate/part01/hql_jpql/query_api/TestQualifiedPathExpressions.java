package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Call;

public class TestQualifiedPathExpressions extends BaseTest {

	public static void main(String[] args) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			// Select all the calls (the map value) for a given Phone.
			List<Call> calls1 = entityManager.createQuery(
				"select ch " +
				"from Phone27 ph " +
				"join ph.callHistory ch " +
				"where ph.id = :id", Call.class)
			.setParameter("id", 1L)
			.getResultList();
			
			// Same as above (A map reference is a reference to the value of that map).
			List<Call> calls2 = entityManager.createQuery(
				"select value(ch) " +
				"from Phone27 ph " +
				"join ph.callHistory ch " +
				"where ph.id = :id", Call.class )
			.setParameter("id", 1L)
			.getResultList();

			calls1.stream().forEach(System.out::println);
			calls2.stream().forEach(System.out::println);
			
			// Select all the Call timestamps (the map key) for a given Phone.
			List<Date> timestamps1 = entityManager.createQuery(
				"select key(ch) " +
				"from Phone27 ph " +
				"join ph.callHistory ch " +
				"where ph.id = :id ", Date.class)
			.setParameter( "id", 1L)
			.getResultList();
			
			timestamps1.stream().forEach(System.out::println);

// ToDo: Fix code below ...			
			// Select all the Call and their timestamps (the 'Map.Entry') for a given Phone.
//			entityManager.createQuery(
//				"select entry(ch) " +
//				"from Phone27 ph " +
//				"join ph.callHistory ch " +
//				"where ph.id = :id")
//			.setParameter("id", 1L)
//			.getResultList();
//
//          callHistory.stream().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
			
			Long duration = entityManager.createQuery(
					"select sum(ch.duration) " +
					"from Person44 pr " +
					"join pr.phones ph " +
					"join ph.callHistory ch " +
					"where pr.id = :id " +
					"  and index(ph) = :phoneIndex", Long.class)
				.setParameter("id", 1L)
				.setParameter("phoneIndex", 0)
				.getSingleResult();
			
			System.out.println(duration);
			
		});

		entityManagerFactory.close();
	}
	
}
