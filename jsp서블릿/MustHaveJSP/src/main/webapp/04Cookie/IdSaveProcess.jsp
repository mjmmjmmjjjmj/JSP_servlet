<%@ page import="utils.CookieManager" %>
<%@ page import="utils.JSFunction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
//폼값 읽기
String user_id = request.getParameter("user_id");
String user_pw = request.getParameter("user_pw");
String save_check = request.getParameter("save_check") ;

//사용자 인증

//login success
if ("must".equals(user_id) && "1234".equals(user_pw)) {	
	//아이디 저장하기 체크 확인
	if(save_check != null && save_check.equals("Y")) {
		//쿠키 생성
		CookieManager.makeCookie(response, "loginId", user_id, 86400); 
	}
	else {//쿠키 삭제
		CookieManager.deleteCookie(response, "loginId");
	}
	//로그인 성공 알림창 띄우고 메인페이지 (IdSaveMain.jsp) 이동 
	JSFunction.alertLocation("로그인에 성공했습니다.", "IdSaveMain.jsp", out);	
}
else {//로그인 실패
	JSFunction.alertBack("로그인에 실패했습니다.--", out);
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