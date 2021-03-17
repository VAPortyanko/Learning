package by.pva.hibernate.part01.criteria;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class SimpleCriteria extends BaseTest {

public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
			Root<Person> root = criteria.from(Person.class);
			criteria.select(root);
			criteria.where(builder.equal(root.get("name"), "name1"));
			
			List<Person> persons = entityManager.createQuery(criteria).getResultList();
			persons.forEach(System.out::println);
			
		});

		entityManagerFactory.close();
	}
}
