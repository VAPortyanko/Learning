package by.pva.hibernate.part01.native_query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Phone;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestHandlingAssotiationsAndCollections extends BaseTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// JPA native query selecting entities with many-to-one association
			List<Phone> phones1 = entityManager.createNativeQuery(
				"SELECT id, phone_number, phone_type, person_id " +
				"FROM Phones27", Phone.class)
			.getResultList();
			
			// If the entity is mapped with a many-to-one or a child-side one-to-one to another entity, 
			// it is required to also return this when performing the native query, otherwise, 
			// a database-specific column not found error will occur.
			// phones1.get(0).getPerson(); Will throw the Exception if we omit ", person_id" in the select clause.
			
			phones1.forEach(System.out::println);
			
			// Hibernate native query selecting entities with many-to-one association
			List<Phone> phones2 = entityManager.unwrap(Session.class).createNativeQuery(
				"SELECT id, phone_number, phone_type, person_id " +
				"FROM Phones27")
			.addEntity(Phone.class)
			.list();

			phones2.forEach(System.out::println);
			System.out.println(phones2.get(0).getPerson()); // Addition select here!
			
			// Hibernate native query selecting entities with joined many-to-one association
			List<Object[]> tuples = entityManager.unwrap(Session.class).createNativeQuery(
				"SELECT * " +
				"FROM Phones27 ph " +
				"JOIN Persons44 pr ON ph.person_id = pr.id")
			.addEntity("phone", Phone.class )
			.addJoin("pr", "phone.person")
			.list();

			for(Object[] tuple : tuples) {
				Phone phone = (Phone) tuple[0];
				Person person = (Person) tuple[1];
				System.out.println(person.getName() + ": " + phone.getNumber());
			}

			// By default, when using the addJoin() method, the result set will contain both entities that are joined.
			// To construct the entity hierarchy, you need to use a ROOT_ENTITY or DISTINCT_ROOT_ENTITY ResultTransformer.
			//
			// Hibernate native query selecting entities with joined many-to-one association and ResultTransformer:
			@SuppressWarnings("deprecation")
			List<Person> persons1 = entityManager.unwrap(Session.class).createNativeQuery(
				"SELECT * " +
				"FROM Phones27 ph " +
				"JOIN Persons44 pr ON ph.person_id = pr.id" )
			.addEntity("phone", Phone.class)
			.addJoin("pr", "phone.person")
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) // Deprecated !!!
			.list();
			
			for(Person person : persons1) {
				System.out.println(person.getPhones());
			}
			
		});

		entityManagerFactory.close();
	}
	
}