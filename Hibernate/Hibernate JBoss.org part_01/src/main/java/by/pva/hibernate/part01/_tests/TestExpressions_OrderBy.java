package by.pva.hibernate.part01._tests;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

import java.util.List;

import _by.pva.hibernate.part01.hql_jpql.domain_model.*;

public class TestExpressions_OrderBy extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"order by p.name desc nulls first", Person.class)
			.getResultList();
			
			List<Object[]> personTotalCallDurations = entityManager.createQuery(
				"select p.name, sum(c.duration) as total " +
				"from Call c " +
				"join c.phone ph " +
				"join ph.person p " +
				"group by p.name " +
				"order by total", Object[].class)
			.getResultList();
			
			persons.forEach(e -> System.out.println("[ORDER BY NAME]: " + e));
			personTotalCallDurations.forEach(e -> System.out.println("[ORDER BY DURATION]: " + e[0] + " : " + e[1]));

		});

		entityManagerFactory.close();
	}
	
}

/* 
 The results of the query can also be ordered. The ORDER BY clause is used to specify the selected values to be used to order the result. 
 The types of expressions considered valid as part of the ORDER BY clause include:
    - state fields
   - component/embeddable attributes
   - scalar expressions such as arithmetic operations, functions, etc.
   - identification variable declared in the select clause for any of the previous expression types
Additionally, JPQL says that all values referenced in the ORDER BY clause must be named in the SELECT clause. 
HQL does not mandate that restriction, but applications desiring database portability should be aware that not all databases support referencing values
 in the ORDER BY clause that are not referenced in the select clause.

Individual expressions in the order-by can be qualified with either ASC (ascending) or DESC (descending) to indicate the desired ordering direction. 
Null values can be placed in front or at the end of the sorted set using NULLS FIRST or NULLS LAST clause respectively.
*/