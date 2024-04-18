<%@page import="jakarta.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%! String str = "dan";
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>구구단 출력 <%= str %>> 단</h2>
	<table style="border: 1px solid black; border-collapse: collapse" > <% 
    
	
	for(int j = 1; j<10; j++) {%>
    <tr style="border: 1px solid gray;">
	<% 	for(int i = 2; i<10; i++) {
    			if(j <10) {
    				out.print(" <td> "+ i + " * "+j+" = "+(i*j)+" | "+"</td>");
    			}
    		out.println();
    	}%>
	</tr><%
    }
    %>
    </table>
	
</body>
</html>