package database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Database {
	protected Connection connection;
	public PreparedStatement stmt; 
	
    public Database() {
    	try {
         connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JavaProject", "postgres", "presario");
    	} catch (Exception e) {
	         e.printStackTrace();
	         System.err.println("Database connectivity error !");
	         System.exit(0);
	    }
    }
    
    public PreparedStatement Query(String sql)
    {
    	try {
	    	stmt = connection.prepareStatement(sql);
    	} catch (Exception e) {
	         e.printStackTrace();
	         System.err.println("Database connectivity error while preparing statements !");
	         System.exit(0);
	    }
		return stmt;
    }
}
