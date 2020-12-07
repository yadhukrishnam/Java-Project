package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.Database;

public class ClsAuthentication extends Database {
	public String Authenticate(String Username, String Password)
	{
		try {
			String Query = "SELECT AccountType FROM Users WHERE UserName = ? AND pwd = ?";
			PreparedStatement stmt = Query(Query);
			stmt.setString(1, Username);
			stmt.setString(2, Password);
			ResultSet rs = stmt.executeQuery(); 
			rs.next();
			return rs.getString("AccountType");
		} 
	    catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
}
