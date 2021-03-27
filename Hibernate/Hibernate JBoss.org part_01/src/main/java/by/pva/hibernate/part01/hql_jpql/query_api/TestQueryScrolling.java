package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.stream.Stream;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

// Close ScrollablleResults and streams!

public class TestQueryScrolling extends BaseTest{

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			try (ScrollableResults scrollableResults = entityManager.unwrap(Session.class).createQuery(
					"select p " +
					"from Person44 p " +
					"where p.name like :name")
					.setParameter("name", "n%")
					.scroll() // The method is overloaded: scroll(ScrollMode.*)
			) {
				while(scrollableResults.next()) {
					Person person = (Person) scrollableResults.get()[0];
					System.out.println(person);
				}
			}
			
			//
			// query.iterate() - Hibernate also supports Query.iterate, 
			// which is intended for loading entities when it is known 
			// that the loaded entries are already stored in the second-level cache.
			// Read details before using!
			//
			
			// Internally, the stream() behaves like a Query.scroll 
			// and the underlying result is backed by a ScrollableResults.
			try (Stream<Object[]> persons = entityManager.unwrap(Session.class).createQuery(
				"select p.name, p.nickName " +
				"from Person44 p " +
				"where p.name like :name")
			.setParameter("name", "n%")
			.stream()) {
				persons
				.map(row -> (String)row[0] + " " + (String)row[1])
				.forEach(System.out::println);
			}

			// When fetching a single result, like a Person entity, 
			// instead of a Stream<Object[]>, Hibernate is going to figure out 
			// the actual type, so the result is a Stream<Person>.
			try( Stream<Person> persons = entityManager.unwrap(Session.class).createQuery(
				"select p " +
				"from Person44 p " +
				"where p.name like :name" )
			.setParameter("name", "n%")
			.stream() ) {
				persons
				.limit(3)
				.forEach(System.out::println);
				// 	Map<Phone, List<Call>> callRegistry = persons
				//      .flatMap(person -> person.getPhones().stream())
				//      .flatMap(phone -> phone.getCalls().stream())
				//      .collect(Collectors.groupingBy(Call::getPhone));
			}
			
		});

		entityManagerFactory.close();
	}
}

/*
Since this form holds the JDBC ResultSet open, the application should indicate
when it is done with the ScrollableResults by calling its close() method
(as inherited from java.io.Closeable so that ScrollableResults will work with
try-with-resources blocks). If left unclosed by the application, Hibernate will
automatically close the underlying resources (e.g. ResultSet and PreparedStatement)
used internally by the ScrollableResults when the current transaction is ended 
(either commit or rollback).
However, it is good practice to close the ScrollableResults explicitly.
 */

/*
 If you plan to use Query.scroll() with collection fetches it is important
 that your query explicitly order the results so that the JDBC results
  contain the related rows sequentially.
 */
