package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriceDAO {
	String url = "jdbc:mysql://localhost:3306/ProjectDB?characterEncoding=UTF-8&serverTimezone=UTC";
	String userName = "root";
	String password = "dlrjsgml1212";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs;
	String sql;
	
	public PriceDAO(){
		
	}
	
	void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			System.out.println("DB Connection OK!");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB Driver Error!");
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("DB Connection Error!");	
		}
	}
	void closeDB() {
		try {
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
			if(con != null) con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public PriceVO selectPrice(int tnum){
		connectDB();
		sql = "select * from Price where tNum = ?";
		
		PriceVO price = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tnum);
			rs = pstmt.executeQuery();
			
			rs.next();
			price = new PriceVO();
			price.settNum(rs.getInt("tNum"));
			price.setTotal(rs.getInt("total"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
		return price;
	}
	
	public void updatePrice(int tnum, int total) {
		connectDB();
		sql = "update Price set total = ? where tNum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, total);
			pstmt.setInt(2, tnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
	}

	public void initPrice(int tnum) {
		connectDB();
		sql = "update Price set total = ? where tNum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, tnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
	}
}
