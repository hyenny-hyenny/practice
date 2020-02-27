<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shoppingMall.*, java.util.*" %>

<%@ include file="../ad_top.jsp" %>
<h3>카테고리 리스트</h3>
<table width="600" class="outLine">
	<tr class="m1">
		<th>번호</th>
		<th>코드</th>
		<th>카테고리명</th>
		<th>삭제</th>
	</tr>
<%
	CategoryDAO cdao = CategoryDAO.getInstance();
	ArrayList<CategoryDTO> list = cdao.categoryAll();
	if(list == null || list.size() == 0) {
		out.println("<tr><td clospan=4>등록된 카테고리가 없습니다.</td></tr></table>");
	}
	for(int i = 0; i <list.size(); i++) {
		CategoryDTO cdto = list.get(i);
%>
	<!-- 데이터베이스로부터 가져온 리스트 내용 -->
	<tr class="m2">
		<td><%=cdto.getCnum()%></td>
		<td><%=cdto.getCode() %></td>		
		<td><%=cdto.getCname() %></td>
		<td><a href="catDelete.jsp?cnum=<%=cdto.getCnum()%>">삭제</a></td>		
	</tr>
<%
	}
%>
</table>

<%@ include file="../ad_bottom.jsp" %>