<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="shoppingMall.*, java.util.*"%>
<%@ include file="../ad_top.jsp" %>

<table width="80%" border=0 class="outLine">
	<tr class="m3">
		<th>번호</th>
		<th>카테고리 코드</th>
		<th>상품명</th>
		<th>이미지</th>
		<th>가격</th>
		<th>제조사</th>
		<th>수량</th>
		<th>수정/삭제</th>
	</tr>
<%
	ProductDAO pdao = ProductDAO.getInstance();
	ArrayList<ProductDTO> list = pdao.productAll();
	if(list == null || list.size() == 0) {
		out.println("<tr><td colspan=8>상품 데이터가 없습니다.</td></tr>");
		return;
	}//if End
	
	for(ProductDTO pdto : list) {
%>
	<tr>
		<td><%=pdto.getPnum() %></td>
		<td><%=pdto.getPcategory_fk() %></td>
		<td><%=pdto.getPname() %></td>
		<td><%String imgPath = request.getContextPath() + "/uploadFile/" + pdto.getPimage();%>
			<img src="<%=imgPath %>" border=0 width=50 height=50/>
		<td><%=pdto.getPrice() %></td>
		<td><%=pdto.getPcompany() %></td>
		<td><%=pdto.getPqty() %></td>
		<td><a href="prodUpdate.jsp">수정 </a>| <a href="prodDelete.jsp">삭제</a></td>
	</tr>
<%
	}
%>
	
</table>
<%@ include file="../ad_bottom.jsp"%>