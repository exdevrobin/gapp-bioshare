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

@WebServlet("/PasswordOTPServlet"
		+ "")
public class PasswordOTPServlet  extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter pw=resp.getWriter();
		HttpSession hs=req.getSession();

		String userotp=req.getParameter("userotp");
		String otp=(String)hs.getAttribute("otp");
		
		if(otp.equalsIgnoreCase(userotp) && !userotp.equalsIgnoreCase(""))
		{
			try 
			{
				Connection conn=DBConfig.getConnection();
				PreparedStatement ps=conn.prepareStatement("update users set status=? where email=?");
				ps.setString(1, "A");
				ps.setString(2, (String)hs.getAttribute("otpemail"));
				ps.executeUpdate();

				pw.println("<script>"
						 + "window.location='profile.jsp?usrid="+hs.getAttribute("uid")+"';"
						 + "</script>");
			}
			catch(Exception e)
			{
				e.printStackTrace();			
				pw.println("<script>"
						 + "alert('Account Activated. Please Login!!');"
						 + "window.location='login.jsp';"
						 + "</script>");
			}
		}
		else
		{
			pw.println("<script>"
					 + "alert('Registered Not Successful. Data Inadequate!!');"
					 + "window.location='register.jsp';"
					 + "</script>");
		}

	}
}
