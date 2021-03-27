package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.List;
import java.util.Map;

import _by.pva.hibernate.part01.hql_jpql.domain_model.CallStatistics;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

// The SELECT clause identifies which objects and values to return as the query results.
//
// There is a particular expression type that is only valid in the select clause. Hibernate calls this "dynamic instantiation". 
// JPQL supports some of that feature and calls it a "constructor expression".
// So rather than dealing with the Object[], we can wrap the values in a type-safe Java object that will be returned as the results of the query.
//
// The projection class must be fully qualified in the entity query, and it must define a matching constructor.
// 
// The projection class need not be mapped. It can be a DTO class.
// If it does represent an entity, the resulting instances are returned in the NEW state (not managed!).
public class TestSelectClause extends BaseTest {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			CallStatistics callStatistics = entityManager.createQuery(
					"select new _by.pva.hibernate.part01.hql_jpql.domain_model.CallStatistics(" +
					"	count(c), " +
					"	sum(c.duration), " +
					"	min(c.duration), " +
					"	max(c.duration), " +
					"	avg(c.duration)" +
					")  " +
					"from Call c ", CallStatistics.class)
				.getSingleResult();
			
			System.out.println("Count: " + callStatistics.getCount());
			System.out.println("Max:   " + callStatistics.getMax());
			System.out.println("Min:   " + callStatistics.getMin());
			System.out.println("AVG:   " + callStatistics.getAvg());
			System.out.println("Total: " + callStatistics.getTotal());

			// HQL supports additional "dynamic instantiation" features. 
			// First, the query can specify to return a List rather than an Object[] for scalar results:
			List<List> phoneCallDurations = entityManager.createQuery(
				"select new list(" +
				"	p.number, " +
				"	c.duration " +
				")  " +
				"from Call c " +
				"join c.phone p ", List.class)
			.getResultList();
			// The results from this query will be a List<List> as opposed to a List<Object[]>.
			phoneCallDurations.forEach(System.out::println);
			
			// HQL also supports wrapping the scalar results in a Map.
			// The results from this query will be a List<Map<String, Object>> as opposed to a List<Object[]>. 
			// The keys of the map are defined by the aliases given to the select expressions. 
			// If the user doesn’t assign aliases, the key will be the index of each particular result set column (e.g. 0, 1, 2, etc).
			List<Map> phoneCallTotalDurations = entityManager.createQuery(
				"select new map(" +
				"	p.number as phoneNumber , " +
				"	sum(c.duration) as totalDuration, " +
				"	avg(c.duration) as averageDuration " +
				")  " +
				"from Call c " +
				"join c.phone p " +
				"group by p.number ", Map.class )
			.getResultList();

			phoneCallTotalDurations.forEach(map -> {
				System.out.println(" Phone number [" + map.get("phoneNumber") + "] total calls duration: "
			                       + map.get("totalDuration"));
			});

		});

		entityManagerFactory.close();
	}
	
}