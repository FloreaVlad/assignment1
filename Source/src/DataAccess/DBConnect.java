package DataAccess;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnect {

	public static Connection getConnection() throws SQLException {
		Connection conn = null;

		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webassign1", "postgres", "gabriel");
		} catch(Exception e){
			e.printStackTrace();
		}

		return conn;
		/*
		Properties connectionProps = new Properties();
		connectionProps.put("user", "root");
		connectionProps.put("password", "marius");

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment1",
				connectionProps);
		
		return conn;
		*/
	}
	
}

