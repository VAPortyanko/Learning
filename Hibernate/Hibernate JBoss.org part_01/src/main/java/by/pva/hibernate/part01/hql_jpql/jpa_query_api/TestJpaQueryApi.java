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

			// Obtaining a JPA Query or a TypedQuery reference for a named query.
			Query namedQuery = entityManager.createNamedQuery("get_person_by_name");

			TypedQuery<Person> namedTypedQuery = entityManager.createNamedQuery("get_person_by_name",
					                                                             Person.class
			);
			
			String parameter1 = "name%";
			query.setParameter("name", parameter1)
			      .getResultStream()
			      .forEach(System.out::println);
			
			typedQuery.setParameter("name", parameter1)
			          .getResultList()
			          .forEach(System.out::println);
			
			String parameter2 = "name3";
			namedQuery.setParameter("name", parameter2)
		      .getResultStream()
		      .forEach(System.out::println);
		
			namedTypedQuery.setParameter("name", parameter2)
		          .getResultList()
		          .forEach(System.out::println);

		});

		entityManagerFactory.close();
	}

}
