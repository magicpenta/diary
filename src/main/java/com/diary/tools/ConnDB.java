package com.diary.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC 封装类
 * @author panda
 *
 */
public class ConnDB {
	
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/db_9griddiary?useUnicode=true";
	private static String USER = "root";
	private static String PASSWORD = "362400";
	
	public static String propFileName = "connDB.properties";
	public static Properties prop = new Properties();
	
	public ConnDB()
	{
		
	}
	
	public static Connection getConnection()
	{
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if ( conn == null )
		{
			System.out.println("警告：获取数据库连接失败！");
		}
		return conn;
	}
	
	public ResultSet executeQuery(String sql)
	{
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int executeUpdate(String sql)
	{
		int result = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			result = 0;
		}
		return result;
	}
	
	public void close()
	{
		try {
			if ( rs != null )
			{
				rs.close();
			}
			if ( stmt != null )
			{
				stmt.close();
			}
			if ( conn != null )
			{
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
