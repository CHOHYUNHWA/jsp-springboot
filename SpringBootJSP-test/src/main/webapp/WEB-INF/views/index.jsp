<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav>
		<div>SringBoot JSP 게시판 서비스</div>
	</nav>
<%
		// 쿠키 배열 가져오기
		Cookie[] cookies = request.getCookies();
		boolean authCookieExists = false;
		
		// 'auth' 쿠키가 존재하는지 확인
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("auth")) {
					authCookieExists = true;
					break;
				}
			}
		}
		
		// 'auth' 쿠키가 존재하면 "회원정보" 링크를 표시
		if (authCookieExists) {
	%>
		<a href="/board">게시판</a>
		
		<br><br> 
		<a href="/memberInfo">회원정보</a> &nbsp; &nbsp;
		<a href="/allMember">회원리스트</a>
	<%
		} else {
	%>
		<a href="/board">게시판</a>
		
		<br><br>
		<a href="/join">회원가입</a> &nbsp; &nbsp;
		<a href="/login">로그인</a>
	<%
		}
	%>
</body>
</html>