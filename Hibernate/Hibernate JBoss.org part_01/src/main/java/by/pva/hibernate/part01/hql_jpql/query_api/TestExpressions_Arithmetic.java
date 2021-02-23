package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.List;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;

public class TestExpressions_Arithmetic extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			// select clause date/time arithmetic operations
			Long duration = entityManager.createQuery(
				"select sum(ch.duration) * :multiplier " +
				"from Person44 pr " +
				"join pr.phones ph " +
				"join ph.callHistory ch " +
				"where ph.id = 1L ", Long.class)
			.setParameter("multiplier", 1000L)
			.getSingleResult();
			
			System.out.println(duration);

			// select clause date/time arithmetic operations
			Integer years = entityManager.createQuery(
				"select year(current_date()) - year(p.createdOn) " +
				"from Person44 p " +
				"where p.id = 1L", Integer.class )
			.getSingleResult();
			
			System.out.println(years);

			// where clause arithmetic operations
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where year(current_date()) - year(p.createdOn) < 1", Person.class)
			.getResultList();

			persons.stream().forEach(System.out::println);
		});

		entityManagerFactory.close();
	}
	
}