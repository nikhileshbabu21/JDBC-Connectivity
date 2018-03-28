package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class SelectData {
	
	Connection con;
	Statement st;
	
	SelectData() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fastrack", "root", "password");
			if(con != null) {
				System.out.println("Connected!");
				st = con.createStatement(); // Initialized to submit queries to DB
				ResultSet rs = st.executeQuery("select * from orders");
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				for(int i=1;i<=count;i++) {
					System.out.println(rsmd.getColumnName(i) + ":" + rsmd.getColumnType(i));
				}
				while(rs.next()) {
					for(int i=1;i<=count;i++) {
						System.out.println(rsmd.getColumnName(i) + "==>" + rs.getString(i));
					}
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SelectData();
	}

}
