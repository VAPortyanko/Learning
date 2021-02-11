package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.List;
import java.util.stream.Collectors;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestQueryStreaming extends BaseTest {

	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			List<Person> persons = entityManager.createQuery(
					"select p " +
					"from Person44 p " +
					"where p.name like :name", Person.class )
			.setParameter("name", "n%")
			.getResultStream()
			.skip(2)
			.limit(2)
			.collect(Collectors.toList());
			
			// The Stream is closed automatically after calling the collect method,
			// since there is no reason to keep the underlying JDBC ResultSet open if
			// the Stream cannot be reused.
			
			persons.stream().forEach(System.out::println);
			
		});

		entityManagerFactory.close();

	}
}
