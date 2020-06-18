package com.hov.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hov.dbconfig.DBConfig;
import com.hov.email.Email;
import com.hov.model.User;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{	
		PrintWriter pw = resp.getWriter();
		String otp = "";
		int rnumber =0;
		HttpSession hs=req.getSession();
		
		User u=new User();
		u.setEmail(req.getParameter("email"));
		u.setPassword(req.getParameter("password"));

		try 
		{
			Connection conn=DBConfig.getConnection();
			PreparedStatement ps=conn.prepareStatement("select * from users where email=? and password=?");
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{	
				String sts = rs.getString("status");
				if(sts.equalsIgnoreCase("A"))
				{
					hs.setAttribute("uid", rs.getInt("userid"));
					hs.setAttribute("uname", rs.getString("username"));
					
					pw.println("<script>"
							+  "window.location='home.jsp?usrid="+rs.getInt("userid")+"';"
							+  "</script>");
				}
				else
				{
					Random rnd = new Random();
				    rnumber = rnd.nextInt(999999);
				    otp = String.format("%06d", rnumber);

					hs.setAttribute("otp", otp);
					hs.setAttribute("uid", rs.getInt("userid"));

					Email e=new Email(u.getEmail(), 
								      "OTP for Bio.Share Account Activation!!", 
								      "Hi " + u.getUsername() + ", Your Account Activation OTP Code is " + otp);
					e.sendEmail();
					pw.println("<script>"
							 + "alert('Email already used in an inactive account. OTP has been send!!');"
							 + "window.location='otp.jsp';"
							 + "</script>");
				}
			}
			else
			{
				pw.println("<script>"
						+  "alert('Incorrect Email/Password Combination');"
						+  "window.location='login.jsp';"
						+  "</script>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			pw.println("<script>"
					+  "alert('Something went wrong. Please try later!!');"
					+  "window.location='home.jsp';"
					+  "</script>");
		}
	}
}
