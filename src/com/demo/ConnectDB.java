package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectDB {
	
	Connection con;
	Statement st;
	
	ConnectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fastrack", "root", "password");
			if(con != null) {
				System.out.println("Connected!");
				st = con.createStatement(); // Initialized to submit queries to DB
				st.execute("create table orders (id int primary key auto_increment, orderdate date, qty int)");
				System.out.println("Table created...");
				int x = st.executeUpdate("insert into orders (orderdate, qty) values ('2018-01-10', 20)");
				x += st.executeUpdate("insert into orders (orderdate, qty) values ('2018-01-15', 25)");
				System.out.println(x + " rows inserted!");
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ConnectDB();
	}

}
