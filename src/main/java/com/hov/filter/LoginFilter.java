package com.hov.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/postadd.jsp", 
						  "/postlist.jsp"})
public class LoginFilter implements Filter
{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		
		HttpSession hs=req.getSession();
		if(hs.getAttribute("uid")==null)
		{
			resp.sendRedirect("login.jsp");
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() 
	{
		
	}
}