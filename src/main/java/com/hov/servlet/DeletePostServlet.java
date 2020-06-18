package com.hov.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hov.dbconfig.DBConfig;

@WebServlet("/DeletePostServlet")
public class DeletePostServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String postid = req.getParameter("postid");

		PrintWriter pw=resp.getWriter();
		HttpSession hs=req.getSession();
		try 
		{	
			Connection conn=DBConfig.getConnection();
			PreparedStatement ps=conn.prepareStatement("delete from posts where postid=?");
			ps.setString(1, postid);
			ps.executeUpdate();
			
			pw.println("<script>"
					+   "window.location='profile.jsp?usrid="+hs.getAttribute("uid")+"';"
					+   "</script>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			pw.println("<script>"
					+   "window.location='profile.jsp?usrid="+hs.getAttribute("uid")+"';"
					+   "</script>");
		}
	}
}


