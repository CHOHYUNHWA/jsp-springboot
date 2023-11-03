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
      <td>${title}</td>
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
      <td colspan="3">${body}</td>
    </tr>
  </tbody>
</table>

<br><br>

<div class="buttons">
  <c:if test="${checkWriter}">
    <a href="/postUpdate/${postId}" class="styled-button">수정</a>
    <form action="/postDelete/${postId}" method="post">
    <button class="styled-button" type="submit">삭제</button>
    </form>
    <a class="styled-button" href="/board">목록으로</a>
  </c:if>
</div>



</body>
</html>