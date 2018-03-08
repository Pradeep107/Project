package com.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Login extends HttpServlet
{
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
{
	String message = "";
	boolean loginstatus = true;
	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	PrintWriter out = resp.getWriter();
	 String un=req.getParameter("username");
		String pw=req.getParameter("password");
	Entity e = new Entity("person" );
    Query q = new Query("person");
	PreparedQuery pq = ds.prepare(q); 
	HttpSession session = req.getSession();
	session.setAttribute("user1", un);
			for (Entity result : pq.asIterable()) 
			{ 
			String firstName = (String) result.getProperty("name"); 
			String password = (String) result.getProperty("password"); 
			try {
			if(un.equals(firstName))
			{
				if(pw.equals(password))
				{
					
					RequestDispatcher rd = req.getRequestDispatcher("second");
					rd.forward(req, resp);
				}
				else 
				{
					message="incorrect password";
					loginstatus=false;
				}	
			}
			} finally {
			 
			}
			}
//			if(loginstatus == true)
//			{
			
//				RequestDispatcher rd = req.getRequestDispatcher("second");
//				rd.forward(req, resp);
//			}
			if(loginstatus == false)
			{
				RequestDispatcher rd = req.getRequestDispatcher("index.html");
				rd.include(req, resp);
				out.println(message);
			}
			
			
}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out = resp.getWriter();    
	RequestDispatcher rd = req.getRequestDispatcher("index.html");
		rd.include(req, resp);
		out.println("Please Enter the field before logging in");
	}
}
