package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.List;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestExpressions_AggregateFunctions extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			Object[] callStatistics = entityManager.createQuery(
				"select " +
				"	count(c), " +
				"	sum(c.duration), " +
				"	min(c.duration), " +
				"	max(c.duration), " +
				"	avg(c.duration)  " +
				"from Call c ", Object[].class )
			.getSingleResult();
			
			for (Object o: callStatistics) {
				System.out.println(o);
			}
			
			Long phoneCount = entityManager.createQuery(
				"select count(distinct c.phone) " +
				"from Call c ", Long.class )
			.getSingleResult();
			
			System.out.println(phoneCount);
			
			List<Object[]> callCount = entityManager.createQuery(
				"select p.number, count(c) " +
				"from Call c " +
				"join c.phone p " +
				"group by p.number", Object[].class )
			.getResultList();
			
			callCount.forEach(e -> System.out.println(e[0] + " " + e[1]));

		});

		entityManagerFactory.close();
	}
	
}

/*
Aggregate functions are also valid expressions in HQL and JPQL. 
The semantics is the same as their SQL counterpart. 
The supported aggregate functions are:

   COUNT (including distinct/all qualifiers) - The result type is always Long.
   AVG - The result type is always Double.
   MIN - The result type is the same as the argument type.
   MAX - The result type is the same as the argument type.
   SUM - The result type of the SUM() function depends on the type of the values being summed. 

For integral values (other than BigInteger), the result type is Long.
For floating point values (other than BigDecimal) the result type is Double.
For BigInteger values, the result type is BigInteger.
For BigDecimal values, the result type is BigDecimal.
*/