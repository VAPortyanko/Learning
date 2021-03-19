package by.pva.hibernate.part01.criteria;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import _by.pva.hibernate.part01.hql_jpql.domain_model.*;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestRoots  extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// Adding multiple roots.
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
			
			// With multiple roots a cross join will be added. 
			Root<Person> personRoot = criteria.from(Person.class);
			Root<Partner> partnerRoot = criteria.from(Partner.class);
			criteria.multiselect(personRoot, partnerRoot);
			
//			Predicate personRestriction = builder.and(
//				builder.equal(personRoot.get("address"), "Earth1"),
//				builder.isNotEmpty( personRoot.get("phones"))
//			);
//			Predicate partnerRestriction = builder.and(
//				builder.like(partnerRoot.get("name"), "Partner1"),
//				builder.equal(partnerRoot.get("version"), 0)
//			);
//			criteria.where(builder.and( personRestriction, partnerRestriction));
			
			List<Tuple> tuples = entityManager.createQuery(criteria).getResultList();
			for (Tuple tuple: tuples) {
				Person person = (Person) tuple.get(0);
				Partner partner = (Partner) tuple.get(1); 
				System.out.println("Person_id = " + person.getId() + ", Partner_id = " + partner.getId());
			}


			
			

			
		});

		entityManagerFactory.close();
	}
	
}