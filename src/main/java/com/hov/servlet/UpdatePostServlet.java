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
import com.hov.model.Post;

@WebServlet("/UpdatePostServlet")
public class UpdatePostServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Post p=new Post();
		String postid=req.getParameter("postid");
		p.setTitle(req.getParameter("title"));
		p.setContent(req.getParameter("content"));
		p.setHashtag(req.getParameter("hashtag"));
		
		PrintWriter pw=resp.getWriter();
		HttpSession hs=req.getSession();
		try 
		{
			Connection conn=DBConfig.getConnection();
			PreparedStatement ps=conn.prepareStatement("update posts set title=?, content=?, hashtag=? where postid=?");
			ps.setString(1, p.getTitle());
			ps.setString(2, p.getContent());
			ps.setString(3, p.getHashtag());
			ps.setString(4, postid);
			ps.executeUpdate();

			pw.println("<script>"
					 + "window.location='profile.jsp?usrid="+hs.getAttribute("uid")+"';"
					 + "</script>");
		}
		catch(Exception e)
		{
			e.printStackTrace();			
			pw.println("<script>"
					 + "window.location='profile.jsp?usrid="+hs.getAttribute("uid")+"';"
					 + "</script>");
		}
	}
}

