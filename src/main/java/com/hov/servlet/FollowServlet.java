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
import com.hov.model.Following;

@WebServlet("/FollowServlet")
public class FollowServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Following frnd=new Following();
		HttpSession hs=req.getSession();
		PrintWriter pw=resp.getWriter();
		
		frnd.setUserid(Integer.parseInt(hs.getAttribute("uid").toString()));
		frnd.setFollowingid(Integer.parseInt(req.getParameter("followid")));
		try
		{
			Connection conn=DBConfig.getConnection();
			PreparedStatement ps=conn.prepareStatement("insert into following(userid, followingid) values(?,?)");
			ps.setString(1, frnd.getUserid()+"");
			ps.setString(2, frnd.getFollowingid()+"");
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		pw.println("<script>"
				 + "window.location='findusers.jsp';"
				 + "</script>");
	}
}


