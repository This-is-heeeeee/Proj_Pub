package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class CorderDAO {
	String url = "jdbc:mysql://localhost:3306/ProjectDB?characterEncoding=UTF-8&serverTimezone=UTC";
	String userName = "root";
	String password = "dlrjsgml1212";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs;
	String sql;
	
	public CorderDAO(){
		
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
	
	public ArrayList<CorderVO> selectAll(){
		connectDB();
		sql = "select * from Corder";
		
		ArrayList<CorderVO> orderList = new ArrayList<CorderVO>();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CorderVO corder = new CorderVO();
				corder.setoNum(rs.getInt("oNum"));
				corder.settNum(rs.getInt("tNum"));
				corder.setProduct(rs.getString("product"));
				corder.setAmount(rs.getInt("amount"));
				orderList.add(corder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
		return orderList;
	}
	
	public ArrayList<CorderVO> selectCorder(int tnum){
		connectDB();
		sql = "select * from Corder where tNum = ?";
		
		ArrayList<CorderVO> orderList = new ArrayList<CorderVO>();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tnum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CorderVO corder = new CorderVO();
				corder.setoNum(rs.getInt("oNum"));
				corder.settNum(rs.getInt("tNum"));
				corder.setProduct(rs.getString("product"));
				corder.setAmount(rs.getInt("amount"));
				orderList.add(corder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
		return orderList;
	}
	
	public boolean insertCorder(CorderVO corder) {
		connectDB();
		sql = "insert into Corder values(?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, corder.getoNum());
			pstmt.setInt(2, corder.gettNum());
			pstmt.setString(3, corder.getProduct());
			pstmt.setInt(4, corder.getAmount());
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
	
	public void initCorder(int tnum) {
		connectDB();
		sql = "delete from Corder where tNum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
	}
	
	public int getMaxOnum() {
		connectDB();
		sql = "select MAX(oNum) from Corder";
		
		int max = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			max = rs.getInt("MAX(oNum)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
		return max;
	}
}
