package edu.pnu.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L; //Default Serial Version ID 추가
	
	@Override 										//ctrl+space에서 method 추가
	protected void service(HttpServletRequest req, HttpServletResponse res)
					throws ServletException, IOException {
		System.out.println("MyServlet01");
	}
}
