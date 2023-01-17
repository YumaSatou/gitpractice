<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.KadaiDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧表示</title>
</head>
<body>
	<table border = "1">
	<tr>
	<th>名前</th>
	<th>年齢</th>
	<th>性別</th>
	<th>電話番号</th>
	<th>メールアドレス</th>
	</tr>
	
	<%
	List<KadaiDTO> list = (ArrayList<KadaiDTO>)request.getAttribute("list");
	for(KadaiDTO s : list) {
	%>
		<tr>
			<td><%=s.getName() %></td>
			<td><%=s.getAge() %></td>
			<td><%=s.getGender() %></td>
			<td><%=s.getNumber() %></td>
			<td><%=s.getMail() %></td>
	<%} %>
	</table>
	<a href="./">戻る</a>
</body>
</html>