package com.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class second extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession(false);
	try {
		if(session!=null)
		{
			out.println("Welcome" + session.getAttribute("user1"));
			RequestDispatcher rd = req.getRequestDispatcher("success.html");
			rd.forward(req, resp);
			
		}
//		else
//		{
//			RequestDispatcher rd = req.getRequestDispatcher("index.html");
//			rd.forward(req, resp);
//		}
	}finally
	{
		
	}
	}

}
