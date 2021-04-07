package by.pva.hibernate.part01.native_query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import _by.pva.hibernate.part01.hql_jpql.domain_model.CreditCardPayment;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestHandlingInheritance extends BaseTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		buildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			// Native SQL queries which query for entities that are mapped as part of an inheritance 
			// must include all properties for the base class and all its subclasses.
			List<CreditCardPayment> payments = entityManager.unwrap(Session.class).createNativeQuery(
				"SELECT * " +
				"FROM Payments p " +
				"JOIN CreditCardPayments cp on cp.id = p.id" )
			.addEntity(CreditCardPayment.class)
			.list();

			payments.forEach(System.out::println);
			
			// There’s no such equivalent in JPA because the javax.persistence.Query interface 
			// does not define an addEntity method equivalent.
			
		});

		entityManagerFactory.close();
	}
	
}