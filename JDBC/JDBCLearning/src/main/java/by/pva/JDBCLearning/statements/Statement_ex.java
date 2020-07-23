package by.pva.JDBCLearning.statements;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Statement_ex {

	private final static String DATABASE_NAME = "javastudy"; 
	private final static String TIMEZONE = "Europe/Minsk";
	private final static String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useLegacyDatetimeCode=false&serverTimezone=" + TIMEZONE;
	private final static String USER_NAME = "root";
	private final static String PASSWORD = "password";

	public static void main(String... args) {

		try (Connection con = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
			Statement stmt = con.createStatement();) {

			ResultSet rs = stmt.executeQuery("SELECT * FROM contact order by id");
            
			while (rs.next()) {

				int id = rs.getInt("id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				Date birth_date = rs.getDate("birth_date");
				
				System.out.println(id + " - " + first_name + " " + last_name + " - " + birth_date);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
