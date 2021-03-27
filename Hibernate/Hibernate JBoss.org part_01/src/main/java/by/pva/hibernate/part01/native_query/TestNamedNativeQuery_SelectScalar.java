package by.pva.hibernate.part01.native_query;

import java.util.List;

import org.hibernate.Session;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestNamedNativeQuery_SelectScalar extends BaseTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// JPA named native query selecting a scalar value
			List<String> names1 = entityManager.createNamedQuery(
				"find_person_name")
			.getResultList();

			names1.forEach(System.out::println);
			
			// Hibernate named native query selecting a scalar value
			List<String> names2 = entityManager.unwrap(Session.class).getNamedQuery(
				"find_person_name")
			.list();

			names2.forEach(System.out::println);

			// Multiple scalar values NamedNativeQuery
			// Without specifying an explicit result type, Hibernate will assume an Object array:
			// JPA named native query selecting multiple scalar values

			List<Object[]> tuples1 = entityManager.createNamedQuery(
				"find_person_name_and_nickName" )
			.getResultList();

			tuples1.forEach(e -> System.out.println("name: " + (String) e[0] + ". nickname: " + (String) e[1]));

			// It’s possible to use a DTO to store the resulting scalar values
			// JPA named native query selecting multiple scalar values into a DTO:
			 List<PersonNames> personNames1 = entityManager.createNamedQuery(
			 	"find_person_name_and_nickName_dto" )
			 .getResultList();

			 // Hibernate named native query selecting multiple scalar values into a DTO:
			 List<PersonNames> personNames2 = entityManager.unwrap(Session.class).getNamedQuery(
			 	"find_person_name_and_nickName_dto" )
			 .list();

			 personNames1.forEach(System.out::println);
			 personNames2.forEach(System.out::println);

//			You can also use the @NamedNativeQuery Hibernate annotation to customize the named query using various configurations such as fetch mode, cacheability, time out interval.
//			Multiple scalar values using ConstructorResult and Hibernate NamedNativeQuery
//			@NamedNativeQueries({
//			    @NamedNativeQuery(
//			        name = "get_person_phone_count",
//			        query = "SELECT pr.name AS name, count(*) AS phoneCount " +
//			                "FROM Phone p " +
//			                "JOIN Person pr ON pr.id = p.person_id " +
//			                "GROUP BY pr.name",
//			        resultSetMapping = "person_phone_count",
//			        timeout = 1,
//			        readOnly = true
//			    ),
//			})
//			@SqlResultSetMapping(
//			    name = "person_phone_count",
//			    classes = @ConstructorResult(
//			        targetClass = PersonPhoneCount.class,
//			        columns = {
//			            @ColumnResult(name = "name"),
//			            @ColumnResult(name = "phoneCount")
//			        }
//			    )
//			)
//
//			 List<PersonPhoneCount> personNames = session.getNamedNativeQuery(
//				"get_person_phone_count")
//			.getResultList();
			
		});

		entityManagerFactory.close();
	}
	
}