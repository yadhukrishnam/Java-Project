package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import core.Building;
import database.Database;

public class Site extends Database {
	public int SiteId;
	public String SiteName, SiteLocation;
	
	public Site()
	{
		this.SiteId = 0;
	}
	
	public Site(int SiteId)
	{
		try {
			System.out.println(SiteId); 
			PreparedStatement stmt = Query("SELECT * FROM Sites WHERE SiteId = ?");
			stmt.setInt(1, SiteId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				this.SiteId = SiteId;
				this.SiteName = rs.getString(2);
				this.SiteLocation = rs.getString(3);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
	
	public Site(int SiteId, String SiteName, String SiteLocation)
	{
		this.SiteId = SiteId;
		this.SiteName = SiteName;
		this.SiteLocation = SiteLocation;
	}
	
	
	public boolean newSite()
	{
		try {
			PreparedStatement stmt = Query("INSERT INTO Sites (SiteId, SiteName, SiteLocation) VALUES (?, ?, ?)");
			stmt.setInt(1, this.SiteId);
			stmt.setString(2, this.SiteName);
			stmt.setString(3, this.SiteLocation);
			return (stmt.executeUpdate() > 0);  
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return false;
	}
	
	public boolean updateSite()
	{
		try {
			PreparedStatement stmt = Query("UPDATE Sites SET SiteId = ? , SiteName = ?, SiteLocation = ? WHERE SiteId = ?");
			stmt.setInt(1, this.SiteId);
			stmt.setString(2, this.SiteName);
			stmt.setString(3, this.SiteLocation);
			stmt.setInt(4, this.SiteId);
			return (stmt.executeUpdate() > 0);  
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return false;
	}
	
	
	public ArrayList<Site> getSites ()
	{
		ArrayList<Site> sites = new ArrayList<Site>();
		try {
			
			ResultSet rs = Query("SELECT * FROM Sites").executeQuery();
			int i = 0;
			while(rs.next())
			{
				sites.add(new Site(rs.getInt(1), rs.getString(2), rs.getString(3))); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return sites;
	}
	
	public ArrayList<Site> searchSite (String Keyword)
	{
		ArrayList<Site> sites = new ArrayList<Site>();
		try {
			
			PreparedStatement stmt = Query("SELECT * FROM Sites WHERE SiteName LIKE ? OR SiteLocation LIKE ?");
			stmt.setString(1, "%" + Keyword + "%");
			stmt.setString(2, "%" + Keyword + "%"); 
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next())
			{
				sites.add(new Site(rs.getInt(1), rs.getString(2), rs.getString(3))); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return sites;
	}
	
	public ArrayList<String> getLocations()
	{
		ArrayList<String> location =  new ArrayList<String>();
		try {
			
			ResultSet rs = Query("SELECT Distinct sitelocation from sites;").executeQuery(); 
			while(rs.next())
			{
				location.add(rs.getString(1)); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return location;
	}
	public ArrayList<String> getSites (String location)
	{
		ArrayList<String> sites = new ArrayList<String>();
		try {
			
			PreparedStatement stmt = Query("SELECT SiteName FROM Sites WHERE sitelocation = ?");
			stmt.setString(1, location);
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next())
			{
				sites.add(rs.getString(1)); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return sites;
	}
	
	public int getSiteId(String SiteName)
	{
		int SiteId = 0; 
		try {
			
			PreparedStatement stmt = Query("SELECT SiteId FROM Sites WHERE sitename = ?");
			stmt.setString(1, SiteName);
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next())
			{
				System.out.print(rs.getInt(1));
				return rs.getInt(1);  
				
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return SiteId; 
	}
	
	public String getSiteLocation(String SiteName)
	{
		String SiteLocation = ""; 
		try {
			
			PreparedStatement stmt = Query("SELECT SiteLocation FROM Sites WHERE sitename = ?");
			stmt.setString(1, SiteName);
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next())
			{
				return rs.getString(1);  
				
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return SiteLocation; 
	}
}
