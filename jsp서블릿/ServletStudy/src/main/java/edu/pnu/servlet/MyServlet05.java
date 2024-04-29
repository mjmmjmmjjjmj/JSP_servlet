package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class Capital {
	
	
	public Capital(String nation, String capital, String pop) {
		super();
		this.nation = nation;
		this.capital = capital;
		this.pop = pop;
	}
	String nation;
	String capital;
	String pop;
}

@WebServlet("/myservlet05")
public class MyServlet05 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	@Override
//	public void service(HttpServletRequest req, HttpServletResponse res)
//				throws ServletException, IOException {
//		System.out.println("MyServlet05");
//		res.setContentType("text/html; charset=utf-8");
//		PrintWriter out = res.getWriter();
//		out.print("<table border=1>");
//		out.print("<tr>"
//				+ "<td>번호</td>"
//				+ "<td>나라</td>"
//				+ "<td>수도</td>"
//				+ "<td>인구</td>"
//				+ "</tr>");
//		out.print("<tr>"
//				+"<td>1</td>"
//				+"<td>대한민국</td>"
//				+"<td>서울</td>"
//				+"<td>1000만</td>"
//				+ "</tr>");
//		out.print("<tr>"
//				+"<td>2</td>"
//				+"<td>미국</td>"
//				+"<td>워싱턴</td>"
//				+"<td>70만</td>"
//				+ "</tr>");
//		out.print("<tr>"
//				+"<td>3</td>"
//				+"<td>일본</td>"
//				+"<td>도쿄</td>"
//				+"<td>1400만</td>"
//				+ "</tr>");
//		out.print("<tr>"
//				+"<td>4</td>"
//				+"<td>중국</td>"
//				+"<td>베이징</td>"
//				+"<td>2100만</td>"
//				+ "</tr>");
//		out.print("</table>");
//		out.close();
//	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		List<Capital> list = new ArrayList<>();
		list.add(new Capital("대한민국", "서울", "1000만"));
		list.add(new Capital("미국", "워싱턴", "70만"));
		list.add(new Capital("일본", "도쿄", "1400만"));
		list.add(new Capital("중국", "베이징", "2100만"));
		
		System.out.println("MyServlet05");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<table border=1px"
				+ "text-align=center"
				+ "margin=auto>");
		out.print("<tr>"
				+ "<td>번호</td>"
				+ "<td>나라</td>"
				+ "<td>수도</td>"
				+ "<td>인구</td>"
				+ "</tr>");
		for(int i=0; i<list.size(); i++) {
			Capital c = list.get(i);
			out.print("<tr>"
					+"<td>"+ (i+1) + "</td>"
					+"<td>"+c.nation+"</td>"
					+"<td>"+c.capital+"</td>"
					+"<td>"+c.pop+"</td>"
					+ "</tr>");
		}
		out.print("</table>");
		out.close();
	}
}
