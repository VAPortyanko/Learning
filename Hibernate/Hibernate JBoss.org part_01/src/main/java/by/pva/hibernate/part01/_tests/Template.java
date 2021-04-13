package by.pva.hibernate.part01._tests;

import java.util.Collections;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class Template extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			buildEntityManagerFactory(Collections.singletonMap("hibernate.format_sql", "true"));
			
		});

		entityManagerFactory.close();
	}
	
}