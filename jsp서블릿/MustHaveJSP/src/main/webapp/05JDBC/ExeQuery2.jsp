<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>Board 목록 조회 테스트(executeQuery() 사용)</h2>
	<table border="1">
        <tr>
            <th>num</th>
            <th>title</th>
            <th>content</th>
            <th>id</th>
            <th>postdate</th>
            <th>visitcount</th>
        </tr>
	<%
	String idParam = request.getParameter("id");
	
	//DB에 연결
	JDBConnect jdbc = new JDBConnect();
	
	//쿼리문 생성
	String sql = "select num, title, content, id, postdate, visitcount from board";
	if (idParam != null && !idParam.isEmpty()) {
		sql += " where id = '" + idParam + "'";
	}
	Statement stmt = jdbc.con.createStatement();
	
	//쿼리 수행
	ResultSet rs = stmt.executeQuery(sql);
	
	//결과 확인(웹 페이지에 출력)
	while(rs.next()) {
		String num = rs.getString(1);
		String title = rs.getString(2);
		String content = rs.getString("content");
		String id = rs.getString("id");
		java.sql.Date postdate = rs.getDate("postdate");
		String visitcount = rs.getString(6);
		
		//out.print(String.format("%s %s %s %s %s %s", num, title, content, id, postdate, visitcount) + "<br/>");
		%>
        <tr>
            <td><%= num %></td>
            <td><%= title %></td>
            <td><%= content %></td>
            <td><%= id %></td>
            <td><%= postdate %></td>
            <td><%= visitcount %></td>
        </tr>
        <%
	}
	
	//연결 닫기
	jdbc.close();
	
	%>
</body>
</html>