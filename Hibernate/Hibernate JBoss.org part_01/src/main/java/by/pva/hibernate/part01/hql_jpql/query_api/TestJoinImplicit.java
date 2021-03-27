package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Phone;

public class TestJoinImplicit extends BaseTest {

	public static void main(String[] args) {

		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			List<Phone> phones1 = entityManager.createQuery(
				"select ph " +
				"from Phone27 ph " +
				"where ph.person.address = :address ", Phone.class)
			.setParameter("address", "Earth1")
			.getResultList();
			
			// same as
			List<Phone> phones2 = entityManager.createQuery(
				"select ph " +
				"from Phone27 ph " +
				"join ph.person pr " +
				"where pr.address = :address ", Phone.class)
			.setParameter("address", "Earth2")
			.getResultList();
			
			phones1.stream().forEachOrdered(System.out::println);
			phones2.stream().forEachOrdered(System.out::println);
			
			// Reused implicit join
			List<Phone> phones3 = entityManager.createQuery(
				"select ph " +
				"from Phone27 ph " +
				"where ph.person.address = :address " +
				"  and ph.person.name = :name", Phone.class)
			.setParameter("address", "Earth1")
			.setParameter("name", "Name1")
			.getResultList();
			
			// same as
			List<Phone> phones4 = entityManager.createQuery(
				"select ph " +
				"from Phone27 ph " +
				"inner join ph.person pr " +
				"where pr.address = :address " +
				"  and pr.name = :name", Phone.class)
			.setParameter("address", "Earth2")
			.setParameter("name", "Name2")
			.getResultList();
			
			phones3.stream().forEachOrdered(System.out::println);
			phones4.stream().forEachOrdered(System.out::println);
			
		});

		entityManagerFactory.close();
	}

}
// If the attribute represents an entity association (non-collection) or 
// a component/embedded, that reference can be further navigated. 
// Basic values and collection-valued associations cannot be further navigated.

// Implicit joins are always treated as inner joins.
// Multiple references to the same implicit join always refer to the 
// ame logical and physical (SQL) join.
