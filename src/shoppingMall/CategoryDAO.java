package shoppingMall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CategoryDAO {
	private static CategoryDAO insCatDAO = new CategoryDAO();
	
	private CategoryDAO() {
		
	}
	
	public static CategoryDAO getInstance() {
		return insCatDAO;
	}
	
	
	public int insertCat(String code, String cname) {
		int n = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO category VALUES(category_seq.NEXTVAL, ?, ?)";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, cname);
			n = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(ps !=null) ps.close();
					if(conn != null) conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return n;
		}
	}
	
	//** 카테고리 목록 가져오기
	public ArrayList<CategoryDTO> categoryAll() throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CategoryDTO> list = null;
		try {
			conn = getConnection();
			String sql = "SELECT * FROM category ORDER BY catenum DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			list = new ArrayList<CategoryDTO>();
			while(rs.next()) {
				String cnum = rs.getString("catenum");
				String code = rs.getString("code");
				String cname = rs.getString("catename");
				CategoryDTO cdto = new CategoryDTO(cnum, code, cname);
				list.add(cdto);
			}//whileEnd
			
		}finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) ps.close();
		}
		return list;
	}
	
	//카테고리 삭제
	public int categortDelete(String cnum) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int n = 0;
		try {
			String sql = "DELETE FROM category WHERE catenum=?";
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cnum);
			n = ps.executeUpdate();
		}finally {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}
		return n;
	}
	
	
	private Connection getConnection() {
		Context ctx = null;
		DataSource dataSource = null;
		Connection conn = null;
		
		try {
			ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = dataSource.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
