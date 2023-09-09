package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;

public class CartDao {

	public void insert(CartVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {
			int bookNumber = 0;
			int memberNumber = 0;
			
			conn = getConnection();
			
			String sql = "select no from book where title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBook());
			rs = pstmt.executeQuery();
			if (rs.next())
				bookNumber = rs.getInt(1);
			
			sql = "select no from member where name = ?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setString(1, vo.getMember());
			rs2 = pstmt2.executeQuery();
			if (rs2.next())
				memberNumber = rs2.getInt(1);

			sql = "insert into cart values(null, ?, ?, ?)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, vo.getCount());
			pstmt3.setInt(2, memberNumber);
			pstmt3.setInt(3, bookNumber);
			pstmt3.executeQuery();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null) 
					pstmt.close();
				if(pstmt2 != null) 
					pstmt2.close();
				if(pstmt3 != null) 
					pstmt3.close();
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<CartVo> findAll() {
		List<CartVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		try {
			conn = getConnection();

			String sql = "select no, member_no, book_no, count from cart";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				String memberName = null;
				int member_no = rs.getInt(2);
				sql = "select name from member where no = ?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, member_no);
				rs2 = pstmt2.executeQuery();
				if (rs2.next())
					memberName = rs2.getString(1);
				
				String bookName = null;
				int book_no = rs.getInt(3);
				sql = "select title from book where no = ?";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1, book_no);
				rs3 = pstmt3.executeQuery();
				if (rs3.next())
					bookName = rs3.getString(1);

				result.add(new CartVo(rs.getInt(1),
						memberName,
						bookName,
						rs.getInt(4)));
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) 
					rs.close();
				if(rs2 != null) 
					rs2.close();
				if(rs3 != null) 
					rs3.close();
				if(pstmt != null) 
					pstmt.close();
				if(pstmt2 != null) 
					pstmt2.close();
				if(pstmt3 != null) 
					pstmt3.close();
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
				if(pstmt != null) 
					pstmt.close();
				if(conn != null) 
					conn.close();
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
