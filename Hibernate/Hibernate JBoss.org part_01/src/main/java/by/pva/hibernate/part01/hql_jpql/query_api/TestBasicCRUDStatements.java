package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.stream.Stream;

import org.hibernate.Session;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestBasicCRUDStatements extends BaseTest {

	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			// The simplest possible HQL SELECT statement is of the form:
			entityManager.unwrap(Session.class)
			             .createQuery("from Person44", Person.class)
			             .stream()
			             .forEach(System.out::println);
			
			// The select statement in JPQL is exactly the same as for HQL except
			// that JPQL requires a select_clause, whereas HQL does not.
			entityManager.createQuery("select p " +
							          "from Person44 p", Person.class )
					.getResultList()
					.stream()
					.forEach(System.out::println);

		});

		entityManagerFactory.close();
	}

}
