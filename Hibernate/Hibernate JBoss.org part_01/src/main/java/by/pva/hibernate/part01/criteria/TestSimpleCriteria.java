package by.pva.hibernate.part01.criteria;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.Tuple;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestSimpleCriteria extends BaseTest {

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
			
// ToDo: This example doesn't work: 
//       (java.lang.ClassCastException: org.hibernate.hql.internal.ast.tree.SqlNode cannot be cast to org.hibernate.hql.internal.ast.tree.FromReferenceNode).
//       Why?
//			
// 3.3) Selecting a wrapper.
//			CriteriaQuery<PersonWrapper> criteria5 = builder.createQuery(PersonWrapper.class);
//			Root<Person> root5 = criteria5.from(Person.class);
//
//			Path<Long> idPath5 = root5.get("id");
//			Path<String> nickNamePath5 = root5.get("nickName");
//
//			criteria5.select(builder.construct(PersonWrapper.class, idPath5, nickNamePath5));
//			criteria5.where(builder.equal(root5.get("name"), "name1"));
//
//			List<PersonWrapper> idAndNickNames5 = entityManager.createQuery(criteria5).getResultList();

			// 3.4) Selecting a tuple.
			// The example uses the explicit createTupleQuery() of javax.persistence.criteria.CriteriaBuilder. 
			// An alternate approach is to use createQuery(Tuple.class).
			CriteriaQuery<Tuple> criteria6 = builder.createQuery(Tuple.class);
			Root<Person> root6 = criteria6.from(Person.class);

			Path<Long> idPath6 = root6.get("id");
			Path<String> nickNamePath6 = root6.get("nickName");
			idPath6.alias("id");
			nickNamePath6.alias("nickName");

			criteria6.multiselect(idPath6, nickNamePath6);
			criteria6.where(builder.equal(root6.get("name"), "name1"));

			List<Tuple> tuples = entityManager.createQuery(criteria6).getResultList();

			System.out.println(tuples.get(0).getElements());
			for (Tuple tuple : tuples) {
				Long id6_1 = tuple.get("id", Long.class);
				String nickName6_1 = tuple.get("nickName", String.class);
				System.out.println("id:" + id6_1 + ", nickName: " + nickName6_1);
			}
			// or using indices
			for ( Tuple tuple : tuples ) {
				Long id6_2 = (Long) tuple.get(0);
				String nickName6_2 = (String) tuple.get(1);
				System.out.println("id:" + id6_2 + ", nickName: " + nickName6_2);
			}
			
			// The javax.persistence.Tuple contract provides three forms of access to the underlying elements:
			// 			
			//    typed      - the Selecting a tuple example illustrates this form of access in the tuple.get(idPath) and tuple.get(nickNamePath) calls. 
			//			       This allows typed access to the underlying tuple values based on the javax.persistence.TupleElement expressions used to build the criteria.
			//    positional - Allows access to the underlying tuple values based on the position. 
			// 			       The simple Object get(int position) form is very similar to the access illustrated in Selecting an array and Selecting an array using multiselect. 
			//                 The <X> X get(int position, Class<X> type form allows typed positional access, 
			//    			   but based on the explicitly supplied type which the tuple value must be type-assignable to.
			//    aliased    - Allows access to the underlying tuple values based on (optionally) assigned alias.
			// 			       The example query did not apply an alias. An alias would be applied via the alias method on javax.persistence.criteria.Selection. 
			// 			       Just like positional access, there is both a typed (Object get(String alias)) and an untyped (<X> X get(String alias, Class<X> type)) form.
		
		});

		entityManagerFactory.close();
	}
}



