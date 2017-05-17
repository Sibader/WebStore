package com.duo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
       
   	private static final String driver = "com.mysql.jdbc.Driver";
   	private static final String url = "jdbc:mysql://localhost/shopping";
   	private static final String user = "root";
   	private static final String password = "520";
  
	//加载驱动
	public static Connection getConn() throws Exception{
		
		Class.forName(driver);
		
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void close(ResultSet rs,PreparedStatement pstmt,
			Connection conn) throws SQLException{
		if(rs!=null){
			rs.close();
		}
		if(pstmt!=null){
			pstmt.close();
		}
		if(conn!=null){
			conn.close();
		}	
	}
    public static void main(String[] args) {
    	try {
		      Connection connection= DBHelper.getConn();

		      if(connection!=null){
			
			   System.out.println("数据库连接成功");
		      }
		      else {
			  System.out.println("数据库连接失败");
		      }
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
