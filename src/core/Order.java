package core;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import database.Database;

public class Order extends Database {
	public int OrderId, MaterialId, QuantityOrdered, toId;
	public Date OrderDate;
	public String Mode;
	
	public Order() {}
	
	public Order(int OrderId) {
		this.OrderId = OrderId; 
		try {
			String query = "SELECT * FROM Orders WHERE OrderId = ?"; 
			PreparedStatement stmt = Query(query);
			stmt.setInt(1, this.OrderId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				this.OrderDate = rs.getDate("orderdate");
				this.QuantityOrdered = rs.getInt("QuantityOrdered"); 
				
				if (rs.getInt("SupplierId") != 0) {
					this.toId = rs.getInt("SupplierId");
					this.Mode = "OUT"; 
				} else {
					this.toId = rs.getInt("SiteId");
					this.Mode = "IN"; 
				}
				this.MaterialId = rs.getInt("materialid");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Order(int OrderId, Date Orderdate, String Mode, int MaterialId, int qty, int toId)
	{
		this.OrderId = OrderId;
		this.OrderDate = Orderdate;
		this.Mode = Mode;
		this.MaterialId = MaterialId;
		this.QuantityOrdered = qty;
		this.toId = toId;
	}
	
	public Order(String Mode, int MaterialId, int qty, int toId)
	{
		this.Mode = Mode;
		this.MaterialId = MaterialId;
		this.QuantityOrdered = qty;
		this.toId = toId;
	}
	
	public boolean PlaceOrder()
	{
		try {
			String query = 
				"INSERT INTO Orders (OrderDate, Mode, MaterialId, quantityordered, supplierid, siteid) VALUES ((SELECT CURRENT_DATE), ?, ?, ?, ?, ?)"; 
			PreparedStatement stmt = Query(query);
			stmt.setString(1, this.Mode);
			stmt.setInt(2, this.MaterialId);
			stmt.setInt(3, this.QuantityOrdered);
			
			if (Mode == "OUT")
				{
					stmt.setNull(4, Types.NULL);
					stmt.setInt(5, this.toId);
				}
			else
			{
				stmt.setInt(4, this.toId);
				stmt.setNull(5, Types.NULL);
				
			}
			stmt.execute();
			return true; 
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean TransactOrder()
	{
		try {
			String query = "INSERT INTO Transaction (OrderId, FullfilledDate) VALUES (?, (SELECT CURRENT_DATE))"; 
			PreparedStatement stmt = Query(query);
			stmt.setInt(1, this.OrderId);
			Material m = new Material(MaterialId);
			m.updateQuantity(this.QuantityOrdered); 
			stmt.execute(); 
			return true; 
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	// Returns all pending orders to suppliers in table
	public ArrayList<Order> getPendingOrders ()
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			
			ResultSet rs = Query("SELECT * FROM orders WHERE mode = 'IN' AND siteid IS null AND orderid NOT IN (SELECT Orderid FROM Transaction) ;").executeQuery();
			int i = 0;
			while(rs.next())
			{
				Order obj = new Order(rs.getInt(1), rs.getDate(2), "IN", rs.getInt("materialid"), rs.getInt("quantityordered"), rs.getInt("supplierid"));
				orders.add(obj); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return orders;
	} 
		
	// Returns all fulfilled orders by suppliers
	public ArrayList<Order> getPastOrders ()
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			
			ResultSet rs = Query("SELECT * FROM orders WHERE mode = 'IN' AND siteid IS null AND orderid IN (SELECT Orderid FROM Transaction) ;").executeQuery();
			int i = 0;
			while(rs.next())
			{
				Order obj = new Order(rs.getInt(1), rs.getDate(2), "IN", rs.getInt("materialid"), rs.getInt("quantityordered"), rs.getInt("supplierid"));
				orders.add(obj); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return orders;
	} 
	
	public boolean deleteOrder()
	{
		try {
			String query = "DELETE FROM Orders WHERE OrderId = ?"; 
			PreparedStatement stmt = Query(query);
			stmt.setInt(1, this.OrderId);
			stmt.execute();
			return true; 
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Order> getSitePendingOrders (int SiteId)
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			String query = "SELECT * FROM orders WHERE SiteId = ? AND OrderId NOT IN (SELECT OrderId FROM Transaction)"; 
			PreparedStatement stmt = Query(query);
			stmt.setInt(1, SiteId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				orders.add(new Order(rs.getInt("OrderId")));
			}
			System.out.println(orders); 
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return orders;
	}
	
	public ArrayList<Order> getSitePastOrders (int SiteId)
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			String query = "SELECT * FROM orders WHERE SiteId = ? AND OrderId IN (SELECT OrderId FROM Transaction)"; 
			PreparedStatement stmt = Query(query);
			stmt.setInt(1, SiteId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				orders.add(new Order(rs.getInt("OrderId")));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return orders;
	}
	
	public ArrayList<Order> getSitePendingOrders ()
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			String query = "SELECT * FROM orders WHERE OrderId NOT IN (SELECT OrderId FROM Transaction)"; 
			PreparedStatement stmt = Query(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				orders.add(new Order(rs.getInt("OrderId")));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return orders;
	}
}
