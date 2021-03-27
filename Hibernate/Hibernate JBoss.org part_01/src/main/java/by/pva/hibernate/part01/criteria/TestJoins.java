package by.pva.hibernate.part01.criteria;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Phone;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestJoins  extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Phone> criteria = builder.createQuery(Phone.class);
			Root<Phone> root = criteria.from(Phone.class);
			
			// Phone.person is a @ManyToOne
			Join<Phone, Person> personJoin = root.join("person");
			// Person.addresses is an @ElementCollection
			@SuppressWarnings("unused")
			Join<Person, String> addressesJoin = personJoin.join("addresses");
			
			criteria.where(builder.and(builder.isNotEmpty(root.get("calls")), 
					                   builder.equal(personJoin.get("name"), "name1")
					                   ));
			
			criteria.distinct(true);
			
			List<Phone> phones = entityManager.createQuery(criteria).getResultList();
			for (Phone phone:phones) {
				System.out.println(phone + " " + phone.getPerson().getName());
			}
			
		});

		entityManagerFactory.close();
	}
	
}