package by.pva.hibernate.part01.hql_jpql.query_api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _by.pva.hibernate.part01.hql_jpql.domain_model.CreditCardPayment;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Payment;
import _by.pva.hibernate.part01.hql_jpql.domain_model.WireTransferPayment;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestPolymorphism extends BaseTest {

	public static void main(String[] args) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			Payment pay1 = new Payment();
			pay1.setId(1L);
			pay1.setAmount(new BigDecimal(10));
			
			WireTransferPayment pay2 = new WireTransferPayment();
			pay2.setId(2L);
			pay2.setAmount(new BigDecimal(20));
			
			CreditCardPayment pay3 = new CreditCardPayment();
			pay3.setId(3L);
			pay3.setAmount(new BigDecimal(30));
			
			entityManager.persist(pay1);
			entityManager.persist(pay2);
			entityManager.persist(pay3);
			
			entityManager.flush();
			entityManager.clear();
			
			// HQL and JPQL queries are inherently polymorphic.
			List<Payment> payments = entityManager.createQuery(
				"select p " +
				"from Payment p ", Payment.class )
			.getResultList();

			payments.forEach(System.out::println);
			
			List<Payment> payments2 = entityManager.createQuery(
					"select p " +
					"from Payment p " +
					"where type(p) = CreditCardPayment", Payment.class )
				.getResultList();
			
			payments2.forEach(System.out::println);
			
		});

		entityManagerFactory.close();
	}
	
}

// https://stackoverflow.com/questions/27966134/hibernate-explicit-polymorphism-with-joined-inheritence-strategy.