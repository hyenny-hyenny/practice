package shoppingMall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;

public class ProductDAO {

	private static ProductDAO insPDAO = new ProductDAO();
	
	private ProductDAO() {}//기본생성자
	
	public static ProductDAO getInstance() {
		return insPDAO;
	}
	
	//상품 등록
	public int registerProduct(MultipartRequest multi) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		int n = 0;
		String sql = "INSERT INTO product VALUES(product_seq.NEXTVAL, ?,?,?,?,?,?,?,?,?,sysdate)";
	
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			String pname = multi.getParameter("pname");
			String pcategory_fk = multi.getParameter("pcategory_fk");
			String pcompany = multi.getParameter("pcompany");
			String pimage = multi.getFilesystemName("pimage");
			String pqty = multi.getParameter("pqty");
			String price = multi.getParameter("price");
			String pspec = multi.getParameter("pspec");
			String pcontents = multi.getParameter("pcontents");
			String point = multi.getParameter("point");
			
			ps.setString(1, pname);
			ps.setString(2, pcategory_fk);
			ps.setString(3, pcompany);
			ps.setString(4, pimage);
			ps.setString(5, pqty);
			ps.setString(6, price);
			ps.setString(7, pspec);
			ps.setString(8,  pcontents);
			ps.setString(9, point);
			
			n = ps.executeUpdate();
			
		}finally {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}
		return n;
	}
	
	//ConnectionPool에서 connection 확보
	private Connection getConnection() {
		Context ctx = null;
		DataSource dataSource = null;
		Connection conn = null;
		
		try {
			ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
