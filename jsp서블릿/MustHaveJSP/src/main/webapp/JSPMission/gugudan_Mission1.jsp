<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% String str = request.getParameter("dan");
    int dan = (str != null && !str.isEmpty()) ? Integer.parseInt(str) : 2; 
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mission 1</title>
</head>
<body>
<h2>구구단 출력 <%= dan %> 단</h2>
	<%
	for(int j = 1; j<10; j++) {
    		if(j <10) {
    			out.print(dan + " * "+j+" = "+(dan*j)+"<br/>");
    		out.println();
    	}
    }
	%>

	
</body>
</html>