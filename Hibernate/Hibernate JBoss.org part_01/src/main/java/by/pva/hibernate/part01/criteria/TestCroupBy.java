package by.pva.hibernate.part01.criteria;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestCroupBy extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
			Root<Person> root = criteria.from(Person.class);
			
			criteria.groupBy(root.get("address"));
			criteria.multiselect(root.get("address"), builder.count(root));
			
			List<Tuple> tuples = entityManager.createQuery( criteria ).getResultList();
			
			for ( Tuple tuple : tuples ) {
				String name = (String) tuple.get( 0 );
				Long count = (Long) tuple.get( 1 );
				System.out.println(name + ": " + count);
			}
			
		});

		entityManagerFactory.close();
	}
	
}