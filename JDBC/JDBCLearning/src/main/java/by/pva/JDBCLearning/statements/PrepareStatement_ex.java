package by.pva.JDBCLearning.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.pva.JDBCLearning.utils.SimpleDataSource;

public class PrepareStatement_ex {
	public static void main(String[] args) throws SQLException {

		SimpleDataSource dataSource = new SimpleDataSource();
		Connection con = dataSource.getConnection();

		String query = "select id, name, age from fortest where name =?";

		try (PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setString(1, "Petia");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("AGE");
				System.out.println("Id: " + id + ", name: " + name + ", age: " + age);
			}
		}

		dataSource.closeConnection(con);
	}
}
