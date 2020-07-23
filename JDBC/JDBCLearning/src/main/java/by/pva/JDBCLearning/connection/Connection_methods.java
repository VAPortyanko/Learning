package by.pva.JDBCLearning.connection;

import java.sql.Connection;
import java.sql.SQLException;

import by.pva.JDBCLearning.utils.SimpleDataSource;

public class Connection_methods {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		SimpleDataSource dataSource = new SimpleDataSource();
		Connection con = dataSource.getConnection();
		
		// Connection methods and constants.
		System.out.println("catalog - " + con.getCatalog());
		System.out.println("autocomit - " + con.getAutoCommit());
		System.out.println("holdability - " + con.getHoldability());
		System.out.println("schema - " + con.getSchema());
		System.out.println("TransactionIsolation - " + con.getTransactionIsolation());
		System.out.println("isReadOnly - " + con.isReadOnly());
		
		System.out.println();
		System.out.println("TRANSACTION_NONE " + Connection.TRANSACTION_NONE);
		System.out.println("TRANSACTION_READ_COMMITTED " + Connection.TRANSACTION_READ_COMMITTED);
		System.out.println("RANSACTION_READ_UNCOMMITTED " + Connection.TRANSACTION_READ_UNCOMMITTED);
		System.out.println("TRANSACTION_REPEATABLE_READ " + Connection.TRANSACTION_REPEATABLE_READ);
		System.out.println("TRANSACTION_SERIALIZABLE " + Connection.TRANSACTION_SERIALIZABLE);
		
		System.out.println();
		System.out.println("MetaData:DatabaseProductName - " + con.getMetaData().getDatabaseProductName());
		System.out.println("MetaData:DatabaseProductVersion - " + con.getMetaData().getDatabaseProductVersion());
		System.out.println("MetaData:DriverName - " + con.getMetaData().getDriverName());
		System.out.println("MetaData:DriverVersion - " + con.getMetaData().getDriverVersion());
		System.out.println("MetaData:MaxConnections - " + con.getMetaData().getMaxConnections());
		System.out.println("MetaData:Url - " + con.getMetaData().getURL());
		
		System.out.println("\nIs closed? - " + con.isClosed());
		dataSource.closeConnection(con);
		System.out.println("Is closed? - " + con.isClosed());
		
	}
}
