package by.pva.hibernate.part01.naming_strategy;

import java.util.HashMap;
import java.util.Map;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestPhysicalNamingStrategy extends BaseTest {
	
	public static void main(String[] args) {

		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.physical_naming_strategy", "by.pva.hibernate.part01.naming_strategy.CustomPhysicalNamingStrategy");
		properties.put("hibernate.hbm2ddl.auto", "none"); 

		buildEntityManagerFactory(properties);

		doInJPA(entityManager -> {

			Customer customer = new Customer();
			customer.setEmailAddress("Some@mail.com");
			customer.setFirstName("FirstName");
			customer.setLastName("LastName");

			entityManager.persist(customer);

		});

		entityManagerFactory.close();

	}
}
