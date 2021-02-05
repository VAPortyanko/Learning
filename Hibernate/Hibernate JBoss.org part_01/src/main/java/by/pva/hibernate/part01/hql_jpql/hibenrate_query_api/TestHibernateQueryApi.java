package by.pva.hibernate.part01.hql_jpql.hibenrate_query_api;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.Phone;
import by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestHibernateQueryApi extends BaseTest {

	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			// 1) Hibernate offers a specific @NamedQuery annotation which provides ways to configure various query features, 
			//    like flush mode, cacheability, time out interval.
			// 
			//    See the by.pva.hibernate.part01.hql_jpql.domain_model.Phone class.
			TypedQuery<Phone> query = entityManager.createNamedQuery("get_phone_by_number", Phone.class);
			Phone phone = query.setParameter("number", "+375 (33) 012-38-22").getSingleResult();

			System.out.println(phone);
			
			/* 2) Hibernate query hints:
			      org.hibernate.query.Query query = session.createQuery(
				      "select p " +
				      "from Person p " +
				      "where p.name like :name" )
			      .setTimeout( 2 )                             // Timeout - in seconds.
			      .setCacheMode( CacheMode.REFRESH )           // rite to L2 caches, but do not read from them.
			      .setCacheable( true )                        // Assuming query cache was enabled for the SessionFactory.
			      .setComment( "+ INDEX(p idx_person_name)" ); // Add a comment to the generated SQL if enabled via the hibernate.use_sql_comments
			                                             // configuration property.
           
			Query hints here are database query hints. 
			They are added directly to the generated SQL according to Dialect#getQueryHintString.
			The JPA notion of query hints, on the other hand, refer to hints that target the provider (Hibernate).
			So even though they are called the same, be aware they have a very different purpose. 
			Also, be aware that Hibernate query hints generally make the application non-portable across databases
			unless the code adding them first checks the Dialect.
			                                              
		    */

		
			// 3) In terms of execution, Hibernate offers 4 different methods. The 2 most commonly used are
			//    Query#list - executes the select query and returns back the list of results.
			//    Query#uniqueResult - executes the select query and returns the single result. 
			//    If there were more than one result an exception is thrown.

			List<Person> persons = entityManager.unwrap(Session.class)
					.createQuery("select p " +
					             "from Person44 p " +
			                     "where p.name like :name", Person.class)
					.setParameter("name", "name%")
					.list();
			persons.stream().forEach(System.out::println);
			
			Person person = entityManager.unwrap(Session.class)
					.createQuery("select p " +
				                 "from Person44 p " +
				                 "where p.name like :name", Person.class)
			.setParameter("name", "name3")
			.uniqueResult();

			System.out.println(person);
			// If the unique result is used often and the attributes upon which it is based are unique, 
			// you may want to consider mapping a natural-id and using the natural-id loading API. 
			// See the Natural Ids for more information on this topic.
			
		});

		entityManagerFactory.close();

	}

}
