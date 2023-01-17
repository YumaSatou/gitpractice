<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除画面</title>
</head>
<body>
	<h2>削除するメールアドレスを入力してください。</h2>
	<form action="DeleteKadaiServlet" method="post">
	メールアドレス:<input type="text" name="mail"><br>
	<input type="submit" value="削除"><br>
	<a href = "./">戻る</a>
	</form>
</body>
</html>