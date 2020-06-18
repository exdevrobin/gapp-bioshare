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

@WebServlet("/UnfollowServlet")
public class UnfollowServlet extends HttpServlet
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
			PreparedStatement ps=conn.prepareStatement("delete from following where userid=? and followingid=?");
			ps.setString(1, frnd.getUserid()+"");
			ps.setString(2, frnd.getFollowingid()+"");
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		pw.println("<script>"
				 + "window.location='following.jsp';"
				 + "</script>");
	}
}
