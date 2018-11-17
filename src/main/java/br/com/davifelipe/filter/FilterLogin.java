package br.com.davifelipe.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.davifelipe.model.UserEnt;

@WebFilter(urlPatterns={"/*"})
public class FilterLogin implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		UserEnt loggedUser = (UserEnt) session.getAttribute("loggedUser");
		
		String url = req.getServletPath();
		
		if (!url.equalsIgnoreCase("/login.jsf") 
		&& url.indexOf("/javax.faces.resource",0) == -1
		&& loggedUser == null){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsf");
			dispatcher.forward(request, response);
			return;
		}else {
			
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
