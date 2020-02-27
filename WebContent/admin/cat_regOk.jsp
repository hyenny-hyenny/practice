<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shoppingMall.CategoryDAO" %>
<%
	//사용자로부터 입력한 값을 받아오기
	request.setCharacterEncoding("UTF-8");
	String code = request.getParameter("code");
	String cname = request.getParameter("cname");
	
	//유효성 검사
	if(code ==null && cname ==null || code.trim().equals("") || cname.trim().equals("")){
		response.sendRedirect("cat_input.jsp");
	}
	
	//비즈니스 로직(DAO) 수행
	CategoryDAO cdao = CategoryDAO.getInstance();
 	int n = cdao.insertCat(code.trim(), cname.trim());
 	String msg = "", url ="";
 	if(n>0){
 		msg = "카테고리 등록 완료";
 		url = "cat_list.jsp";
 	}else{
 		msg = "카테고리 등록 실패";
 		url = "cat_input.jsp";
 	}
%>
<script>
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>