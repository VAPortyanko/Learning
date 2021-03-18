package by.pva.hibernate.part01.criteria;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class SimpleCriteria extends BaseTest {

public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			
			// 1) Selecting an entity.
			CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
			Root<Person> root = criteria.from(Person.class);
			// The call to the CriteriaQuery#select method in this example is unnecessary 
			// because root will be the implied selection since we have only a single query root.
			criteria.select(root);
			criteria.where(builder.equal(root.get("name"), "name1"));
						
			List<Person> persons = entityManager.createQuery(criteria).getResultList();
			persons.forEach(System.out::println);
			
			// 2) Selecting an expression.
			CriteriaQuery<String> criteria2 = builder.createQuery(String.class);
			Root<Person> root2 = criteria2.from(Person.class);
			criteria2.select(root2.get("nickName"));
			criteria2.where(builder.equal(root2.get("name"), "name3"));

			List<String> nickNames = entityManager.createQuery(criteria2).getResultList();
			nickNames.forEach(System.out::println);
			
			// 3) Selecting multiple values.
			// 3.1) selecting an array.
			CriteriaQuery<Object[]> criteria3 = builder.createQuery(Object[].class);
			Root<Person> root3 = criteria3.from(Person.class);
			
			Path<Long> idPath = root3.get("id");
			Path<String> nickNamePath = root3.get("nickName");
			
			criteria3.select(builder.array(idPath, nickNamePath ));
			criteria3.where(builder.equal(root3.get("name"), "name3"));
			
			List<Object[]> idAndNickNames = entityManager.createQuery(criteria3).getResultList();
			idAndNickNames.forEach(e -> System.out.println("Id = '" + e[0] + "', nickName = '" + e[1] + "'"));
			
			// 3.2) selecting an array using multiselect.
			CriteriaQuery<Object[]> criteria4 = builder.createQuery(Object[].class);
			Root<Person> root4 = criteria4.from(Person.class);

			Path<Long> idPath4 = root4.get("id");
			Path<String> nickNamePath4 = root4.get("nickName");

			criteria4.multiselect(idPath4, nickNamePath4);
			criteria4.where(builder.equal(root4.get("name"), "name1"));

			List<Object[]> idAndNickNames4 = entityManager.createQuery(criteria4).getResultList();
			idAndNickNames4.forEach(e -> System.out.println("Id = '" + e[0] + "', nickName = '" + e[1] + "'"));
			
			// 4) Selecting a wrapper.
			CriteriaQuery<PersonWrapper> criteria5 = builder.createQuery(PersonWrapper.class);
			Root<Person> root5 = criteria5.from(Person.class);

			Path<Long> idPath5 = root5.get("id");
			Path<String> nickNamePath5 = root5.get("nickName");

			criteria5.select(builder.construct(PersonWrapper.class, idPath5, nickNamePath5));
			//criteria5.where(builder.equal(root5.get("name"), "name5"));

			List<PersonWrapper> wrappers = entityManager.createQuery(criteria5).getResultList();
			wrappers.forEach(System.out::println);
		});

		entityManagerFactory.close();
	}
}



