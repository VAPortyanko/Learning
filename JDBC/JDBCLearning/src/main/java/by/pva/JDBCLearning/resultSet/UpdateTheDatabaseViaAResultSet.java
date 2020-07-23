package by.pva.JDBCLearning.resultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTheDatabaseViaAResultSet {

	private final static String DATABASE_NAME = "javastudy";
	private final static String TIMEZONE = "Europe/Minsk";
	private final static String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME	+ "?useLegacyDatetimeCode=false&serverTimezone=" + TIMEZONE;
	private final static String USER_NAME = "root";
	private final static String PASSWORD = "password";

	public static void main(String... args) {

		try (Connection con = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
			 Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);) {

			ResultSet uprs = stmt.executeQuery("SELECT * FROM FORTEST");

			while (uprs.next()) {
				
				int age = uprs.getInt("AGE");
				uprs.updateInt("AGE", age + 1);
				uprs.updateRow();
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}

