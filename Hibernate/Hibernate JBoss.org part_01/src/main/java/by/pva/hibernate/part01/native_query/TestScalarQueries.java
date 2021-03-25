package by.pva.hibernate.part01.native_query;

import java.util.List;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestScalarQueries extends BaseTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// 1.1 JPA native query selecting all columns.
			List<Object[]> persons1 = entityManager.createNativeQuery(
				"SELECT * FROM Persons44")
			.getResultList();
			
			persons1.forEach(e -> Stream.of(e).forEach(System.out::println));

			// 1.2 JPA native query with a custom column selection.
			List<Object[]> persons2 = entityManager.createNativeQuery(
				"SELECT id, name FROM Persons44" )
			.getResultList();

			persons2.forEach(e -> System.out.println(e[0] + " " + e[1]));
				
			// 2.1 Hibernate native query selecting all columns
			List<Object[]> persons3 = entityManager.unwrap(Session.class).createNativeQuery(
				"SELECT * FROM Persons44" )
			.list();

			persons3.forEach(e -> Stream.of(e).forEach(System.out::println));

			// 2.2 Hibernate native query with a custom column selection
			List<Object[]> persons4 = entityManager.unwrap(Session.class).createNativeQuery(
				"SELECT id, name FROM Persons44" )
			.list();

			persons4.forEach(e -> System.out.println(e[0] + " " + e[1]));

			// Hibernate will use java.sql.ResultSetMetadata to deduce the actual order and types of the returned scalar values.
			// To avoid the overhead of using ResultSetMetadata, or simply to be more explicit in what is returned, 
			// one can use addScalar():
			// 2.3 Hibernate native query with explicit result set selection
			List<Object[]> persons5 = entityManager.unwrap(Session.class).createNativeQuery(
				"SELECT * FROM Persons44")
			.addScalar("id", LongType.INSTANCE)
			.addScalar("name", StringType.INSTANCE)
			.list();

			persons5.forEach(e -> System.out.println(e[0] + " " + e[1]));

			// 2.4 Hibernate native query with result set selection that’s a partially explicit
			List<Object[]> persons = entityManager.unwrap(Session.class).createNativeQuery(
				"SELECT * FROM Persons44" )
			.addScalar("id", LongType.INSTANCE)
			.addScalar("name")
			.list();
			
			for(Object[] person : persons) {
				Long id = (Long) person[0];
				String name = (String) person[1];
				System.out.println("id: " + id + ", name: " + name );
			}

		});

		entityManagerFactory.close();
	}
	
}