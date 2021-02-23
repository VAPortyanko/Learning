package by.pva.hibernate.part01.hql_jpql.query_api;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

import java.util.List;

import _by.pva.hibernate.part01.hql_jpql.domain_model.*;

public class TestExpressions_EntityType extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			List<Payment> payments1 = entityManager.createQuery(
				"select p " +
				"from Payment p " +
				"where type(p) = CreditCardPayment", Payment.class )
			.getResultList();
			
			payments1.forEach(System.out::println);
			
			List<Payment> payments2 = entityManager.createQuery(
				"select p " +
				"from Payment p " +
				"where type(p) = :type", Payment.class )
			.setParameter( "type", WireTransferPayment.class)
			.getResultList();
			
			payments2.forEach(System.out::println);

		});

		entityManagerFactory.close();
	}
	
}