<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    
    public int add(int num1, int num2) {
    	return num1 + num2;
    }
    %>

<% 
String str1 = request.getParameter("num1"); 
String str2 = request.getParameter("num2");
int int_num1 = Integer.parseInt(str1);
int int_num2 = Integer.parseInt(str2);
try{
	Integer.parseInt(null);
}
catch (Exception e){ 
	out.println("num1 num2 값 있어야됨");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
    int result = add(10, 20);
    %>
    <br />
    덧셈 결과 1 : <%= result %> <br />
    덧셈 결과 2 : <%= add(30, 40) %> <br />
    덧셈 결과 3 : <%= result %> <br />
    덧셈 결과 4 : <%= add(int_num1, int_num2) %>
</body>
</html>