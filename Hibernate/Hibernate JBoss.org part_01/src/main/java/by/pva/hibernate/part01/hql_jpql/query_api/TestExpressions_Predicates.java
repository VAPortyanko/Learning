package by.pva.hibernate.part01.hql_jpql.query_api;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import _by.pva.hibernate.part01.hql_jpql.domain_model.*;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

// Predicates form the basis of the where clause, the having clause and searched case expressions. 
// They are expressions which resolve to a truth value, generally TRUE or FALSE, although boolean comparisons
// involving NULL resolve typically to UNKNOWN.

public class TestExpressions_Predicates extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// Relational comparisons
			// Comparisons involve one of the comparison operators: =, >, >=, <, <=, <>. 
			// HQL also defines != as a comparison operator synonymous with <>. 
			// The operands should be of the same type.

			// numeric comparison
			List<Call> calls1 = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration < 30 ", Call.class)
			.getResultList();

			calls1.forEach(System.out::println);
			
			// string comparison
			List<Person> persons1 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.name like 'name%' ", Person.class)
			.getResultList();
			
			persons1.forEach(System.out::println);

			// datetime comparison
			List<Person> persons2 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.createdOn > '1950-01-01' ", Person.class)
			.getResultList();

			persons2.forEach(System.out::println);
			
			// enum comparison
			List<Phone> phones1 = entityManager.createQuery(
				"select p " +
				"from Phone27 p " +
				"where p.type = 'MOBILE' ", Phone.class)
			.getResultList();

			phones1.forEach(System.out::println);
			
			// boolean comparison
			Person person = entityManager.find(Person.class, 2l);
			Payment payment = new WireTransferPayment();
			payment.setId(1L);
			payment.setAmount(new BigDecimal(100));
			payment.setCompleted(true);
			payment.setPerson(person);
			
			entityManager.persist(payment);
			entityManager.flush();
			entityManager.clear();
			
			List<Payment> payments1 = entityManager.createQuery(
				"select p " +
				"from Payment p " +
				"where p.completed = true ", Payment.class)
			.getResultList();

			payments1.forEach(System.out::println);
			
			// boolean comparison
			List<Payment> payments2 = entityManager.createQuery(
				"select p " +
				"from Payment p " +
				"where type(p) = WireTransferPayment ", Payment.class)
			.getResultList();

			payments2.forEach(System.out::println);
			
			// entity value comparison
			List<Payment> phonePayments1 = entityManager.createQuery(
				"select distinct p " +
				"from Payment p, Phone27 ph " +
				"where p.person = ph.person ", Payment.class )
			.getResultList();

			phonePayments1.forEach(System.out::println);
			
			// Comparisons can also involve subquery qualifiers: ALL, ANY, SOME. SOME and ANY are synonymous.
			// The ALL qualifier resolves to true if the comparison is true for all of the values in the result of the subquery. 
			// It resolves to false if the subquery result is empty.
			// The ANY/SOME qualifier resolves to true if the comparison is true for some of 
			// (at least one of) the values in the result of the subquery. 
			// It resolves to false if the subquery result is empty.

			// select all persons with all calls shorter than 50 seconds.
			List<Person> persons3 = entityManager.createQuery(
				"select distinct p.person " +
				"from Phone27 p " +
				"join p.calls c " +
				"where 50 > all ( " +
				"	select duration" +
				"	from Call" +
				"	where phone = p " +
				") ", Person.class )
			.getResultList();

			persons3.forEach(System.out::println);
			
			// Nullness predicate.
			// It check a value for nullness. It can be applied to basic attribute references, entity references, and parameters. 
			// HQL additionally allows it to be applied to component/embeddable types.

			// select all persons with a nickname.
			List<Person> persons4 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.nickName is not null", Person.class )
			.getResultList();

			persons4.forEach(System.out::println);
			
			// select all persons without a nickname
			List<Person> persons5 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.nickName is null", Person.class )
			.getResultList();
			
			persons5.forEach(System.out::println);

			// Like predicate
			// Performs a like comparison on string values. The syntax is:
			// like_expression ::=
			//     string_expression
			//     [NOT] LIKE pattern_value
			//     [ESCAPE escape_character]

			// The semantics follow that of the SQL like expression. The pattern_value is the pattern to attempt to match in the string_expression. 
			// Just like SQL, pattern_value can use _ and % as wildcards. The meanings are the same. 
			// The _ symbol matches any single character and % matches any number of characters.
			 
			List<Person> persons6 = entityManager.createQuery(
				"select p " +
			 	"from Person44 p " +
			 	"where p.name like 'name%'", Person.class)
			.getResultList();

			persons6.forEach(e -> System.out.println("Person with name like 'name%': " + e));
						
			List<Person> persons7 = entityManager.createQuery(
			 	"select p " +
			 	"from Person44 p " +
			 	"where p.name not like 'name3'", Person.class)
			.getResultList();

			persons7.forEach(e -> System.out.println("Person with name not like 'name3': " + e));
			
			// The optional escape 'escape character' is used to specify an escape character used to escape the special meaning of _ and % in the pattern_value. 
			// This is useful when needing to search on patterns including either _ or %.

			// find any person with a name starting with "Dr_"
			Person person1 = entityManager.find(Person.class, 2L);
			person1.setName("Dr_dred");
			List<Person> persons8 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.name like 'Dr|_%' escape '|'", Person.class)
			.getResultList();
			
			persons8.forEach(e -> System.out.println("Persons with name like Dr_: " + e));

			// Between predicate
			// Analogous to the SQL BETWEEN expression, it checks if the value is within boundaries. All the operands should have comparable types.
			List<Person> persons9 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"join p.phones ph " +
				"where p.id = 1L and index(ph) between 0 and 3", Person.class)
			.getResultList();

			persons9.forEach(e -> System.out.println("Between1 persons: " + e));
			
			List<Person> persons10 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.createdOn between '1999-01-01' and '2022-01-02'", Person.class )
			.getResultList();

			persons10.forEach(e -> System.out.println("Between2 persons: " + e));
			
			List<Call> calls2 = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration between 5 and 20", Call.class )
			.getResultList();

			calls2.forEach(e -> System.out.println("Between3 persons: " + e));
			
			List<Person> persons11 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.name between 'name1' and 'name5'", Person.class )
			.getResultList();

			persons11.forEach(e -> System.out.println("Between4 persons: " + e));

			// [IN] predicate
            // IN predicates performs a check that a particular value is in a list of values. 
			// Its syntax is:
			// in_expression ::=
			//    single_valued_expression [NOT] IN single_valued_list

			// single_valued_list ::=
			//    constructor_expression | (subquery) | collection_valued_input_parameter

			// constructor_expression ::= (expression[, expression]*)
			
			// The types of the single_valued_expression and the individual values in the single_valued_list must be consistent.
			// JPQL limits the valid types here to string, numeric, date, time, timestamp, and enum types, and, in JPQL, single_valued_expression can only refer to:
			// "state fields", which is its term for simple attributes. Specifically, this excludes association and component/embedded attributes.
			// entity type expressions. See Entity type.
			// In HQL, single_valued_expression can refer to a far more broad set of expression types. Single-valued association are allowed, 
			// and so are component/embedded attributes, although that feature depends on the level of support for tuple or "row value constructor syntax" 
			// in the underlying database. Additionally, HQL does not limit the value type in any way, though application developers should be aware that different types
			// may incur limited support based on the underlying database vendor. This is largely the reason for the JPQL limitations.
			// The list of values can come from a number of different sources. In the constructor_expression and collection_valued_input_parameter, 
			// the list of values must not be empty; it must contain at least one value.

			List<Payment> payments3 = entityManager.createQuery(
				"select p " +
				"from Payment p " +
				"where type(p) in (CreditCardPayment, WireTransferPayment)", Payment.class)
			.getResultList();
			
			payments3.forEach(e -> System.out.println("[IN] predicare example 1: " + e));
			
			List<Phone> phones2 = entityManager.createQuery(
				"select p " +
				"from Phone27 p " +
				"where type in ('MOBILE', 'LAND_LINE')", Phone.class)
			.getResultList();
			
			phones2.forEach(e -> System.out.println("[IN] predicare example 2: " + e));
			
			List<Phone> phones3 = entityManager.createQuery(
				"select p " +
				"from Phone27 p " +
				"where type in :types", Phone.class)
			.setParameter("types", Arrays.asList(PhoneType.MOBILE, PhoneType.LAND_LINE))
			.getResultList();
			
			phones3.forEach(e -> System.out.println("[IN] predicare example 3: " + e));
			
			List<Phone> phones4 = entityManager.createQuery(
				"select distinct p " +
				"from Phone27 p " +
				"where p.person.id in (" +
				"	select py.person.id " +
				"	from Payment py" +
				"	where py.completed = true and py.amount > 0 " +
				")", Phone.class )
			.getResultList();
			
			phones4.forEach(e -> System.out.println("[IN] predicare example 4:" + e));
			
			// Not JPQL compliant!
			List<Phone> phones5 = entityManager.createQuery(
				"select distinct p " +
				"from Phone27 p " +
				"where p.person in (" +
				"	select py.person " +
				"	from Payment py" +
				"	where py.completed = true and py.amount > 0 " +
				")", Phone.class)
			.getResultList();
			
			phones5.forEach(e -> System.out.println("[IN] predicare example 5:" + e));
			
			// Not JPQL compliant!
			List<Payment> payments4 = entityManager.createQuery(
				"select distinct p " +
				"from Payment p " +
				"where (p.amount, p.completed) in (" +
				"	(50, true)," +
				"	(100, true)," +
				"	(5, false)" +
				")", Payment.class)
			.getResultList();
			
			payments4.forEach(e -> System.out.println("[IN] predicare example 6:" + e));

			List<Person> persons13 = entityManager.createQuery(
					"select distinct p " +
					"from Person44 p " +
					"where exists (" +
					"	select py.person " +
					"	from Payment py" +
					"	where py.person = p " +
					")", Person.class)
				.getResultList();
			
			persons13.forEach(e -> System.out.println("[EXISTS] prdicate artificial example: " + e));
			
			// The IS [NOT] EMPTY expression applies to collection-valued path expressions. 
			// It checks whether the particular collection has any associated values.
			List<Person> persons14 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.phones is empty", Person.class )
			.getResultList();
			
			List<Person> persons15 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.phones is not empty", Person.class )
			.getResultList();
			
			persons14.forEach(e -> System.out.println("[IS EMPTY] predicate: " + e));
			persons15.forEach(e -> System.out.println("[EMPTY] predicate: " + e));
			
			// Member-of collection predicate.
            // The [NOT] MEMBER [OF] expression applies to collection-valued path expressions. It checks whether a value is a member of the specified collection.
			List<Person> persons16 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where 'Home address' member of p.addresses", Person.class)
			.getResultList();

			List<Person> persons17 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where 'Home address' not member of p.addresses", Person.class)
			.getResultList();

			persons16.forEach(e -> System.out.println("[MEMBER OF] predicate: " + e));
			persons17.forEach(e -> System.out.println("[NOT MEMBER OF] predicate: " + e));
			
			// NOT predicate operator
			// The NOT operator is used to negate the predicate that follows it. If that following predicate is true, the NOT resolves to false.
	        // If the predicate is true, NOT resolves to false. If the predicate is unknown (e.g. NULL), then NOT resolves to unknown as well.

			//  AND predicate operator
			// The AND operator is used to combine 2 predicate expressions. The result of the AND expression is true if and only if both predicates resolve to true. 
			// If either predicate resolves to unknown, the AND expression resolves to unknown as well. Otherwise, the result is false.
			// OR predicate operator
			// The OR operator is used to combine 2 predicate expressions. The result of the OR expression is true if one predicate resolves to true. 
			// If both predicates resolve to unknown, the OR expression resolves to unknown. Otherwise, the result is false.
			
			List<Payment> paymentsForDelete = entityManager.createQuery(
					"select p" +
					" from Payment p", Payment.class)
			.getResultList();
			
			paymentsForDelete.forEach(e -> entityManager.remove(e));
			
		});

		entityManagerFactory.close();
	}
	
}