package _by.pva.hibernate.part01.hql_jpql.domain_model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestCollectionMemberReference extends BaseTest {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			List<Phone> phones1 = entityManager.createQuery(
				"select ph " +
				"from Person44 pr " +
				"join pr.phones ph " +
				"join ph.calls c " +
				"where pr.address = :address " +
				"  and c.duration > :duration", Phone.class)
			.setParameter("address", "Earth1")
			.setParameter("duration", 20)
			.getResultList();
			
			phones1.stream().forEach(System.out::println);
			
			// alternate syntax
			List<Phone> phones2 = entityManager.unwrap(Session.class).createQuery(
				"select ph " +
				"from Person44 pr, " +
				"in (pr.phones) ph, " +
				"in (ph.calls) c " +
				"where pr.address = :address " +
				"  and c.duration > :duration")
			.setParameter("address", "Earth1")
			.setParameter("duration", 20)
			.list();

			phones2.stream().forEach(System.out::println);
			
		});	
		
		entityManagerFactory.close();
	}
}
