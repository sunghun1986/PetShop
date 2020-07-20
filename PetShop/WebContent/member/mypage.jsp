<%@page import="com.pet.domain.OrderSummary"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	Member obj = (Member)request.getAttribute("member");
	List<OrderSummary> orderList = (List)request.getAttribute("orderList");
%>
<!DOCTYPE html>
<html>
<head>
<title>Pet Shop | Contact</title>
<meta charset="utf-8">
<link href="/css/style.css" rel="stylesheet" type="text/css">
<!--[if IE 6]><link href="/css/ie6.css" rel="stylesheet" type="text/css"><![endif]-->
<!--[if IE 7]><link href="/css/ie7.css" rel="stylesheet" type="text/css"><![endif]-->
</head>
<body>
<div id="header">
	<%@ include file="/include/main_navi.jsp"%>
</div>
<div id="body">
  <div id="content">
    <div class="content">
      <h2>[MyPage]</h2>
      
      <ul class="connect">
        <li>
          <h2>[나의 정보]</h2>
          <p> 
          <span>아이디:<%=obj.getId()%></span> 
          <span>비밀번호:<input type="password" value="<%=obj.getPassword()%>"</span>
          <span>연락처<%=obj.getPhone()%></span>
          <span>Email:<%=obj.getEmail()%></span>
          <span>주소:<%=obj.getAddr()%></span>          
           </p>          
        </li>
        <li>
          <h2>[결제 내역]</h2>
          <p> <span>monday to Friday 9:00 am - 7:00 pm EST (GMT -05000)</span> <span>Closed Saturday and Sunday</span> </p>
        </li>
        <li>
          <h2>[상담 내역]</h2>
          <p> <span>Petshop</span> <span>250 Lorem Ipsum Street</span> <span>4th Floor</span> <span>jaonfanr, Caknan 109935</span> <span>Kiangab</span> </p>
        </li>
      </ul>
    </div>
    <div id="sidebar">
      <div class="connect">
        <h2>Follow Us</h2>
        <ul>
          <li><a class="facebook" href="#">Facebook</a></li>
          <li><a class="subscribe" href="#">Subscribe</a></li>
          <li><a class="twitter" href="#">Twitter</a></li>
          <li><a class="flicker" href="#">Flicker</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div class="featured">
    <ul>
      <li><a href="#"><img src="/images/organic-and-chemical-free.jpg" width="300" height="90" alt=""></a></li>
      <li><a href="#"><img src="/images/good-food.jpg" width="300" height="90" alt=""></a></li>
      <li class="last"><a href="#"><img src="/images/pet-grooming.jpg" width="300" height="90" alt=""></a></li>
    </ul>
  </div>
</div>
<%@ include file="/include/footer.jsp"%>
</body>
</html>
