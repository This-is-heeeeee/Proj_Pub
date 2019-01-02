package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.s2;

public class CustomerDAO {
	String url = "jdbc:mysql://localhost:3306/ProjectDB?characterEncoding=UTF-8&serverTimezone=UTC";
	String userName = "root";
	String password = "dlrjsgml1212";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs;
	String sql;
	
	
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
	
	public CustomerDAO(){
	
	}
	
	public CustomerVO selectCustomer(int tnum){
		connectDB();
		sql = "select * from Customer where tNum = ?";
		
		CustomerVO customer = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tnum);
			rs = pstmt.executeQuery();
			
			rs.next();
			customer = new CustomerVO();
			customer.settNum(rs.getInt("tNum"));
			customer.setNumofpeo(rs.getInt("numofpe"));
			customer.setSex(rs.getString("sex"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
		return customer;
	}
	
	public ArrayList<CustomerVO> selectAll(){
		connectDB();
		s2 s2;
		sql = "select * from Customer";
		
		ArrayList<CustomerVO> customerlist = new ArrayList<CustomerVO>();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomerVO c = new CustomerVO();
				c.settNum(rs.getInt("tNum"));
				c.setNumofpeo(rs.getInt("numofpe"));
				c.setSex(rs.getString("sex"));
				customerlist.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customerlist;
	}
	
	public boolean updateCustomer(CustomerVO customer) {
		connectDB();
		
		sql = "update Customer set numofpe = ?, sex = ? where tnum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customer.getNumofpeo());
			pstmt.setString(2, customer.getSex());
			pstmt.setInt(3, customer.gettNum());
			pstmt.executeUpdate();
			closeDB();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("실패ㅜㅜ");
			closeDB();
			return false;
		}
		
	}
	public void initCustomer(int tnum) {
		connectDB();
		
		sql = "update Customer set numofpe = ?, sex = ? where tNum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, "x");
			pstmt.setInt(3, tnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
	}
	

}
