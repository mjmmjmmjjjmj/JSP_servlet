package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gugudan02")
public class gugudan02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		System.out.println("gugudan02");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		String direction = req.getParameter("dir");
		boolean isVertical = "ver".equalsIgnoreCase(direction);
//		boolean isHorizontal = "hor".equalsIgnoreCase(direction);
		
		if(isVertical) {
			for (int i = 2; i <= 9; i++) {
				out.println("<h3>"+i+"단</h3>");
				for(int j = 1; j<10; j++) {
	    			out.print(i + " * "+j+" = "+(i*j)+" <br/>");
				}
			}
		} else {
			out.println("<table><tr>");
			for (int i = 2; i <= 9; i++) {
				out.print("<td><h3>"+i+"단</h3></td>");
				out.print("<td>");
				for(int j = 1; j<10; j++) {
	    			out.println(i + " * "+j+" = "+(i*j)+"<br/>");
				}
			}
            out.println("</tr></table>");
			
		}
		out.close();
	}

}
