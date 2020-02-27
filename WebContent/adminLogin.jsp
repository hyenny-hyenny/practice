<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("valid") != null) {
%>
	<jsp:forward page = "ad_main.jsp"></jsp:forward>
<%
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<hr width="500" color="green"/>
<h2>관리자 로그인</h2>
<hr width="500" color="green"/>
	<form action="loginOk.jsp" method="post">
		아이디 : <input type="text" name="id" value="<%if(session.getAttribute("id") != null) out.println(session.getAttribute("id"));%>"/><br/>
		비밀번호 : <input type="password" name="pw"/><br/>
		<input type="submit" value="로그인"/><br/>
	</form>
</center>
</body>
</html>