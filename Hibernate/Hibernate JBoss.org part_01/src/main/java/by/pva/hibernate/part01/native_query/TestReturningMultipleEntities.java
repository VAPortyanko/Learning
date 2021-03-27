package by.pva.hibernate.part01.native_query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.hibernate.Session;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Partner;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestReturningMultipleEntities extends BaseTest {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// the result set column names are assumed to be the same as the column names specified in the mapping document. 
			// This can be problematic for SQL queries that join multiple tables since the same column names can appear in more than one table.
            // Column alias injection is needed in the following query which otherwise throws NonUniqueDiscoveredSqlAliasException.
			try {
				List<Object> entities = entityManager.createNativeQuery(
					"SELECT * " +
					"FROM Persons44 pr, Partners pt " +
					"WHERE pr.name = pt.name")
				.getResultList();
			} catch (PersistenceException e) {
				System.out.println(e.getMessage());
			}

			// The query was intended to return all Person and Partner instances with the same name. 
			// The query fails because there is a conflict of names since the two entities are mapped 
			// to the same column names (e.g. id, name, version).
			
			// The following form is not vulnerable to column name duplication:
			List<Object> entities = entityManager.unwrap(Session.class).createNativeQuery(
				"SELECT {pr.*}, {pt.*} " +
				"FROM Persons44 pr, Partners pt " +
				"WHERE pr.name = pt.name")
			.addEntity("pr", Person.class)
			.addEntity("pt", Partner.class)
			.list();

			entities.forEach(e -> System.out.println("Person:" + e));

			// There’s no such equivalent in JPA because the javax.persistence.Query interface does not define an addEntity method equivalent.

		});

		entityManagerFactory.close();
	}
	
}