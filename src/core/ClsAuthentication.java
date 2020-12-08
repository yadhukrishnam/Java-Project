package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.Database;

public class ClsAuthentication extends Database {
	public boolean Authenticate(String Username, String Password)
	{
		try {
			String Query = "SELECT count(*) FROM Users WHERE UserId = ? AND Password = ?";
			PreparedStatement stmt = Query(Query);
			stmt.setString(1, Username);
			stmt.setString(2, Password);
			ResultSet rs = stmt.executeQuery(); 
			rs.next();
			if (rs.getInt(1) == 1) {
				return true;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	}
}
