<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>게시글 작성</h3>

<form method="post" action="/post">
<label id="title">제목</label>
<input type="text" name="title" placeholder="제목을 입력해 주세요">

<br><br>
<label id="body">내용</label>
<textarea name="body" placeholder="내용을 입력해 주세요"></textarea>

<br> 
<button type="submit">작성하기</button>

</form>

<form method="get" action="/board">
<button type="submit">취소</button>
</form>

</body>
</html>