<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style.css">
</head>
<body>
<center>
		<hr/>
	<h2>관리자 페이지</h2>
	<hr/>
	
	<table width="800">
		<tr>
			<td><a href="<%=request.getContextPath()%>/ad_main.jsp">관리자 홈</a></td>
			<td><a href="#">쇼핑몰 홈</a></td>
			<td><a href="<%=request.getContextPath()%>/admin/cat_input.jsp">카테고리 등록</a></td>			
			<td><a href="<%=request.getContextPath()%>/admin/cat_list.jsp">카테고리 리스트</a></td>
			<td><a href="<%=request.getContextPath()%>/admin/prod_input.jsp">상품 등록</a></td>
			<td><a href="<%=request.getContextPath()%>/admin/prodList.jsp">상품 리스트</a></td>					
		</tr>
	</table>
