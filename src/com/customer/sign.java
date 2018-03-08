package com.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class sign extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		PrintWriter aa1 = resp.getWriter();
		String u= req.getParameter("us");
		String p= req.getParameter("p");
		String cp = req.getParameter("p2");
		Entity e = new Entity("person" );
		Query q = new Query("person");
		PreparedQuery pq = ds.prepare(q); 
		for (Entity result : pq.asIterable()) 
		{ 
		String first = (String) result.getProperty("name"); 
		try {
			if(u.equals(first))
			{
				aa1.println("Username already exists");
				RequestDispatcher rd = req.getRequestDispatcher("signup.html");
				rd.forward(req, resp);
				
			}
			else if (!p.equals(cp))
			{
				aa1.println("Password doesnot match");
				RequestDispatcher rd = req.getRequestDispatcher("signup.html");
				rd.forward(req, resp);
			}
			else
			{
				e.setProperty("name", u );
				e.setProperty("password", p );
				e.setProperty("Confirm_Password", cp);
				ds.put(e);
				aa1.println("Signed up successfully Login to continue");
				resp.sendRedirect("index.html"); 
			}
			}finally{			
			}
		}
	}
}
