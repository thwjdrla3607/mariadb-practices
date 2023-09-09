package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrdersVo;

public class OrdersDao {
	int memberNumber;
	int cartNumber;
	int totalPrice;
	
	public void getMemberNumber(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql = "select no from member where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			if (rs.next())
				memberNumber = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null) 
					pstmt.close();
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getCartNumber() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql = "select no from cart where member_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNumber);
			
			rs = pstmt.executeQuery();
			if (rs.next())
				cartNumber = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null) 
					pstmt.close();
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void getTotalPrice() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try {
			conn = getConnection();

			String sql = "select book_no, count from cart where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNumber);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookNumber = rs.getInt(1);
				int count = rs.getInt(2);
				
				sql = "select price from book where no = ?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, bookNumber);
				
				rs2 = pstmt2.executeQuery();
				int price = 0;
				if (rs2.next())
					price = rs2.getInt(1);
				
				totalPrice += price * count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(rs2 != null)
					rs2.close();
				if(pstmt != null) 
					pstmt.close();
				if(pstmt2 != null) 
					pstmt2.close();
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insert(OrdersVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		this.getMemberNumber(vo.getMemberName());
		this.getCartNumber();
		this.getTotalPrice();
		
		try {			
			conn = getConnection();

			String sql = "insert into orders values(null, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, totalPrice); 
			pstmt.setString(2, vo.getAddress());
			pstmt.setString(3, vo.getOrdersCode());
			pstmt.setInt(4, memberNumber);
			pstmt.setInt(5, cartNumber);
			pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null) 
					pstmt.close();
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<OrdersVo> findAll() {
		List<OrdersVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {
			conn = getConnection();

			String sql = "select no, totalPrice, address, ordersCode, member_no from orders";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				String memberName = "";
				int member_no = rs.getInt(5);
				
				sql = "select name from member where no = ?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, member_no);
				rs2 = pstmt2.executeQuery();
				if (rs2.next())
					memberName = rs2.getString(1);

				result.add(new OrdersVo(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						memberName));
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) 
					rs.close();
				if(pstmt != null) 
					pstmt.close();
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public void deleteByNo(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from member where email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		URL url = new URL();
		String ip;

		try {
			ip = url.getURL();
			Class.forName("org.mariadb.jdbc.Driver");
			String connQuery = "jdbc:mariadb://" + ip + ":3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(connQuery, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 

		return conn;
	}
}
