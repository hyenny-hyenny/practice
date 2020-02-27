package shoppingMall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminDAO {
	public static final int ADMIN_NONEXISTENT = 0;
	public static final int ADMIN_EXIST = 1;
	public static final int ADMIN_LOGIN_PW_FAIL = 0;
	public static final int ADMIN_LOGIN_SUCESS = 1;
	public static final int ADMIN_LOGIN_NOT = -1;
	
	private static AdminDAO insAdmin = new AdminDAO();
	
	private AdminDAO() {}
	
	public static AdminDAO getInstance() {
		return insAdmin;
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
	
	

	public int adminCheck(String id, String pw) {
		int n = 0;
		String dbPw;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT password FROM admin WHERE id = ?";
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dbPw = rs.getString("password");
				if(dbPw.equals(pw)) {
					n = ADMIN_LOGIN_SUCESS;		//로그인 성공 1
				}else {
					n = ADMIN_LOGIN_PW_FAIL;	//비밀번호 불일치 0
				}
			}else {
				n = ADMIN_LOGIN_NOT;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return n;
	}

	
	
	public AdminDTO getAdmin(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM admin WHERE id = ?";
		AdminDTO adto = null;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				adto = new AdminDTO();
				adto.setId(rs.getString("id"));
				adto.setPassword(rs.getString("password"));
				adto.setName(rs.getString("name"));
				adto.setEmail(rs.getString("email"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return adto;
	}
	
	
	
	
}
