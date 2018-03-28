package com.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class CallProc {
	
	Connection con;
	CallableStatement cs;
	
	CallProc() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.17.30.119:1521:XE", "system", "password123");
			//con = DriverManager.getConnection("jdbc:oracle:thin:@172.17.30.32:1521:orcl", "system", "Oracle_1");
			if(con != null) {
				System.out.println("Connected to ORCL!");
				CallableStatement cs = con.prepareCall("{ call demoproc3(?,?,?) }");
				cs.setInt(1, 200);
				cs.setInt(2, 1505);
				cs.registerOutParameter(3, Types.INTEGER);
				cs.execute();
				int output = cs.getInt(3);
				System.out.println(output);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CallProc();
	}

}
