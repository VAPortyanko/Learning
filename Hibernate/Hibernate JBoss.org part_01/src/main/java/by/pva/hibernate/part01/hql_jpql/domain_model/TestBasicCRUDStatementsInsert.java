package by.pva.hibernate.part01.hql_jpql.domain_model;

import org.hibernate.Session;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

// There is no JPQL equivalent to HQL-style INSERT statements.
public class TestBasicCRUDStatementsInsert extends BaseTest {

	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			int insertedEntities = entityManager.unwrap(Session.class).createQuery(
				"insert into Partner (id, name) " +
				"select p.id, p.name " +
				"from Person44 p ")
			.executeUpdate();
			
			System.out.println("Inserted Entities: " + insertedEntities);
			
		});

		entityManagerFactory.close();
	}

}

/* 
insert_statement ::=
    insert_clause select_statement

insert_clause ::=
    INSERT INTO entity_name (attribute_list)

attribute_list ::=
    state_field[, state_field ]*
*/

// For entities involved in mapped inheritance, only attributes directly defined 
// on the named entity can be used in the attribute_list. 
// Superclass properties are not allowed and subclass properties do not make sense. 
// In other words, INSERT statements are inherently non-polymorphic.

// For the id attribute, the insert statement gives you two options. 
// You can either explicitly specify the id property in the attribute_list, 
// in which case its value is taken from the corresponding select expression, 
// or omit it from the attribute_list in which case a generated value is used.

// For optimistic locking attributes, the insert statement again gives you two options. 
// You can either specify the attribute in the attribute_list in which case its value 
// is taken from the corresponding select expressions or omit it from the attribute_list 
// in which case the seed value defined by the corresponding org.hibernate.type.VersionType is used.