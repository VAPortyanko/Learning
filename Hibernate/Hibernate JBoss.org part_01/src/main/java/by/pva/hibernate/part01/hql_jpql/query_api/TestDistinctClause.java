package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.QueryHints;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;

public class TestDistinctClause extends BaseTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		buildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			 // Using distinct there is good. 
			 List<String> lastNames = entityManager.createQuery(
						"select distinct p.name " +
						"from Person44 p", String.class)
					.getResultList();
			 
			 // 1) No distinct is used.
			 List<Person> persons1 = entityManager.createQuery(
						"select p " +
						"from Person44 p " +
						"left join fetch p.phones", Person.class)
					.getResultList();
			 
			 // 2) The DISTINCT SQL keyword is undesirable here 
			 //    since it does a redundant result set sorting
			 //    (https://in.relation.to/2016/08/04/introducing-distinct-pass-through-query-hint/)			 
			 List<Person> persons2 = entityManager.createQuery(
				"select distinct p " +
				"from Person44 p " +
				"left join fetch p.phones", Person.class)
			.getResultList();
			
			// Right way for previous example (distinct + setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)).
			List<Person> persons3 = entityManager.createQuery(
				"select distinct p " +
				"from Person44 p " +
				"left join fetch p.phones", Person.class)
			.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
			.getResultList();

			System.out.println("Without distinct keyword:");
			persons1.stream().forEachOrdered(e -> System.out.println("   " + e));
			System.out.println("With distinct keyword:");
			persons2.stream().forEachOrdered(e -> System.out.println("   " + e));
			System.out.println("With distinct keyword and the QueryHints.HINT_PASS_DISTINCT_THROUGH hibernate hint:");
			persons3.stream().forEachOrdered(e -> System.out.println("   " + e));
			
		});

		entityManagerFactory.close();
	}

}

// Read this - https://in.relation.to/2016/08/04/introducing-distinct-pass-through-query-hint/