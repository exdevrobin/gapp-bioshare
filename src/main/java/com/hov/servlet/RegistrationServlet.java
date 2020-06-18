package com.hov.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.hov.dbconfig.DBConfig;
import com.hov.email.Email;
import com.hov.model.User;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/RegistrationServlet")
@MultipartConfig(maxFileSize = 999999999999999L)
public class RegistrationServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = new User();
		u.setUsername(req.getParameter("username"));
		u.setEmail(req.getParameter("email"));
		u.setPassword(req.getParameter("password"));
		
		Part part=req.getPart("photo");
		InputStream is=part.getInputStream();

		PrintWriter pw = resp.getWriter();
		String otp = "";
		int rnumber = 0;
		HttpSession hs=req.getSession();
		
		try {
			Connection conn=DBConfig.getConnection();
			PreparedStatement ps1=conn.prepareStatement("select * from users where email=?");
			ps1.setString(1, u.getEmail());
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				String sts = rs.getString("status");
				if (sts.equals("A")) {
					pw.println("<script>"
							 + "alert('Email already used in an active account. Just Login!!');"
							 + "window.location='login.jsp';"
							 + "</script>");
				}
				else {
					Random rnd = new Random();
				    rnumber = rnd.nextInt(999999);
				    otp = String.format("%06d", rnumber);

					hs.setAttribute("otp", otp);

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
			else {
				hs.setAttribute("otpemail", u.getEmail());
				PreparedStatement ps2=conn.prepareStatement("insert into users (username, email, password, photo, status) values (?,?,?,?,?)");
				ps2.setString(1, u.getUsername());
				ps2.setString(2, u.getEmail());
				ps2.setString(3, u.getPassword());
				ps2.setBlob(4, is);
				ps2.setString(5,"I");
				ps2.executeUpdate();
				
				PreparedStatement ps3=conn.prepareStatement("select * from users where email=? and password=? limit 1");
				ps3.setString(1, u.getEmail());
				ps3.setString(2, u.getPassword());
				ResultSet rs3 = ps3.executeQuery();
				
				if(rs3.next()) {	
					Random rnd = new Random();
				    rnumber = rnd.nextInt(999999);
				    otp = String.format("%06d", rnumber);
				    
					hs.setAttribute("otp", otp);
					hs.setAttribute("uid", rs3.getInt("userid"));
	
					Email e=new Email(u.getEmail(), 
								      "OTP for Bio.Share Account Activation!!", 
								      "Hi " + u.getUsername() + ", Your Account Activation OTP Code is " + otp);
					e.sendEmail();
					pw.println("<script>"
							 + "window.location='otp.jsp';"
							 + "</script>");
				}
				else {

					pw.println("<script>"
							 + "alert('Registered Not Successful. Please Try Again!!');"
							 + "window.location='home.jsp';"
							 + "</script>");
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<script>"
					 + "alert('Registered Not Successful. Please Try Again!!');"
					 + "window.location='home.jsp';"
					 + "</script>");
		}
	}
}