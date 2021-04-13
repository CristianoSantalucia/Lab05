package it.polito.tdp.anagrammi.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCconnect
{ 
	public static Connection getConnection() throws SQLException
	{
		String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root&password=root";
		return DriverManager.getConnection(jdbcURL);
	}
}
