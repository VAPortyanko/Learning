package by.pva.hibernate.part01.native_query;

import java.util.List;

import org.hibernate.Session;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestEntityQueries extends BaseTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			 // JPA native query selecting entities.
			 List<Person> persons1 = entityManager.createNativeQuery(
			 	"SELECT * FROM Persons44", Person.class)
			 .getResultList();

			 persons1.forEach(System.out::println);

			 // Hibernate native query selecting entities.
			 List<Person> persons2 = entityManager.unwrap(Session.class).createNativeQuery(
			 	"SELECT * FROM Persons44")
			 .addEntity(Person.class)
			 .list();

			 persons2.forEach(System.out::println);
			 
			 // JPA native query selecting entities with explicit result set
			 List<Person> persons3 = entityManager.createNativeQuery(
			 	"SELECT id, name, nickName, address, createdOn, version " +
			 	"FROM Persons44", Person.class)
			 .getResultList();
			 
			 persons3.forEach(System.out::println);
			 
			 // Hibernate native query selecting entities with explicit result set
			 List<Person> persons4 = entityManager.unwrap(Session.class).createNativeQuery(
			 	"SELECT id, name, nickName, address, createdOn, version " +
			 	"FROM Persons44")
			 .addEntity(Person.class)
			 .list();

			 persons4.forEach(System.out::println);

		});

		entityManagerFactory.close();
	}
	
}