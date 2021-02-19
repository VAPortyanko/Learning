package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;

public class TestJoinFetch  extends BaseTest {

	public static void main(String[] args) {

		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			List<Person> persons = entityManager.createQuery(
				"select distinct pr " +
				"from Person44 pr " +
				"left join fetch pr.phones ", Person.class )
			.getResultList();
			
			persons.stream().forEachOrdered(System.out::println);
		});

		entityManagerFactory.close();
	}

}

// Fetch joins are not valid in sub-queries.
// Care should be taken when fetch joining a collection-valued association which is 
// in any way further restricted (the fetched collection will be restricted too).
// For this reason, it is usually considered best practice not to assign
// an identification variable to fetched joins except for the purpose of
// specifying nested fetch joins.
// Fetch joins should not be used in paged queries
// (e.g. setFirstResult() or setMaxResults()), nor should they be used with
// the scroll() or iterate() features.
