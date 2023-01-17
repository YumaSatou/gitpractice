<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除成功</title>
</head>
<body>
	<h1 style="color:red">下記のデータを削除しました。</h1>
	<%
	request.setCharacterEncoding("UTF-8");
	String mail = request.getParameter("mail");
	%>
	メールアドレス:<%=mail %><br>
	<a href="./">戻る</a>
</body>
</html>