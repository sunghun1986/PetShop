<%@page import="com.pet.domain.OrderSummary"%>
<%@page import="com.pet.controller.common.Pager"%>
<%@page import="com.pet.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<OrderSummary> orderList=(List)request.getAttribute("orderList");
	Pager pager = (Pager)request.getAttribute("pager");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin.css" />
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	
	function getDetail(product_id){
		//상세보기 요청
		location.href="/admin/product/detail?product_id="+product_id;
	}
</script>
</head>
<body>
	<%@include file="/admin/inc/main_navi.jsp"%>

	<table>
		<tr>
			<th>No</th>
			<th>주문자 명</th>
			<th>주문 금액</th>
			<th>주문 일자</th>
			<th>결제 방법</th>
			<th>주문/배송 동일여부</th>
			<th>받는 사람</th>
		</tr>
		
		<%int curPos=pager.getCurPos();%>
		<%int num=pager.getNum();%>
		<%for(int i = 0; i < pager.getPageSize(); i++){%>		
		<%OrderSummary orderSummary = orderList.get(curPos++);%>
		<tr<%=%>>
			<td><%=%></td>
			<td><%=%></td>
			<td><%=%></td>
			<td><%=%></td>
			<td><%=%></td>
			<td><%=%></td>
			<td><%=%></td>
		</tr>
		<%}%>
		<tr>
			<td colspan="6" align="center">
				<button onclick="location.href='/admin/product/registForm.jsp';">상품등록</button>
			</td>
		</tr>
		<tr>
			<td colspan="6" style="text-align:center">
				<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){%>
				<%if(i>pager.getTotalPage())break;%>
				<a href ="/admin/product/list?currentPage=<%=i%>">[<%=i%>]</a>
				<%}%>
			</td>
		</tr>
		
	</table>

</body>
</html>









