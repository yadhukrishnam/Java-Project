package core;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.Database;

public class ClsAuthentication extends Database {
	
	public boolean isAuthenticated = false;
	public String Username, AccountType;
	private String Password; 
	public int UserId; 
	
	public ClsAuthentication(String Username, String Password)
	{
		this.Username = Username;
		this.Password = Password; 
	}
	
	public void Authenticate()
	{
		try {
			String Query = "SELECT UserId, AccountType FROM Users WHERE UserName = ? AND pwd = ?";
			PreparedStatement stmt = Query(Query);
			stmt.setString(1, this.Username);
			stmt.setString(2, this.Password);
			ResultSet rs = stmt.executeQuery(); 
			
			while(rs.next()) {
				this.isAuthenticated = true;
				this.AccountType = rs.getString("AccountType"); 
				this.UserId = rs.getInt("UserId"); 
			}
		} 
	    catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}