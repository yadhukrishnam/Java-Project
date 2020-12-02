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
			String query = 
					"INSERT INTO Transaction (OrderId, FullfilledDate) VALUES (?, (SELECT CURRENT_DATE))"; 
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
	
}
