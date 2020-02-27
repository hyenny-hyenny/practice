<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="shoppingMall.*, java.util.*"%>
<%@ include file="../ad_top.jsp" %>
<%
	CategoryDAO cdao = CategoryDAO.getInstance();
%>
<h3>상품 등록페이지</h3>
<form name="prodFrm" action="prod_regOk.jsp" method="Post">
	<table border=0 class="outLine" width=600>
		<tr>
			<th class="m3">카테고리</th>
			<td><select name="pcategory_fk">
			<%
				ArrayList<CategoryDTO> list = cdao.categoryAll();
				if(list == null || list.size()==0) {
			%>
			<option value="">카테고리 없음</option>
			<%
				}else {
					for(CategoryDTO cdto : list) {
						String cname = cdto.getCname();
						String code = cdto.getCode();
			%>
				<option value="<%=code%>"><%=cname%>[<%=code %>]</option>
			<%
					}
				}
			%>
			</select></td>
		</tr>
		<tr>
			<th class="m3">상품명</th>
			<td align="left"><input type="text" name="pname"/></td>
		</tr>
		<tr>
			<th class="m3">제조사</th>
			<td><input type="text" name="pcompany"/></td>
		</tr>
		<tr>
			<th class="m3">상품이미지</th>
			<td><input type="file" name="pimage"/></td>
		</tr>
		<tr>
			<th class="m3">상품수량</th>
			<td><input type="text" name="pqty" maxlength="8"/></td>
		</tr>
		<tr>
			<th class="m3">상품가격</th>
			<td><input type="text" name="price" maxlength="8"></td>
		</tr>
		<tr>
			<th class="m3">상품사양</th>
			<td><select name="pspec">
				<option value="none" selected>일반</option>
				<option value="hit">인기</option>
				<option value="new">최신</option>
				<option value="recommand">추천</option>
			</select></td>
		</tr>
		<tr>
			<th class="m3">상품소개</th>
			<td><textarea name ="pcontents" rows="5" cols="50"></textarea></td>
		</tr>
		<tr>
			<th class="m3">상품 포인트</th>
			<td><input type="text" name="point"/></td>
		</tr>
		<tr>
			<td colspan="2" class="m1" align="center"><input type="submit" value="상품등록"/>
			<input type="reset" value="취 소"/></td>
		</tr>
	</table>
</form>

<%@ include file="../ad_bottom.jsp"%>