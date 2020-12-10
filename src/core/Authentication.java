package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.Database;

public class Authentication extends Database {
	
	public boolean isAuthenticated = false;
	public String Username, AccountType;
	private String Password; 
	public int UserId; 
	
	public Authentication(String Username, String Password)
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
	
	public boolean CreateAccount()
	{
		try {
			String Query = "INSERT INTO Users (UserId, AccountType, Username, Pwd) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = Query(Query);
			stmt.setInt(1, this.UserId);
			stmt.setString(2, this.AccountType);
			stmt.setString(3, this.Username);
			stmt.setString(4, this.Password);
			stmt.execute(); 
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}


}