package by.pva.hibernate.part01.native_query;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestAliasAndPropertyReferences extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {


			
			
		});

		entityManagerFactory.close();
	}
	
}