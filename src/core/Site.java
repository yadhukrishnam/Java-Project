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
}
