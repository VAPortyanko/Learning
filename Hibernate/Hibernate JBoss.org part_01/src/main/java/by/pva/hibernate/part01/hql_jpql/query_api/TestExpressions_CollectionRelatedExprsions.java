package by.pva.hibernate.part01.hql_jpql.query_api;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Call;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Phone;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;

public class TestExpressions_CollectionRelatedExprsions extends BaseTest {

	public static void main(String[] args) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// There are a few specialized expressions for working with collection-valued associations. 
			// Generally, these are just abbreviated forms or other expressions for the sake of conciseness.\
			//
			// SIZE       - Calculate the size of a collection. Equates to a subquery!
			// MAXELEMENT - Available for use on collections of basic type. 
			//              Refers to the maximum value as determined by applying the max SQL aggregation.
			// MAXINDEX   - Available for use on indexed collections. 
			//              Refers to the maximum index (key/position) as determined by applying the max 
			//              SQL aggregation.
			// MINELEMENT - Available for use on collections of basic type. 
			//              Refers to the minimum value as determined by applying the min SQL aggregation.
			// MININDEX   - Available for use on indexed collections. 
			//              Refers to the minimum index (key/position) as determined by applying the min 
			//              SQL aggregation.
			// ELEMENTS   - Used to refer to the elements of a collection as a whole. 
			//              Only allowed in the where clause. Often used in conjunction 
			//              with ALL, ANY or SOME restrictions.
			// INDICES    - Similar to elements except that the indices expression refers to the collections
			//              indices (keys/positions) as a whole.
			Call call1 = entityManager.find(Call.class, 2L);
			
			List<Phone> phones1 = entityManager.createQuery(
				"select p " +
				"from Phone27 p " +
				"where maxelement(p.calls) = :call", Phone.class)
			.setParameter("call", call1)
			.getResultList();
			phones1.forEach(System.out::println);
			
			Call call2 = entityManager.find(Call.class, 3L);
			
			List<Phone> phones2 = entityManager.createQuery(
				"select p " +
				"from Phone27 p " +
				"where minelement(p.calls) = :call", Phone.class )
			.setParameter("call", call2)
			.getResultList();
			phones2.forEach(System.out::println);
			
			List<Person> persons1 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where maxindex(p.phones) = 1", Person.class)
			.getResultList();
			persons1.forEach(System.out::println);
			
			// the above query can be re-written with member of
			Phone phone1 = entityManager.find(Phone.class,  8L);
			List<Person> persons2 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where :phone member of p.phones", Person.class )
			.setParameter("phone", phone1)
			.getResultList();
			persons2.forEach(System.out::println);
			
			List<Person> persons3 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where :phone = some elements (p.phones)", Person.class )
			.setParameter("phone", phone1)
			.getResultList();
			persons3.forEach(System.out::println);
			
			List<Person> persons4 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where exists elements (p.phones)", Person.class )
			.getResultList();
			persons4.forEach(System.out::println);
			
			List<Phone> phones3 = entityManager.createQuery(
				"select p " +
				"from Phone27 p " +
				"where current_date() > key(p.callHistory)", Phone.class )
			.getResultList();
			phones3.forEach(System.out::println);
			
			List<Phone> phones4 = entityManager.createQuery(
				"select p " +
				"from Phone27 p " +
				"where current_date() > all elements(p.repairTimestamps)", Phone.class)
			.getResultList();
			phones4.forEach(System.out::println);
			
			List<Person> persons5 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where 1 in indices(p.phones)", Person.class)
			.getResultList();
			persons5.forEach(System.out::println);
			
			List<Person> persons6 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where size(p.phones) = 2", Person.class)
			.getResultList();
			persons6.forEach(System.out::println);

            /*
			* Elements of indexed collections (arrays, lists, and maps) can be referred to by index operator.
			*/
			
			// indexed lists
			List<Person> persons7 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.phones[0].type = 'LAND_LINE'", Person.class)
			.getResultList();
			persons7.forEach(System.out::println);
			
			// maps
			List<Person> persons8 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.addresses['HOME'] = :address", Person.class)
			.setParameter( "address", "Home address1")
			.getResultList();
			persons8.forEach(System.out::println);
			
			//max index in list
			List<Person> persons9 = entityManager.createQuery(
				"select pr " +
				"from Person44 pr " +
				"where pr.phones[minindex(pr.phones)].type = 'LAND_LINE'", Person.class)
			.getResultList();
			persons9.forEach(System.out::println);

		});

		entityManagerFactory.close();
	}
	
}