package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/myservlet04")
public class MyServlet04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		System.out.println("MyServlet04");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<table border=1>");
		out.print("<tr>"
				+"<td>번호</td>"
				+ "<td>나라</td>"
				+ "<td>수도</td>"
//				+ "<td>인구</td>"
				+ "</tr>");
		out.print("<tr>"
				+"<td>1</td>"
				+"<td>대한민국</td>"
				+"<td>서울</td>"
//				+"<td>1000만</td>"
				+ "</tr>");
		out.print("<tr>"
				+"<td>2</td>"
				+"<td>미국</td>"
				+"<td>워싱턴</td>"
//				+"<td>70만</td>"
				+ "</tr>");
		out.print("<tr>"
				+"<td>3</td>"
				+"<td>일본</td>"
				+"<td>도쿄</td>"
//				+"<td>1400만</td>"
				+ "</tr>");
		out.print("<tr>"
				+"<td>4</td>"
				+"<td>중국</td>"
				+"<td>베이징</td>"
//				+"<td>2100만</td>"
				+ "</tr>");
		out.print("</table>");
		out.close();
	}

}
