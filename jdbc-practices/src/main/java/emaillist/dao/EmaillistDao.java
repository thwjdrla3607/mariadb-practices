package emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import emaillist.vo.EmaillistVo;

public class EmaillistDao {

   public List<EmaillistVo> findAll() {
      List<EmaillistVo> result = new ArrayList<>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         // 1. JDBC Driver Class 로딩
         Class.forName("org.mariadb.jdbc.Driver");
         
         // 2. 연결하기
         String url = "jdbc:mariadb://192.168.0.185:3307/webdb?charset=utf8";
         conn = DriverManager.getConnection(url, "webdb", "webdb");
         
         // 3. Statement 객체 생성
         String sql = "select no, first_name, last_name, email from emaillist order by no desc";
         pstmt = conn.prepareStatement(sql);
         
         // 4. binding
         
         // 5. SQL 실행
         rs = pstmt.executeQuery();
         
         // 6. 결과 처리
         while(rs.next()) {
            Long no = rs.getLong(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String email = rs.getString(4);
            
            EmaillistVo vo = new EmaillistVo();
            vo.setNo(no);
            vo.setFirstName(firstName);
            vo.setLastName(lastName);
            vo.setEmail(email);
            
            result.add(vo);
         }
         
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버 로딩 실패:" + e);
      } catch (SQLException e) {
         System.out.println("error:" + e);
      } finally {
         try {
            // 7. 자원정리
            if(rs != null) {
               rs.close();
            }
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
      return result;
   }

   public void insert(EmaillistVo vo) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         // 1. JDBC Driver Class 로딩
         Class.forName("org.mariadb.jdbc.Driver");
         
         // 2. 연결하기
         String url = "jdbc:mariadb://192.168.0.185:3307/webdb?charset=utf8";
         conn = DriverManager.getConnection(url, "webdb", "webdb");
         
         // 3. Statement 객체 생성
         String sql = "insert into emaillist values(null, ?, ?, ?)";
         pstmt = conn.prepareStatement(sql);
         
         // 4. binding
         pstmt.setString(1, vo.getFirstName());
         pstmt.setString(2, vo.getLastName());
         pstmt.setString(3, vo.getEmail());
         
         // 5. SQL 실행
         pstmt.executeQuery();
         
         // 6. 결과 처리
   
         
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버 로딩 실패:" + e);
      } catch (SQLException e) {
         System.out.println("error:" + e);
      } finally {
         try {
            // 7. 자원정리
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
   
}