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

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter pw=resp.getWriter();
		HttpSession hs=req.getSession();

		String oldpassword=req.getParameter("oldpassword");
		String newpassword=req.getParameter("newpassword");
		
		if(oldpassword!=null && 
		   !oldpassword.isEmpty() && 
		   newpassword!=null &&
		   !newpassword.isEmpty())
		{
			try 
			{
				Connection conn=DBConfig.getConnection();
				PreparedStatement ps=conn.prepareStatement("update users set password=? where userid=? and password=?");
				ps.setString(1, newpassword);
				ps.setInt(2, (Integer)hs.getAttribute("uid"));
				ps.setString(3, oldpassword);
				ps.executeUpdate();

				pw.println("<script>"
						 + "alert('Password Changed. Please Login Again!!');"
						 + "window.location='login.jsp';"
						 + "</script>");
			}
			catch(Exception e)
			{
				e.printStackTrace();			
				pw.println("<script>"
						 + "alert('Something went wrong. Please try later!!');"
						 + "window.location='login.jsp';"
						 + "</script>");
			}
		}
		else
		{
			pw.println("<script>"
					 + "alert('Password field cannot be blank!!');"
					 + "window.location='changepassword.jsp';"
					 + "</script>");
		}

	}
}
