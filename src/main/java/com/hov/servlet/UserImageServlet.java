package com.hov.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hov.dbconfig.DBConfig;

@WebServlet(name="UserImageServlet", urlPatterns = "/UserImageServlet")
public class UserImageServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String userid=req.getParameter("userid");
		
		try
		{
			Connection conn=DBConfig.getConnection();
			PreparedStatement ps=conn.prepareStatement("select photo from users where userid=?");
			ps.setString(1, userid);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				Blob blob=rs.getBlob("photo");
				byte arr[]=blob.getBytes(1, (int)blob.length());
				conn.close();
				resp.getOutputStream().write(arr);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
