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

@WebServlet("/OTPConfirmationServlet")
public class OTPConfirmationServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw=resp.getWriter();
		HttpSession hs=req.getSession();

		String o=(String) hs.getAttribute("otp");
		String usrotp=req.getParameter("userotp");

		try {
			if(o!=null && o.equals(usrotp)) {
				Connection conn=DBConfig.getConnection();
				PreparedStatement ps=conn.prepareStatement("update users set status=? where userid=?");
				ps.setString(1, "A");
				ps.setInt(2, (Integer)hs.getAttribute("uid"));
				ps.executeUpdate();

				pw.println("<script>"
						 + "alert('Success. Login to get Started!!');"
						 + "window.location='login.jsp';"
						 + "</script>");
			}
			else {
				hs.removeAttribute("otp");
				pw.println("<script>"
						 + "alert('OTP Incorrect. Login and get OTP again!!');"
						 + "window.location='login.jsp';"
						 + "</script>");
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();			
			pw.println("<script>"
					 + "alert('Something went wrong. Please try again!!');"
					 + "window.location='home.jsp';"
					 + "</script>");
		}
	}
}

