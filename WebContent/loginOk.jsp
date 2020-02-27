<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shoppingMall.AdminDAO, shoppingMall.AdminDTO" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	AdminDAO adao = AdminDAO.getInstance();
	int chkNum = adao.adminCheck(id, pw);
	
	if(chkNum == -1 ){
%>
	<script>
		alert("관리자만 접속이 가능합니다.");
		history.back();
	</script>
<%
	}else if(chkNum == 0) {
%>
	<script>
		alert("비밀번호가 일치하지 않습니다.");
		history.back();
	</script>
<%		
	}else if(chkNum == 1) {
		AdminDTO adto = adao.getAdmin(id);	
		
		String name = adto.getName();
		session.setAttribute("id", id);
		session.setAttribute("name", "name");
		session.setAttribute("valid", "yes");
		response.sendRedirect("ad_main.jsp");
	}
%>