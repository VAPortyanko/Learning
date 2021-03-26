package by.pva.hibernate.part01.native_query;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestAliasAndPropertyReferences extends BaseTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {


			
			
		});

		entityManagerFactory.close();
	}
	
}