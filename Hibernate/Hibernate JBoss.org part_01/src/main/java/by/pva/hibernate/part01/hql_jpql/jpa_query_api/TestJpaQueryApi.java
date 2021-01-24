package by.pva.hibernate.part01.hql_jpql.jpa_query_api;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;
import by.pva.hibernate.part01.hql_jpql.domain_model.*;

public class TestJpaQueryApi extends BaseTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// Obtaining a JPA Query or a TypedQuery reference.
			Query query = entityManager
				  .createQuery("select p " +
			                   "from Person44 p " +
					           "where p.name like :name");

			TypedQuery<Person> typedQuery = entityManager
					.createQuery("select p " +
			                     "from Person44 p " +
							     "where p.name like :name",
							     Person.class);
			
			query.setParameter("name", "name%")
			      .getResultStream()
			      .forEach(System.out::println);
			
			typedQuery.setParameter("name", "name%")
			          .getResultList()
			          .forEach(System.out::println);

		});

		entityManagerFactory.close();
	}

}
