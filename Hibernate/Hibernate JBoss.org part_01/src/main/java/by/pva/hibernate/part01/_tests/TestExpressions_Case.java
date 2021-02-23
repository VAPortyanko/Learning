package by.pva.hibernate.part01._tests;

import java.util.List;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestExpressions_Case extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// Both the simple and searched forms are supported, as well as the two SQL defined abbreviated forms
			// (NULLIF and COALESCE).
			
			// The simple form has the following syntax:
            // CASE {operand} WHEN {test_value} THEN {match_result} ELSE {miss_result} END
			List<String> nickNames1 = entityManager.createQuery(
				"select " +
				"	case p.nickName " +
				"	when 'nickName1' " +
				"	then '<nickNameA>' " +
				"	else p.nickName " +
				"	end " +
				"from Person44 p", String.class)
			.getResultList();
			
			nickNames1.forEach(System.out::println);
			
			Person person = entityManager.find(Person.class, 2L);
			person.setNickName(null);
			entityManager.flush();
			entityManager.clear();
			
			List<String> nickNames2 = entityManager.createQuery(
				"select coalesce(p.nickName, '<no nick name>') " +
				"from Person44 p", String.class)
			.getResultList();
			
			nickNames2.forEach(System.out::println);

		});

		entityManagerFactory.close();
	}
	
}
