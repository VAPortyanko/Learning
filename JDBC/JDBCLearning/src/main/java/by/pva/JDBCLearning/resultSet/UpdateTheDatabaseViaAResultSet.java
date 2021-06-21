package by.pva.JDBCLearning.resultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTheDatabaseViaAResultSet {

	private final static String DATABASE_NAME = "jdbc";
	private final static String TIMEZONE = "Europe/Minsk";
	private final static String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME	+ "?useLegacyDatetimeCode=false&serverTimezone=" + TIMEZONE;
	private final static String USER_NAME = "root";
	private final static String PASSWORD = "password";

	public static void main(String... args) {

		try (Connection con = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
			 Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);) {

			ResultSet uprs = stmt.executeQuery("select * from contact");

			System.out.println("**************************************************");
			
			while (uprs.next()) {
				
				int id = uprs.getInt("id");
				String fn = uprs.getString("first_name");
				String ln = uprs.getString("last_name");
				String bd = uprs.getString("birth_date");
				int version = uprs.getInt("version");
				
				System.out.println("Id:" + id);
				System.out.println("First name:" + fn);
				System.out.println("Last name:" + ln);
				System.out.println("Birth date:" + bd);
				System.out.println("Version:" + version);
				System.out.println("**************************************************");
				
				uprs.updateInt("version", version + 1);
				uprs.updateRow();
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}


