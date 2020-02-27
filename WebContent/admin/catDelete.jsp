<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="shoppingMall.*"%>
<%
	String cnum = request.getParameter("cnum");
	if(cnum == null || cnum.trim().equals("")) {
		response.sendRedirect("cat_list.jsp");
		return;
	}
	
	//categoryDAO의 비즈니스 로직을 수행
	CategoryDAO cdao = CategoryDAO.getInstance();
	int n = cdao.categortDelete(cnum);
	
	String msg = "", url ="cat_list.jsp";
	
	if(n>0) {
		msg="카테고리 삭제 완료";
	}else {
		msg="카테고리 삭제 실패";
	}
%>
<script>
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>
