<%@page import="com.pet.domain.OrderDetail"%>
<%@page import="com.pet.domain.OrderSummary"%>
<%@page import="com.pet.controller.common.Pager"%>
<%@page import="com.pet.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<OrderDetail> detailList=(List)request.getAttribute("detailList");
	out.print(detailList.size());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin.css"/>
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
#btnArea{
	margin-top:20px;
	text-align:center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function getDetail(order_summary_id){
	//상세보기 요청 
	location.href="/admin/order/detail?order_summary_id="+order_summary_id;
}
</script>
</head>
<body>
<%@include file="/admin/inc/main_navi.jsp"%>
<div id="productList">
	<h3>구매 상품 정보</h3>
	<table>
	  <tr>
	    <th>No</th>
	    <th>주문자</th>
	    <th>주문금액</th>
	    <th>주문일시</th>
	    <th>결제방법</th>
	    <th>받는 사람</th>
	  </tr>	
	    <td><%%></td>
		<td><%%></td>
		<td><%%></td>
		<td><%%></td>
		<td><%%></td>
		<td><%%></td>
	  </tr>	
	  <tr>
	  	<td colspan="7" align="center">
	  		<button onClick="location.href='/admin/product/registForm.jsp';">상품등록</button>
	  	</td>
	  </tr>
	  <tr>
	  	<td colspan="7" style="text-align:center">
	  	</td>
	  </tr>	
	</table>
</div>

<div id="payInfo">
	<h3>결제 내역</h3>
	<table>
	  <tr>
	    <th>No</th>
	    <th>주문자</th>
	    <th>주문금액</th>
	    <th>주문일시</th>
	    <th>결제방법</th>
	    <th>받는 사람</th>
	  </tr>	
	    <td><%%></td>
		<td><%%></td>
		<td><%%></td>
		<td><%%></td>
		<td><%%></td>
		<td><%%></td>
	  </tr>	
	  <tr>
	  	<td colspan="7" align="center">
	  		<button onClick="location.href='/admin/product/registForm.jsp';">상품등록</button>
	  	</td>
	  </tr>
	  <tr>
	  	<td colspan="7" style="text-align:center">
	  	</td>
	  </tr>	
	</table>
</div>

<div id="receiveInfo">
	<h3>배송자 정보</h3>
	<table>
	  <tr>
	    <th>No</th>
	    <th>주문자</th>
	    <th>주문금액</th>
	    <th>주문일시</th>
	    <th>결제방법</th>
	    <th>받는 사람</th>
	  </tr>	
	    <td><%%></td>
		<td><%%></td>
		<td><%%></td>
		<td><%%></td>
		<td><%%></td>
		<td><%%></td>
	  </tr>	
	  <tr>
	  	<td colspan="7" align="center">
	  		<button onClick="location.href='/admin/product/registForm.jsp';">상품등록</button>
	  	</td>
	  </tr>
	  <tr>
	  	<td colspan="7" style="text-align:center">
	  	</td>
	  </tr>	
	</table>
</div>
<div id="btnArea">
	<button>주문목록</button>
	<button>주문수정</button>
	<button>주문삭제</button>
</div>

</body>
</html>











