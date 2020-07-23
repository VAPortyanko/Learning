package by.pva.JDBCLearning.statements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.pva.JDBCLearning.utils.SimpleDataSource;

public class CallableStatement_ex {
	public static void main(String[] args) throws SQLException {
		
		SimpleDataSource dataSource = new SimpleDataSource();
		Connection con = dataSource.getConnection();

		String query = "{CALL new_procedure()}";
		try (CallableStatement stmt = con.prepareCall(query);) {

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

//CREATE DEFINER=`root`@`localhost` PROCEDURE `new_procedure`()
//BEGIN
//SELECT id, name, age FROM javastudy.fortest;
//END