package com.assist.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	private static final String URL="jdbc:mysql://127.0.0.1:3306/asssist";
	private static final String USER="root";
	private static final String PASSWORD="fangchen123";
	
	private static Connection conn=null;
	
	//��̬������ִ��
	static{
		try {
			//1.������������
			Class.forName("com.mysql.jdbc.Driver");
			//2.������ݿ������
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return conn;
	}
	
	public static void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn=null;
		}
	}
}
