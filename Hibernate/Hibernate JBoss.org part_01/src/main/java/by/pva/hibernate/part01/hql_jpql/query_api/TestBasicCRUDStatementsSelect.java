package by.pva.hibernate.part01.hql_jpql.query_api;

import org.hibernate.Session;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestBasicCRUDStatementsSelect extends BaseTest {

	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			// 1) The simplest possible HQL SELECT statement is of the form:
			entityManager.unwrap(Session.class)
			             .createQuery("from Person44", Person.class)
			             .stream()
			             .forEach(System.out::println);
			
			// 2) The select statement in JPQL is exactly the same as for HQL except
			// that JPQL requires a select_clause, whereas HQL does not.
			entityManager.createQuery("select p " +
							          "from Person44 p", Person.class )
					.getResultList()
					.stream()
					.forEach(System.out::println);

			// 3) [Select] clause and parameter binding.
			entityManager.createQuery(
					    " select p"
			          + "    from Person44 p"
			          + " where p.name = 'name4'"
			          + "    or p.id = 1"
			          + "    or p.id = :id"
			          + " order by p.name desc"
			          , Person.class)
			.setParameter("id", 2L)
			.getResultList()
			.stream()
			.forEach(System.out::println);
			
		});

		entityManagerFactory.close();
	}

}

/* 
select_statement :: =
    [select_clause]
    from_clause
    [where_clause]
    [groupby_clause]
    [having_clause]
    [orderby_clause]
*/

