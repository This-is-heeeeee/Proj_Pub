package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDAO {
	String url = "jdbc:mysql://localhost:3306/ProjectDB?characterEncoding=UTF-8&serverTimezone=UTC";
	String userName = "root";
	String password = "dlrjsgml1212";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs;
	String sql;
	
	public MenuDAO(){
		
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
	
	public MenuVO selectMenu(String product){
		connectDB();
		sql = "select * from Menu where product = ?";
		
		MenuVO menu = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product);
			rs = pstmt.executeQuery();
			
			rs.next();
			menu = new MenuVO();
			menu.setProduct(rs.getString("product"));
			menu.setPrice(rs.getInt("price"));
			menu.setCategory(rs.getString("category"));
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeDB();
		return menu;
	}
	
	public boolean insertMenu(MenuVO menu) {
		connectDB();
		sql = "insert into Customer values(?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu.getProduct());
			pstmt.setInt(2, menu.getPrice());
			pstmt.setString(3, menu.getCategory());
			pstmt.executeUpdate();
			closeDB();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeDB();
			return false;
		}
		
	}
}
