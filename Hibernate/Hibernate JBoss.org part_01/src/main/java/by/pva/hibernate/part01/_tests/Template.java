package by.pva.hibernate.part01._tests;

import java.util.HashMap;
import java.util.Map;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class Template extends BaseTest {

	public static void main(String[] args) {
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
		});

		entityManagerFactory.close();
	}
	
}
