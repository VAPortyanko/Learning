package by.pva.hibernate.part01.hql_jpql.query_api;

import org.hibernate.Session;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestBasicCRUDStatementsUpdate extends BaseTest {

	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			final String oldName1 = "name1", newName1 = "name_1";
			final String oldName2 = "name2", newName2 = "name_2";
			final String oldName3 = "name3", newName3 = "name_3";
			
			int updatedEntities = entityManager.createQuery(
				"update Person44 p " +
				"set p.name = :newName1 " +
				"where p.name = :oldName1")
			.setParameter("oldName1", oldName1)
			.setParameter("newName1", newName1)
			.executeUpdate();
			
			// The int value returned by the executeUpdate() method indicates the number of entities
			// affected by the operation. This may or may not correlate to the number of rows 
			// affected in the database.
			System.out.println(updatedEntities);  
			
			int updatedEntities2 = entityManager.unwrap(Session.class).createQuery(
				"update Person44 " +
				"set name = :newName2 " +
				"where name = :oldName2")
			.setParameter( "oldName2", oldName2 )
			.setParameter( "newName2", newName2 )
			.executeUpdate();
			
			System.out.println(updatedEntities2);
			
			// UPDATE statements, by default, do not affect the version or the 
			// timestamp attribute values for the affected entities.
			// However, you can force Hibernate to set the version or timestamp
			// attribute values through the use of a versioned update.
			// This is achieved by adding the VERSIONED keyword after the UPDATE keyword.
			
			// Versioned updates is a Hibernate-specific feature and will not work in a portable manner.
			
			// Custom version types, org.hibernate.usertype.UserVersionType, are not allowed 
			// in conjunction with an update versioned statement.
			int updatedEntities3 = entityManager.unwrap(Session.class).createQuery(
				"update versioned Person44 " +
				"set name = :newName3 " +
				"where name = :oldName3")
			.setParameter("oldName3", oldName3)
			.setParameter("newName3", newName3)
			.executeUpdate();
		
			System.out.println(updatedEntities3);
			
			// Neither UPDATE nor DELETE statements allow implicit joins.
			// Their form already disallows explicit joins too.
			
		});

		entityManagerFactory.close();
	}

}

/* 
update_statement ::= 
	update_clause [where_clause]
update_clause ::= 
	UPDATE entity_name [[AS] identification_variable]
    SET update_item {, update_item}*
update_item ::= 
	[identification_variable.]{state_field | single_valued_object_field} = new_value
new_value ::= 
	scalar_expression | simple_entity_expression | NULL
*/

