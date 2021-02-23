package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.List;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Call;

// Here is the list of functions defined as supported by JPQL. 
// Applications interested in remaining portable between JPA providers should stick to these functions.
public class TestExpressions_JPQLStandardizedFunctions extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			// CONCAT
			// String concatenation function. 
			// Variable argument length of 2 or more string values to be concatenated together.
			List<String> callHistory = entityManager.createQuery(
				"select concat(p.number, ' : ' , cast(c.duration as string)) " +
				"from Call c " +
				"join c.phone p", String.class )
			.getResultList();
			callHistory.forEach(System.out::println);
			
			// SUBSTRING
			// Extracts a portion of a string value. 
			// The second argument denotes the starting position, where 1 is the first character of the string. 
			// The third (optional) argument denotes the length.
			List<String> prefixes = entityManager.createQuery(
				"select substring(p.number, 1, 9) " +
				"from Call c " +
				"join c.phone p", String.class )
			.getResultList();
			prefixes.forEach(System.out::println);
			
			// UPPER
			// Upper cases the specified string.
			List<String> names1 = entityManager.createQuery(
				"select upper(p.name) " +
				"from Person44 p ", String.class )
			.getResultList();
			names1.forEach(System.out::println);
			
			// LOWER
			// Lower cases the specified string.
			List<String> names2 = entityManager.createQuery(
				"select lower( p.name ) " +
				"from Person44 p ", String.class )
			.getResultList();
			names2.forEach(System.out::println);

			// TRIM
			// Follows the semantics of the SQL trim function.
			List<String> names3 = entityManager.createQuery(
				"select trim('    Customer: ' || p.name) " +
				"from Person44 p ", String.class )
			.getResultList();
			names3.forEach(System.out::println);
			
			// LENGTH
			// Returns the length of a string.
			List<Integer> lengths = entityManager.createQuery(
				"select length(p.name) " +
				"from Person44 p ", Integer.class )
			.getResultList();
			lengths.forEach(e -> System.out.println("Name length = " + e));
			
			// LOCATE
			// Locates a string within another string. 
			// The third argument (optional) is used to denote a position from which to start looking.
			List<Integer> sizes = entityManager.createQuery(
				"select locate('me', p.name) " +
				"from Person44 p ", Integer.class )
			.getResultList();
			sizes.forEach(e -> System.out.println("Position of 'me' in the person name: " + e));
			
			// ABS 		
			// Calculates the mathematical absolute value of a numeric value.
			List<Integer> abs = entityManager.createQuery(
				"select abs(c.duration * -1) " +
				"from Call c ", Integer.class )
			.getResultList();
			abs.forEach(e -> System.out.println("ABS(duration*-1) = " + e));
			
			// MOD
			// Calculates the remainder of dividing the first argument by the second.
			List<Integer> mods = entityManager.createQuery(
				"select mod( c.duration, 10 ) " +
				"from Call c ", Integer.class )
			.getResultList();
			mods.forEach(e -> System.out.println("Mod(duration/10) = " + e));
			
			// SQRT
			// Calculates the mathematical square root of a numeric value.
			List<Double> sqrts = entityManager.createQuery(
				"select sqrt(c.duration) " +
				"from Call c ", Double.class )
			.getResultList();
			sqrts.forEach(e -> System.out.println("SQRT(duration) = " + e));
			
			// CURRENT_DATE
			// Returns the database current date.
			List<Call> calls1 = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.timestamp < current_date", Call.class )
			.getResultList();
			calls1.forEach(e -> System.out.println("Call with a date that is less than the current date: " + e));
			
			// CURRENT_TIME
			// Returns the database current time.
			List<Call> calls2 = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.timestamp = current_time", Call.class )
			.getResultList();
			calls2.forEach(e -> System.out.println("Call with the current time: " + e));
			
			// CURRENT_TIMESTAMP
			// Returns the database current timestamp.
			List<Call> calls3 = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.timestamp = current_timestamp", Call.class )
			.getResultList();
			calls3.forEach(e -> System.out.println("Call with the current timestamp: " + e));
			
		});

		entityManagerFactory.close();
	}
	
}