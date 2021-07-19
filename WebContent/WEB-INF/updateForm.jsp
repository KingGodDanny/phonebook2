<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<%//@ page import="com.javaex.dao.PhoneDao" %>
<%//@ page import="com.javaex.vo.PersonVo" %>

<%

	//PersonVo personVo = (PersonVo)request.getAttribute("personList");

// 	이렇게 하는것은 모델1을 쓰는방법이다.
// 	PhoneDao phoneDao = new PhoneDao();
// 	//id추출
// 	int personId = //Integer.parseInt(request.getParameter("personId"));
// 	System.out.println(personId);
	
// 	//dao 에서 한사람(id)의 정보 가져오기
// 	PersonVo personVo = phoneDao.getPerson(personId);
// 	System.out.println(personVo.toString());
	
	
%>

    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 수정</h1>
	<p>수정화면 입니다. 아래 항목을 수정하고 "수정" 버튼을 클릭하세요</p>
	
	<form action="/phonebook2/pbc" method="get">
		<input type="hidden" name="action" value="update"> <br>
		이름: <input type="text" name="name" value=${personList.name }  > <br>
		핸드폰: <input type="text" name="hp" value=${personList.hp } > <br>
		회사: <input type="text" name="company" value=${personList.company } > <br>
		<input type="hidden" name="id" value=${personList.personId }  > <br>
		
		
		<button type="submit">수정</button>
	</form>
	
</body>
</html>