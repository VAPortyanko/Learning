package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.List;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestExpressions_GroupBy extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// The GROUP BY clause allows building aggregated results for various value groups. 
			// As an example, consider the following queries:
			
			Long totalDuration = entityManager.createQuery(
				"select sum(c.duration) " +
				"from Call c ", Long.class)
			.getSingleResult();
			
			List<Object[]> personTotalCallDurations1 = entityManager.createQuery(
				"select p.name, sum(c.duration) " +
				"from Call c " +
				"join c.phone ph " +
				"join ph.person p " +
				"group by p.name", Object[].class)
			.getResultList();
			
			//It's even possible to group by entities!
			List<Object[]> personTotalCallDurations2 = entityManager.createQuery(
				"select p, sum(c.duration) " +
				"from Call c " +
				"join c.phone ph " +
				"join ph.person p " +
				"group by p", Object[].class)
			.getResultList();

			List<Object[]> personTotalCallDurations = entityManager.createQuery(
				"select p.name, sum(c.duration) " +
				"from Call c " +
				"join c.phone ph " +
				"join ph.person p " +
				"group by p.name " +
				"having sum(c.duration) > 150", Object[].class)
			.getResultList();

			System.out.println("Total duration: " + totalDuration);
			personTotalCallDurations1.forEach(e -> System.out.println("[GROUP BY (example 1)] Person: " + e[0] + ", calls duration: " + e[1]));
			personTotalCallDurations2.forEach(e -> System.out.println("[GROUP BY (example 2)] Person: " + e[0] + ", calls duration: " + e[1]));
			personTotalCallDurations.forEach(e -> System.out.println("[GROUP BY (example 3)] Person: " + e[0] + ", calls duration: " + e[1]));

		});

		entityManagerFactory.close();
	}
	
}