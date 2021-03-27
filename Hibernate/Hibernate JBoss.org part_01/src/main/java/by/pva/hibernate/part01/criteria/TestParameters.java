package by.pva.hibernate.part01.criteria;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestParameters extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
			Root<Person> root = criteria.from(Person.class);
			
			ParameterExpression<String> nickNameParameter = builder.parameter(String.class);
			criteria.where(builder.equal(root.get("nickName"), nickNameParameter));
			
			TypedQuery<Person> query = entityManager.createQuery(criteria);
			query.setParameter(nickNameParameter, "nickName1");
			List<Person> persons = query.getResultList();
			
			persons.forEach(System.out::println);
			
		});

		entityManagerFactory.close();
	}
	
}