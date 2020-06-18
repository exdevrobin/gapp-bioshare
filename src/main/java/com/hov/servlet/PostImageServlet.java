package com.hov.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hov.dbconfig.DBConfig;

@WebServlet(name="PostImageServlet", urlPatterns = "/PostImageServlet")
public class PostImageServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String postid=req.getParameter("postid");
		
		try
		{
			Connection conn=DBConfig.getConnection();
			PreparedStatement ps=conn.prepareStatement("select photo from posts where postid=?");
			ps.setString(1, postid);
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
