<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
로그인 페이지 입니다.
<hr>
<form method="post" action="/login">

<label>아이디</label>
<br>
<Input type="text" name = "username">

<br><br>
<label>비밀번호</label>
<br>
<input type="password" name = "password">

<button type="submit">로그인</button> <a href="/">메인으로</a>
</form>
</body>
</html>