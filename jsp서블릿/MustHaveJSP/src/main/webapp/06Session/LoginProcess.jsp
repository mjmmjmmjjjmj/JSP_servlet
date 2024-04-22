<%@ page import="membership.MemberDTO" %>
<%@ page import="membership.MemberDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% 
// 로그인 폼에서 받은 id & pwd
String userId = request.getParameter("user_id");
String userPwd = request.getParameter("user_pw");

// web.xml 에서 가져온 datbase 연결 정보
String mysqlDriver = application.getInitParameter("MySQLDriver");
String mysqlURL = application.getInitParameter("MySQLURL");
String mysqlId = application.getInitParameter("MySQLId");
String mysqlPwd = application.getInitParameter("MySQLPwd");

//회원 테이블 DAO를 통해 회원 정보 DTO 획득
MemberDAO dao = new MemberDAO(mysqlDriver, mysqlURL, mysqlId, mysqlPwd);
MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd);
dao.close();

//로그인 성고 ㅇ여부에 따른 처리
if (memberDTO.getId() != null) {
	//로그인 성공
	session.setAttribute("UserId", memberDTO.getId());
	session.setAttribute("UserName", memberDTO.getName());
	response.sendRedirect("LoginForm.jsp");
}
else {
	//로그인 실패
	request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
	request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>