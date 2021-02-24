package by.pva.hibernate.part01.hql_jpql.query_api;

import java.math.BigDecimal;
import java.util.List;

import _by.pva.hibernate.part01.hql_jpql.domain_model.*;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

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
			
			List<Payment> paymentsForDelete = entityManager.createQuery(
					"select p" +
					" from Payment p", Payment.class)
			.getResultList();
			
			paymentsForDelete.forEach(e -> entityManager.remove(e));
			
		});

		entityManagerFactory.close();
	}
	
}