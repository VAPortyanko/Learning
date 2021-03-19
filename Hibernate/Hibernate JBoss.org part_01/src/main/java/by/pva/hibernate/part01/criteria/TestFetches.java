package by.pva.hibernate.part01.criteria;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Phone;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestFetches  extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Phone> criteria = builder.createQuery(Phone.class);
			Root<Phone> root = criteria.from(Phone.class);
			
			// Phone.person is a @ManyToOne
			Fetch<Phone, Person> personFetch = root.fetch("person");
			// Person.addresses is an @ElementCollection
			@SuppressWarnings("unused")
			Fetch<Person, String> addressesJoin = personFetch.fetch("addresses");
			
			criteria.where(builder.isNotEmpty(root.get("calls")));
			
			criteria.distinct(true);
			
			List<Phone> phones = entityManager.createQuery(criteria).getResultList();
			for (Phone phone:phones) {
				System.out.println(phone + " " + phone.getPerson().getName());
			}
		});

		entityManagerFactory.close();
	}
	
}