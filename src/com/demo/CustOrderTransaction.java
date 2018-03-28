package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustOrderTransaction {
	
	Connection con;
	Statement st;
	
	CustOrderTransaction() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fastrack", "root", "password");
			con.setAutoCommit(false);
			if(con != null) {
				System.out.println("Connected!");
				st = con.createStatement(); // Initialized to submit queries to DB
				int x = st.executeUpdate("insert into orders (orderdate, qty) values ('2018-01-10', 20)", Statement.RETURN_GENERATED_KEYS);
				//System.out.println(x);
				ResultSet keys = st.getGeneratedKeys();
				int newOrderKey = -1;
				while(keys.next()) {
					newOrderKey = keys.getInt(1);
				}
				System.out.println(newOrderKey);
				PreparedStatement ps = con.prepareStatement("insert into custorder values (?,?)");
				ps.setString(1, "1");
				ps.setInt(2, newOrderKey);
				int y = ps.executeUpdate();
				con.commit();
				System.out.println("Insrted Cust Order data!");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CustOrderTransaction();
	}

}
