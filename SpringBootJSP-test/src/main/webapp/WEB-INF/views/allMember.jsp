<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
	table{
	width:50%;
	/* border: 1px solid #444444; */
	border-collapse: collapse;
	}
	th, td{
	border: 1px solid #444444;
	text-align: center;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원 목록</h2>
<hr>
<table>
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="member" items="${memberList}">
            <tr>
           		<td>${member.username}</td>
                <td>${member.name}</td>
            </tr>
        </c:forEach>
	</tbody>
</table>

</body>
</html>