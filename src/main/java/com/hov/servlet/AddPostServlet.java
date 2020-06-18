package com.hov.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.hov.dbconfig.DBConfig;
import com.hov.model.Post;

@WebServlet(name = "AddPostServlet", urlPatterns = "/AddPostServlet")
@MultipartConfig(maxFileSize = 999999999999999L)
public class AddPostServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Post p=new Post();
		p.setTitle(req.getParameter("title"));
		p.setContent(req.getParameter("content"));
		p.setHashtag(req.getParameter("hashtag"));
		
		Part part=req.getPart("photo");
		InputStream is=part.getInputStream();
				
		PrintWriter pw=resp.getWriter();
		HttpSession hs=req.getSession();
		
		try 
		{
			Connection conn=DBConfig.getConnection();
			PreparedStatement ps=conn.prepareStatement("insert into posts (userid, title, content, hashtag, photo, datetime)  values (?,?,?,?,?,?)");
			ps.setString(1, hs.getAttribute("uid").toString());
			ps.setString(2, p.getTitle());
			ps.setString(3, p.getContent());
			ps.setString(4, p.getHashtag());
			ps.setBlob(5, is);
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
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
