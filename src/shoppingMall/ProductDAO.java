package shoppingMall;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	
	//상품 리스트 조회
	public ArrayList<ProductDTO> productAll() throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM product ORDER BY pnum DESC";
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ArrayList<ProductDTO> list = makeArrayList(rs);
			return list;
		}finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}
	}
	
	
	public ArrayList<ProductDTO> makeArrayList(ResultSet rs) throws SQLException{
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		while(rs.next()) {
			String pnum = rs.getString(1);
			String pname = rs.getString(2);
			String pcategory_fk = rs.getString(3);
			String pcompany = rs.getString(4);
			String pimage = rs.getString(5);
			int pqty = rs.getInt(6);
			int price = rs.getInt(7);
			String pspec = rs.getString(8);
			String pcontents = rs.getString(9);
			int point = rs.getInt(10);
			Date d = rs.getDate(11);
			String pinputDate = d.toString();
			ProductDTO pdto = new ProductDTO(pnum, pname, pcategory_fk, pcompany, pimage, pqty, 
					price, pspec, pcontents, point, pinputDate, 0, 0, 0);
			list.add(pdto);
		}
		return list;
	}//makeArrayList()
	
	
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
