package by.pva.JDBCLearning.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SimpleDataSource {

	private final static ResourceBundle connectionData = ResourceBundle.getBundle("DataSourceProperties");

	public Connection getConnection() throws SQLException {

		Connection con = null;

		try {
			con = DriverManager.getConnection(connectionData.getString("url"), connectionData.getString("login"),
					connectionData.getString("password"));
		} catch (SQLException e) {
			System.out.println("Error getting connection!");
			throw e;
		}

		return con;
	}

	public void closeConnection(Connection con) throws SQLException {
		try {
			if (con != null)
				con.close();
			else
				System.out.println("Connection is null!");
		} catch (SQLException e) {
			System.out.println("Can't close connection!");
			throw e;
		}
	}
}
