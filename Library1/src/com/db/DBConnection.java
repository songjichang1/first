package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 *  DB 연결담당 및 닫기 담당 클래스 
 *    
 */
public  class DBConnection {

	public Connection con;

	public  DBConnection() {

		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// DB 연결
			String user  = "c##song1"; 
			String pw = "1234";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			con = null;

			con = DriverManager.getConnection(url, user, pw);
			System.out.println("DB 연결 성공");

		}catch(Exception e) {

			e.printStackTrace();
		}

	}
	//DB 연결 해제 
	public void close(ResultSet rs) {

		try {

			if(rs !=null) rs.close();


			System.out.println("JDBC 자원 해제");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close(PreparedStatement psmt) {
		try {
			if(psmt !=null) psmt.close();

			System.out.println("JDBC 자원 해제");

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	public void close(Connection con) {
		try {

			if(con !=null) con.close();
			System.out.println("JDBC 자원 해제");

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
