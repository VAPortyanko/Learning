package by.pva.hibernate.part01.hql_jpql.hibenrate_query_api;

import javax.persistence.TypedQuery;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.Phone;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestHibernateQueryApi extends BaseTest {

	public static void main(String[] args) {

		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// Hibernate offers a specific @NamedQuery annotation which provides ways to configure
			// various query features, like flush mode, cacheability, time out interval.
			// See the by.pva.hibernate.part01.hql_jpql.domain_model.Phone class.
			TypedQuery<Phone> query = entityManager.createNamedQuery("get_phone_by_number", Phone.class);
			Phone phone = query
					.setParameter("number", "+375 (33) 012-38-22")
					.getSingleResult();
			
			System.out.println(phone);

		});

		entityManagerFactory.close();

	}

}
