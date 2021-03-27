package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import _by.pva.hibernate.part01.hql_jpql.domain_model.PhoneType;

/* Doc references: 
      1) Hibernate Tips: What’s the Difference between JOIN, LEFT JOIN and JOIN FETCH 
            - https://thorben-janssen.com/hibernate-tips-difference-join-left-join-fetch-join/.
      2) JPA Join Types 
            - https://www.baeldung.com/jpa-join-types .
*/ 
public class TestJoinExplicit extends BaseTest {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {

		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			// Explicit inner join examples.
			List<Person> persons1 = entityManager.createQuery(
				"select distinct pr " +
				"from Person44 pr " +
				"join pr.phones ph " +
				"where ph.type = :phoneType", Person.class)
			.setParameter("phoneType", PhoneType.MOBILE)
			.getResultList();
			// Same query but specifying join type as 'inner' explicitly.
			List<Person> persons2 = entityManager.createQuery(
				"select distinct pr " +
				"from Person44 pr " +
				"inner join pr.phones ph " +
				"where ph.type = :phoneType", Person.class)
			.setParameter("phoneType", PhoneType.MOBILE)
			.getResultList();
			
			// Explicit left (outer) join examples.
			List<Person> persons3 = entityManager.createQuery(
				"select distinct pr " +
				"from Person44 pr " +
				"left join pr.phones ph " +
				"where ph is null " +
				"   or ph.type = :phoneType", Person.class)
			.setParameter("phoneType", PhoneType.LAND_LINE)
			.getResultList();
			// Functionally the same query but using the 'left outer' phrase.
			List<Person> persons4 = entityManager.createQuery(
				"select distinct pr " +
				"from Person44 pr " +
				"left outer join pr.phones ph " +
				"where ph is null " +
				"   or ph.type = :phoneType", Person.class)
			.setParameter("phoneType", PhoneType.LAND_LINE)
			.getResultList();
			
			//  HQL WITH clause join example. 
			List<Object[]> personsAndPhones = entityManager.unwrap(Session.class).createQuery(
				"select pr.name, ph.number " +
				"from Person44 pr " +
				"left join pr.phones ph with ph.type = :phoneType ")
			.setParameter("phoneType", PhoneType.LAND_LINE)
			.list();
			/*
			     select
			        person0_.name as col_0_0_,
			        phones1_.phone_number as col_1_0_ 
			    from Persons44 person0_ 
			    	left outer join Phones27 phones1_ on person0_.id=phones1_.person_id 
			                                         	 and (phones1_.phone_type=?)
			*/
			List<Object[]> personsAndPhones2 = entityManager.unwrap(Session.class).createQuery(
					"select pr.name, ph.number " +
					"from Person44 pr " +
					"left join Phone27 ph with ph.type = :phoneType ")
				.setParameter("phoneType", PhoneType.LAND_LINE)
				.list();
			/*
			    select
			        person0_.name as col_0_0_,
			        phone1_.phone_number as col_1_0_ 
			    from Persons44 person0_ 
			        left outer join Phones27 phone1_ on (phone1_.phone_type=?)
			*/

			// The HQL-style WITH keyword is specific to Hibernate. JPQL defines the ON clause for this feature.
			// The important distinction is that in the generated SQL the conditions of the WITH/ON clause 
			// are made part of the ON clause in the generated SQL.
			List<Object[]> personsAndPhones3 = entityManager.createQuery(
				"select pr.name, ph.number " +
				"from Person44 pr " +
				"left join pr.phones ph on ph.type = :phoneType " )
			.setParameter( "phoneType", PhoneType.LAND_LINE )
			.getResultList();

		});

		entityManagerFactory.close();
	}
}

// Explicit joins may reference association or component/embedded attributes.
// In the case of component/embedded attributes, the join is simply logical
// and does not correlate to a physical (SQL) join.
