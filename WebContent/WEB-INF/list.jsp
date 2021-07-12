<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="com.javaex.vo.PersonVo" %>
<%@page import="java.util.List" %> 
    
<%
	//Request 안에 데이터를 사용하는데 List형을 사용해야해서 강제형변환해줌
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("pList"); 

	System.out.println("==========================JSP================");
	System.out.println(personList);
	
	/*
	int age = (int)request.getAttribute("age");   				//int형이기때문에 int형으로 강제형변환
	String name = (String)request.getAttribute("name");			//String형이기때문에 String형으로 강제형변환
	
	System.out.println(age + ", " + name);
	*/
	
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 리스트</h1>
	<p>입력한 정보 내역입니다.</p>
	
	
	<%for(int i=0; i<personList.size(); i++) {%>
	
	<table border="1">
		<tr>
			<td>이름</td>
			<td><%=personList.get(i).getName() %></td>
		</tr>
		<tr>
			<td>핸드폰</td>
			<td><%=personList.get(i).getHp() %></td>
		</tr>
		<tr>
			<td>회사</td>
			<td><%=personList.get(i).getCompany() %></td>
		</tr>
		<tr>
			<td><a href="/phonebook2/pbc?action=uForm&personId=<%=personList.get(i).getPersonId() %>">수정</a></td>
			<td><a href="/phonebook2/pbc?action=delete&personId=<%=personList.get(i).getPersonId() %>">삭제</a></td>
		</tr>
	</table>
	<br>
	<% } %>
	
	<a href="http://localhost:8088/phonebook2/pbc?action=wForm">등록하러 가기</a>

</body>
</html>