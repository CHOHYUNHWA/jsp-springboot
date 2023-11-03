<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>회원 수정</h3>

<form method="post" action="/memberUpdate" >

아이디 : ${username}<br>

이름 : ${name}<br>

<label id="password">비밀번호</label>
<input type="password" name="password"> 

<label id="newPassword">새 비밀번호</label>
<input type="password" name="newPassword"> 

<br><br>

<button type="submit">수정</button> 
</form>

<br>

<form method="post" action="/memberDelete">
<button type="submit">회원탈퇴</button>
</form>

<br>

<form method="get" action="/memberInfo">
<button type="submit">취소</button>
</form>


</body>
</html>