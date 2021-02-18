package by.pva.hibernate.part01.hql_jpql.query_api;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;
import by.pva.hibernate.part01.hql_jpql.domain_model.Person;

public class TestFromClause extends BaseTest {

	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			// Simple query example.
			entityManager.createQuery(
				"select p " +
				"from by.pva.hibernate.part01.hql_jpql.domain_model.Person p", Person.class )
			.getResultList()
			.stream()
			.forEach(System.out::println);

			// Simple query using entity name for root entity reference.
			entityManager.createQuery(
				"select p " +
				"from Person44 p", Person.class )
			.getResultList()
			.stream()
			.forEach(System.out::println);

			
			String str = "select distinct a, b from by.pva.hibernate.part01.hql_jpql.domain_model.Person a, by.pva.hibernate.part01.hql_jpql.domain_model.Phone b where b.person = a and b is not null";
			System.out.println(str.substring(0, 83));
			
			// Simple query using multiple root entity references.
			entityManager.createQuery(
				"select distinct a, b " +
				"from Person44 a, Phone27 b " +
				"where b.person = a and b is not null", Object[].class)
			.getResultList()
			.stream()
			.forEach(e->System.out.println(e[0]));



			
		});

		entityManagerFactory.close();
	}

}

/* 
The BNF for a root entity reference (JPA calls it "a range variable declaration") is:

root_entity_reference ::=
    entity_name [AS] identification_variable
*/

