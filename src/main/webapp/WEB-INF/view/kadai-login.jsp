<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("error") != null){	
	%>
		<p style="color:red">ログイン失敗</p>
	<form action="KadaiLoginServlet" method="post">
		<h2>【メールアドレスとPWを入力してください。】</h2><br>
		メールアドレス：<input type="text" name="mail" value="<%=request.getParameter("mail") %>"><br>
		PW：<input type="password" name="pw"><br>
		<input type="submit" value="ログイン"><br>
		<a href="./">戻る</a>
	</form>
	<%
		} else {
	%>
	<form action="KadaiLoginServlet" method="post">
		<h2>【メールアドレスとPWを入力してください。】</h2><br>
		メールアドレス：<input type="text" name="mail"><br>
		PW：<input type="password" name="pw"><br>
		<input type="submit" value="ログイン"><br>
		<a href="./">戻る</a>
	</form>
	<%
		}
	%>
</body>
</html>