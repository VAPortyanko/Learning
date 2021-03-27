package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.List;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

// Beyond the JPQL standardized functions, HQL makes some additional functions
// available regardless of the underlying database in use.
public class TestExpressions_HQLStandardizedFunctions extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// BIT_LENGTH
			// Returns the length of binary data.
			List<Number> bits = entityManager.createQuery(
				"select bit_length(c.duration) " +
				"from Call c ", Number.class )
			.getResultList();
			bits.forEach(e -> System.out.println("Duration bit length: " + e));
			
			// CAST
            // Performs a SQL cast. The cast target should name the Hibernate mapping type to use. 
			// See the data types chapter on for more information.
			List<String> durations = entityManager.createQuery(
				"select cast(c.duration as string) " +
				"from Call c ", String.class )
			.getResultList();
			durations.forEach(e -> System.out.println("Duration as a String: " + e));
			
			// EXTRACT
			// Performs a SQL extraction on datetime values. 
			// An extraction extracts parts of the datetime (the year, for example).
			List<Integer> years1 = entityManager.createQuery(
				"select extract(YEAR from c.timestamp) " +
				"from Call c ", Integer.class )
			.getResultList();
			years1.forEach(e -> System.out.println("Year of the Call: " + e));
		
			// ABBREVIATED FORMS:
			// YEAR - Abbreviated extract form for extracting the year.
			// MONTH - Abbreviated extract form for extracting the month.
			// DAY - Abbreviated extract form for extracting the day.
			// HOUR - Abbreviated extract form for extracting the hour.
			// MINUTE - Abbreviated extract form for extracting the minute.
			// SECOND - Abbreviated extract form for extracting the second.
			// STR - Abbreviated form for casting a value as character data.

			List<Integer> years2 = entityManager.createQuery(
					"select year(c.timestamp) " +
					"from Call c ", Integer.class)
				.getResultList();
			years2.forEach(e -> System.out.println("Year of the Call: " + e));
			
			List<String> timestamps1 = entityManager.createQuery(
				"select str(c.timestamp) " +
				"from Call c ", String.class )
			.getResultList();
			timestamps1.forEach(e -> System.out.println("Timestamp as a string: " + e));
			
			List<String> timestamps2 = entityManager.createQuery(
				"select str(cast(duration as float) / 60, 4, 2) " +
				"from Call c ", String.class )
			.getResultList();
			timestamps2.forEach(e -> System.out.println("The expression with timestamp as a string: " + e));
			
		});

		entityManagerFactory.close();
	}
	
}