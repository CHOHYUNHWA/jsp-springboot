<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<style>
    table {
        width: 70%; /* 표 너비를 70%로 조정 (원하는 값으로 변경 가능) */
        border: 1px solid #444444;
        border-collapse: collapse;
        margin: 20px; /* 표 주위 여백 조정 (원하는 값으로 변경 가능) */
    }
    th, td {
        border: 1px solid #444444;
        text-align: center;
        padding: 10px; /* 셀 내 여백 조정 (원하는 값으로 변경 가능) */
    }
    th {
        font-size: 16px; /* th(표 머리글) 글자 크기를 크게 조정 (원하는 크기로 변경 가능) */
    }
    td {
        font-size: 14px; /* td(셀 내용) 글자 크기를 크게 조정 (원하는 크기로 변경 가능) */
    }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시판 페이지</h2>
<h3>게시판 리스트</h3>
<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="post" items="${postList}">
			<tr>
				<td>${post.id}</td>
				<td><a href=/post/${post.id}>${post.title}</a></td>
				<td>${post.writer}</td>
				<td><c:out value="${fn:substring(post.createdAt, 0, 10)}" /></td>
			</tr>		
		</c:forEach>
	</tbody>
</table>

<div class="buttons">
<a href="/post">게시글 작성</a>
<a href="/">메인페이지로</a>
</div>
</body>
</html>