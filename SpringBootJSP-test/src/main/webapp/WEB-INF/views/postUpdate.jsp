<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

.button-container {
  text-align: center;
	}

	.styled-button {
	    text-decoration: none;
	    background-color: #007BFF;
	    color: #fff;
	    padding: 10px 20px;
	    border-radius: 5px;
	    margin: 0 5px;
	    display: inline-block;
	    text-align: center;
	    border: 1px solid #007BFF; 
	    
	}

  body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
  }

  h2 {
    text-align: center;
    margin-top: 20px;
  }

  table {
    width: 80%;
    margin: 0 auto;
    border-collapse: collapse;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }

  th {
    background-color: #007BFF;
    color: #fff;
    text-align: left;
    padding: 10px;
    font-size: 18px;
  }

  th:first-child {
    width: 60%;
  }

  th:nth-child(2),
  th:nth-child(3) {
    width: 20%;
  }

  th, td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
  }

  tr:hover {
    background-color: #f5f5f5;
  }

  td {
    font-size: 20px;
    padding: 15px;
  }

  .buttons {
    text-align: center;
  }

  .buttons a {
    text-decoration: none;
    background-color: #007BFF;
    color: #fff;
    padding: 10px 20px;
    border-radius: 5px;
    margin: 0 5px;
  }
</style>
</head>
<body>
<h2>게시글 조회</h2>
<form method="post" action="/postUpdate/${postId}">
<table>
  <thead>
    <tr>
      <th>제목</th>
      <th>작성자</th>
      <th>작성일</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><input id="inputText" type="text" name="title" value="${title}" placeholder="제목을 입력해 주세요."></td>
      <td>${writer}</td>
      <td>${fn:substring(createdAt, 0, 10)}</td>
    </tr>
  </tbody>
  <thead>
    <tr>
      <th colspan="3">내용</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td id="bodyTextArea" colspan="3"><textarea name="body" placeholder="내용을 입력해 주세요.">${body}</textarea></td>
    </tr>
  </tbody>
</table>
<div class="button-container">
<button type="submit" class="styled-button">수정완료</button>
<a href="/board" class="styled-button">취소</a>
</div>

</form>





</body>
</html>