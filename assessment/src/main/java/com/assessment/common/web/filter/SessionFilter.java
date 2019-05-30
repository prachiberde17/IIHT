package com.assessment.common.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assessment.data.User;

public class SessionFilter implements Filter {

	@Override
	public void destroy() {
		// ...
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void doFilter(ServletRequest request, 
               ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
//		HttpSession session = ((HttpServletRequest) request).getSession();
//		if (request.getParameter("JSESSIONID") != null) {
//		    Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
//		    ((HttpServletResponse) response).addCookie(userCookie);
//		} else {
//		    String sessionId = session.getId();
//		    Cookie userCookie = new Cookie("JSESSIONID", sessionId);
//		    ((HttpServletResponse) response).addCookie(userCookie);
//		}
		System.out.println("123 ");
		try {
			String page = ((HttpServletRequest)request).getRequestURI();
			System.out.println("page is "+page);
			if(page.endsWith("/login") || page.endsWith("/authenticate") || page.endsWith("publicTest") || page.contains("setUpTenant") || page.contains("downloadUserSessionReportsForTest")) {
				chain.doFilter(request, response);
			}
			else if(page.contains("images") || page.contains("css") || page.contains("scripts") || page.contains("fonts") || page.contains("html") || page.contains("startTestSession")){
				chain.doFilter(request, response);
			}
			else {
				 User user = (User) ((HttpServletRequest)request).getSession().getAttribute("user");
				 	if(user == null) {
				 		((HttpServletResponse)response).sendRedirect("login");
				 	}
				 	else {
				 		chain.doFilter(request, response);
				 	}
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			//((HttpServletResponse)response).sendRedirect("login");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			((HttpServletRequest)request).getSession().setAttribute("errorStack", sStackTrace);
			((HttpServletResponse)response).sendRedirect("problem");
		}

	}

}

