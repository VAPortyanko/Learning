package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.List;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import _by.pva.hibernate.part01.hql_jpql.domain_model.PhoneType;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestFromClause extends BaseTest {

	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			// Simple query example.
			entityManager.createQuery(
				"select p " +
				"from _by.pva.hibernate.part01.hql_jpql.domain_model.Person p", Person.class )
			.getResultList()
			.forEach(System.out::println);

			// Simple query using entity name for root entity reference.
			entityManager.createQuery(
				"select p " +
				"from Person44 p", Person.class )
			.getResultList()
			.forEach(System.out::println);
			
			// Simple query using multiple root entity references.
			List<Object[]> persons = entityManager.createQuery(
				"select distinct pr, ph " +
				"from Person44 pr, Phone27 ph " +
				"where ph.type =:type and ph.person = pr", Object[].class)
			.setParameter("type", PhoneType.LAND_LINE)		
			.getResultList();

			persons.forEach(e -> System.out.println(e[0] + " " + e[1]));
			
			List<Person> persons2 = entityManager.createQuery(
				"select distinct pr1" +
				" from Person44 pr1, Person44 pr2" +
				" where pr1.id <> pr2.id" +
				" and pr1.id + pr2.id = 3"
				, Person.class)
			.getResultList();

			persons2.forEach(System.out::println);
		
		});

		entityManagerFactory.close();
	}

}

/* 
The BNF for a root entity reference (JPA calls it "a range variable declaration") is:

root_entity_reference ::=
    entity_name [AS] identification_variable
*/

