package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;

public class BookDao {

	public void insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {
			int categoryNumber = 0;
			conn = getConnection();
			
			String sql = "select no from category where categoryName = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCategoryName());
			
			rs = pstmt.executeQuery();
			if (rs.next())
				categoryNumber = rs.getInt(1);
			
			sql = "insert into book values(null, ?, ?, ?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setString(1, vo.getTitle());
			pstmt2.setInt(2, vo.getPrice());
			pstmt2.setInt(3, categoryNumber);
			pstmt2.executeQuery();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {
			conn = getConnection();

			String sql = "select no, title, price, category_no from book";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				String categoryName = "";
				sql = "select categoryName from category where no = ?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, rs.getInt(4));
				rs2 = pstmt2.executeQuery();
				if (rs2.next())
					categoryName = rs2.getString(1);
				
				result.add(new BookVo(rs.getInt(1), 
						rs.getString(2), 
						rs.getInt(3), 
						categoryName));
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

			String sql = "delete from member where no=?";
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
