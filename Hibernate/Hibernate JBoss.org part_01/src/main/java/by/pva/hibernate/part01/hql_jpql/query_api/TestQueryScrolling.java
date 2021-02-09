package by.pva.hibernate.part01.hql_jpql.query_api;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestQueryScrolling extends BaseTest{

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			try (ScrollableResults scrollableResults = entityManager.unwrap(Session.class).createQuery(
					"select p " +
					"from Person44 p " +
					"where p.name like :name")
					.setParameter("name", "n%")
					.scroll()
			) {
				while(scrollableResults.next()) {
					Person person = (Person) scrollableResults.get()[0];
					System.out.println(person);
				}
			}
			
		});

		entityManagerFactory.close();
	}
}
