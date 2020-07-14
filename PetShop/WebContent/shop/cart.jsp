<%@page import="com.pet.model.product.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.pet.model.product.Product"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<Cart> cartList = (List) session.getAttribute("cartList");
%>
<!DOCTYPE html>
<html>
<head>
<title>Pet Shop</title>
<meta charset="utf-8">
<link href="/css/style.css" rel="stylesheet" type="text/css">
<style>
.pic {
	width: 50%;
	height: 250px;
	background: yellow;
	float: left;
	margin-top: 50px;
}

.spec {
	width: 50%;
	height: 250px;
	background: green;
	float: left;
	margin-top: 50px;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
	margin-top: 50px;
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
	$(function() {
		
	});
		
		function del(product_id){
			if(confirm("상품코드 "+product_id+"를 삭제하시겠어요?")){
				location.href="/shop/cart/del?product_id="+product_id;
			}
		}
		
		function removeAll(){
			if(confirm("장바구니를 비우시겠어요?")){
				location.href="/shop/cart/remove";
			}
		}
		
</script>
</head>
<body>
	<div id="header">
		<%@ include file="/include/main_navi.jsp"%>
	</div>
	<div id="body">
		<div id="content">
			<!-- 장바구니 표 -->
			<h2>장바구니</h2>
			<table width="100%">
				<tr>
					<th><input type="checkbox" /></th>
					<th>이미지</th>
					<th>상세정보</th>
					<th>수량</th>
					<th>변경</th>
				</tr>
				<%
					if (cartList != null) {
				%>
				<%
					for (int i = 0; i < cartList.size(); i++) {
				%>
				<%
					Cart cart = cartList.get(i);
				%>
				<tr>
					<td><input type="checkbox" /></td>
					<td><img src="/data/<%=cart.getFilename()%>" width="45px" /></td>
					<td>
						<ul>
							<li>카테고리명:<%=cart.getCategory().getCategory_name()%></li>
							<li>상품명:<%=cart.getProduct_name()%></li>
							<li>브랜드:<%=cart.getBrand()%></li>
							<li>가격:<%=cart.getPrice()%></li>
						</ul>
					</td>
					<td><input type="text" value="<%=cart.getEa()%>" /></td>
					<td>
						<button>수정</button>
						<button onclick="del(<%=cart.getProduct_id()%>)">삭제</button>
					</td>
				</tr>
				<%
					}
				%>
				<%
					} else {
				%>
				<td style="text-align: center" colspan="5">장바구니가 비어있습니다.</td>
				<%
					}
				%>
				<tr>
					<td colspan="4"  style="text-align:right">총 4,652원</td>
					<td colspan="1" style="">
						<button onclick="removeAll()">장바구니 비우기</button>
						<button>구매하기</button>
					</td>
				</tr>
				
			</table>

		</div>
		<div class="featured">
			<ul>
				<li><a href="#"><img
						src="/images/organic-and-chemical-free.jpg" width="300"
						height="90" alt=""></a></li>
				<li><a href="#"><img src="/images/good-food.jpg"
						width="300" height="90" alt=""></a></li>
				<li class="last"><a href="#"><img
						src="/images/pet-grooming.jpg" width="300" height="90" alt=""></a></li>
			</ul>
		</div>
	</div>
	<div id="footer">
		<%@ include file="/include/footer.jsp"%>
	</div>
</body>
</html>