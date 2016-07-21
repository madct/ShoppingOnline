package com.so.model;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class connectionDb {
	private Connection ct;
	public Connection getCon(){
		try {
//			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433;databaseName=shoppingOnline","sa","123456");
			Context ic=new InitialContext();
			Context cont=(Context)ic.lookup("java:/comp/env");
			DataSource source=(DataSource)cont.lookup("jdbc/ShopOnline");
			ct=source.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
}
