<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원가입 페이지 입니다.</h2>
<hr>
<form method="post" action="/join">
<div>
<label id = "username">
아이디
</label>
<br>
<input type="text" placeholder="아이디" name="username">
<br>
<label>
<br>
비밀번호
</label>
<br>
<input type="password" placeholder="비밀번호" name="password">
<br>
<br>
<label>
이름
</label><br>
<input type="text" placeholder="이름" name="name">

</div>
<button type="submit">회원가입</button>
</form> 
</body>
</html>