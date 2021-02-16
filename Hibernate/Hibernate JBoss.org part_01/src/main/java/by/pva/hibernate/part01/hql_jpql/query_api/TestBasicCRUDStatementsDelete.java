package by.pva.hibernate.part01.hql_jpql.query_api;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.PhoneType;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestBasicCRUDStatementsDelete extends BaseTest {

	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			final PhoneType phoneType = PhoneType.LAND_LINE;

			int updatedEntities = entityManager.createQuery(
				"delete from Phone27 ph " +
				"where ph.type = :type")
			.setParameter("type", phoneType)		
			.executeUpdate();
			
			// The int value returned by the executeUpdate() method indicates the number of entities
			// affected by the operation. This may or may not correlate to the number of rows 
			// affected in the database.
			System.out.println(updatedEntities);  
			
			
			// Neither UPDATE nor DELETE statements allow implicit joins.
			// Their form already disallows explicit joins too.
			
		});

		entityManagerFactory.close();
	}

}

/* 
delete_statement ::= 
    delete_clause [where_clause]

delete_clause ::=
    DELETE FROM entity_name [[AS] identification_variable]
*/

