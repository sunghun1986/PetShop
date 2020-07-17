<%@page import="com.pet.domain.Event"%>
<%@page import="com.pet.controller.common.Pager"%>
<%@page import="com.pet.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<Product> productList=(List)request.getAttribute("productList");
	List<Event> eventList=(List)request.getAttribute("eventList");
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
	
	function sendToEvent(){
		//체크박스에 체크한게 있는지 유효성 검사부터 하자!!
		//체크박스에 체크한 경우 카운트를 1씩 증가시켜서, 만일 증가된게 없다면
		//체크를 하지 않은것이다!!!!!!!!!!!!!!!!!!!!!!!
		var ch = document.getElementsByName("ch");
		alert("체크박스의 총 수는 : "+ ch.length);
		var count=0;
		for(var i = 0; i<ch.length; i++ ){
			if(ch[i].checked){
				count++;
			}
		}
		if(document.querySelector("select").value=="0"){
			alert("등록하실 이벤트의 종류를 선택하세요");
			return;
		}else if(count<1){
			alert("선택된 상품이 없습니다");
			return;	
		}
		if(confirm("선택한 상품을 이벤트 상품으로 등록하시겠습니까?")){
			//이벤트의 종류는 select 박스값이 결정하고 event_id
			//등록될 상품은 checkbox가 결정한다!
			var form = document.querySelector("form");
			form.action="/admin/eventproduct/regist";
			form.method="post";
			form.submit();
		}
	}
</script>
</head>
<body>
	<%@include file="/admin/inc/main_navi.jsp"%>
	<form>
	<table>
	<tr>
		<td colspan="7">
			<select name="event_id">
				<option value="0">이벤트 상품 선택</option>
				<%for(int i = 0; i < eventList.size(); i++){%>
				<%Event event = eventList.get(i);%>
				<option value="<%=event.getEvent_id()%>"><%=event.getTitle()%></option>
				<%}%>
			</select>
		</td>
	</tr>
		<tr>
			<th><input type="checkbox"/></th>
			<th>No</th>
			<th>이미지</th>
			<th>카테고리</th>
			<th>상품명</th>
			<th>가격</th>
			<th>브랜드</th>
		</tr>
		
		<%int curPos=pager.getCurPos();%>
		<%int num=pager.getNum();%>
		<%for(int i = 0; i < pager.getPageSize(); i++){%>
		<%if(num<1)break;%>
		<%Product product = productList.get(curPos++);%>
		<tr>
			<td><input type="checkbox" name="ch" value="<%=product.getProduct_id()%>"/></td>
			<td><%=num--%></td>
			<td><img src="/data/<%=product.getFilename()%>" width="35px"/></td>
			<td><%=product.getCategory().getCategory_name()%></td>
			<td onclick="getDetail(<%=product.getProduct_id()%>)"><%=product.getProduct_name()%></td>
			<td><%=product.getPrice()%></td>
			<td><%=product.getBrand()%></td>
		</tr>
		<%}%>
		<tr>
			<td colspan="7" align="center">
				<button onclick="location.href='/admin/product/registForm.jsp';">상품등록</button>
				<button onclick="sendToEvent()">이벤트 상품으로 등록</button>
			</td>
		</tr>
		<tr>
			<td colspan="7" style="text-align:center">
				<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){%>
				<%if(i>pager.getTotalPage())break;%>
				<a href ="/admin/product/list?currentPage=<%=i%>">[<%=i%>]</a>
				<%}%>
			</td>
		</tr>		
	</table>
	</form>

</body>
</html>









