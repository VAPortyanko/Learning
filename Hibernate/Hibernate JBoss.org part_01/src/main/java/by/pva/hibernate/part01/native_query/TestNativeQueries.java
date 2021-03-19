package by.pva.hibernate.part01.native_query;

import java.util.List;
import java.util.stream.Stream;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestNativeQueries extends BaseTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// JPA native query selecting all columns.
			List<Object[]> persons1 = entityManager.createNativeQuery(
				"SELECT * FROM Persons44")
			.getResultList();
			
			persons1.forEach(e -> Stream.of(e).forEach(System.out::println));

			// JPA native query with a custom column selection.
			List<Object[]> persons2 = entityManager.createNativeQuery(
				"SELECT id, name FROM Persons44" )
			.getResultList();

			persons2.forEach(e -> System.out.println(e[0] + " " + e[1]));
				



			
			

			
		});

		entityManagerFactory.close();
	}
	
}