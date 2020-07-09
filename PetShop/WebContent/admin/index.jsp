<%@page import="com.pet.model.admin.Admin"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Admin admin = (Admin)session.getAttribute("admin");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.topnav {
  overflow: hidden;
  background-color: #333;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #4CAF50;
  color: white;
}
</style>
</head>
<body>

<div class="topnav">
  <a class="active" href="#home">Home</a>
  <a href="/admin/category/index.jsp">카테고리 관리</a>
  <a href="/admin/product/index.jsp">상품 관리</a>
  <a href="/admin/member/index.jsp">회원정보</a>
  <a href="/admin/order/index.jsp">주문정보</a>
  <a href="/admin/cs/index.jsp">고객센터</a>
  <a><%=admin.getName()%>님 로그인 중</a>
</div>

<div style="padding-left:16px">
  <h2>Top Navigation Example</h2>
  <p>Some content..</p>
</div>

</body>
</html>
