package by.pva.hibernate.part01._tests;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;

import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestUsingStoresProceduresForQuering  extends BaseTest {

	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			final Long ID = 1L;
			
			entityManager.createNativeQuery("CREATE PROCEDURE sp_count_phones (" +
		    "   IN personId INT, " +
		    "   OUT phoneCount INT " +
		    ") " +
		    "BEGIN " +
		    "    SELECT COUNT(*) INTO phoneCount " +
		    "    FROM Phones27 p " +
		    "    WHERE p.person_id = personId; " +
		    "END").executeUpdate();
			
			// Calling a MySQL stored procedure with OUT parameter type using JPA.
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_count_phones");
			query.registerStoredProcedureParameter("personId", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("phoneCount", Long.class, ParameterMode.OUT);

			query.setParameter("personId", ID);
			query.execute();
			Long phoneCount1 = (Long) query.getOutputParameterValue("phoneCount");
			System.out.println("Phone count of the person with id=" + ID + ": " + phoneCount1);

			// Calling a MySQL stored procedure with OUT parameter type using Hibernate.
			Session session = entityManager.unwrap(Session.class);

			ProcedureCall call = session.createStoredProcedureCall("sp_count_phones");
			call.registerParameter("personId", Long.class, ParameterMode.IN).bindValue(ID);
			call.registerParameter("phoneCount", Long.class, ParameterMode.OUT);

			Long phoneCount2 = (Long) call.getOutputs().getOutputParameterValue("phoneCount");
			System.out.println("Phone count of the person with id=" + ID + ": " + phoneCount2);
			
			entityManager.createNativeQuery("DROP PROCEDURE IF EXISTS sp_count_phones;").executeUpdate();
		});

		entityManagerFactory.close();
	}
	
}