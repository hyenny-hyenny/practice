<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="shoppingMall.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 
	1. 파일 업로드 라이브러리 다운로드
		http://www.servlets.com
		com.oreilly.servlet 메뉴 선택
		cos-26Dec2008.zip 파일 다운
	2. 위치 지정
		압축 풀고난 후 cos.jar 파일
		WebContent/WEB-INF/lib/에 복사
	3. 업로드 파일을 저장하기 위한 폴더 생성
		WebContent 폴더 내에 생성
 -->
 <%
 	request.setCharacterEncoding("utf-8");
 	String msg = "", url ="";
 	
 	String uploadPath = request.getRealPath("uploadFile");
	int maxSize = 1024 * 1024 * 10;
	String filename = "";
	String originFile = "";
	
	try{
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		ProductDAO pdao = ProductDAO.getInstance();
		int n = pdao.registerProduct(multi);
		
			if(n>0) {
				msg = "상품 등록 완료";
				url = "prodList.jsp";
			}else {
				msg = "상품 등록 실패";
				url = "prod_input.jsp";
			}
	}catch(Exception e) {
		msg = "이미지 파일 업로드 실패";
		url = "prod_input.jsp";
		e.printStackTrace();
	}
 %>
 <script>
 	alert("<%=msg%>");
 	location.href="<%=url%>";
 </script>