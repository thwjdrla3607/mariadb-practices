package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrdersBookVo;

public class OrdersBookDao {

	public List<OrdersBookVo> findAll() {
		List<OrdersBookVo> result = new ArrayList<>();
		List<String> resultString = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		try {
			conn = getConnection();

			String sql = "select member_no from orders";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				int member_no = rs.getInt(1);;
				int bookNumber = 0;

				sql = "select book_no from cart where member_no = ?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, member_no);
				rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					bookNumber = rs2.getInt(1);
				
					sql = "select title from book where no = ?";
					pstmt3 = conn.prepareStatement(sql);
					pstmt3.setInt(1, bookNumber);
					rs3 = pstmt3.executeQuery();
					if (rs3.next()) {
						String bookName = rs3.getString(1);
						if (resultString.contains(bookName) == false) {
							resultString.add(bookName);
							result.add(new OrdersBookVo(bookName));
						}
					}
				}
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
