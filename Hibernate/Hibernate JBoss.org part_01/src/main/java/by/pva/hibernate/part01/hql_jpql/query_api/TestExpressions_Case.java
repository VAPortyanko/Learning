package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.List;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

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

			// The searched form has the following syntax:
			// CASE [ WHEN {test_conditional} THEN {match_result} ]* ELSE {miss_result} END
			person = entityManager.find(Person.class, 4L);
			person.setNickName(null);
			person.setName(null);
			entityManager.flush();
			entityManager.clear();
			
			List<String> nickNames3 = entityManager.createQuery(
				"select " +
				"	case " +
				"	when p.nickName is null " +
				"	then " +
				"		case " +
				"		when p.name is null " +
				"		then '<no nick name>' " +
				"		else p.name " +
				"		end" +
				"	else p.nickName " +
				"	end " +
				"from Person44 p", String.class)
			.getResultList();

			nickNames3.forEach(System.out::println);
			
			// coalesce can handle this more succinctly
			List<String> nickNames4 = entityManager.createQuery(
				"select coalesce( p.nickName, p.name, '<no nick name>' ) " +
				"from Person44 p", String.class )
			.getResultList();

			nickNames4.forEach(System.out::println);
			
			// CASE expressions with arithmetic operations
			// If you want to use arithmetic operations in the CASE expressions, 
			// you need to wrap the arithmetic operation in parentheses as illustrated by the following example
			// (without wrapping the arithmetic expression in ( and ), the entity query parser will not be able to parse the arithmetic operators):
			List<Long> values1 = entityManager.createQuery(
				"select " +
				"	case when p.nickName is null " +
				"		 then (p.id * 1000) " +
				"		 else p.id " +
				"	end " +
				"from Person44 p " +
				"order by p.id", Long.class)
			.getResultList();
			
			values1.forEach(System.out::println);
			
			// NULLIF expressions
			// NULLIF is an abbreviated CASE expression that returns NULL if its operands are considered equal.
			 List<String> nickNames5 = entityManager.createQuery(
			 	"select nullif(p.nickName, p.name) " +
			 	"from Person44 p", String.class )
			 .getResultList();
			 
			 nickNames5.forEach(System.out::println);
			 
			 // equivalent CASE expression
			 List<String> nickNames6 = entityManager.createQuery(
			 	"select " +
			 	"	case" +
			 	"	when p.nickName = p.name" +
			 	"	then null" +
			 	"	else p.nickName" +
			 	"	end " +
			 	"from Person44 p", String.class )
			 .getResultList();
			 
			 nickNames6.forEach(System.out::println);

			 // COALESCE is an abbreviated CASE expression that returns the first non-null operand. We have seen a number of COALESCE examples above.
			 
		});

		entityManagerFactory.close();
	}
	
}
