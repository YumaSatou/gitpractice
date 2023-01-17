<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="dto.KadaiDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>確認画面</title>
</head>
<body>
	<p>下記の内容で登録します。よろしいですか？</p>
	<%
		KadaiDTO account = (KadaiDTO)session.getAttribute("input_data");
	%>
	名前：<%=account.getName() %><br>
	年齢：<%=account.getAge() %><br>
	性別：<%=account.getGender() %><br>
	電話番号：<%=account.getNumber() %><br>
	メール：<%=account.getMail() %><br>
	パスワード：********<br>
	<a href="KadaiExecuteServlet">OK</a><br>
	<a href="KadaiFormServlet">戻る</a>
</body>
</html>