package by.pva.hibernate.part01.hql_jpql.query_api;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestExpressions_Concatenation extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			String name = entityManager.createQuery(
				"select 'Customer: ' || p.name " +
				"from Person44 p " +
				"where p.id = 1", String.class )
			.getSingleResult();

			System.out.println(name);
		});

		entityManagerFactory.close();
	}
	
}

// QL defines a concatenation operator in addition to supporting the concatenation (CONCAT) function. 
// This is not defined by JPQL, so portable applications should avoid its use. 
// The concatenation operator is taken from the SQL concatenation operator (e.g ||).