package com.cmw.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
  	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		System.out.println("checking loggin");
		HttpSession session = request.getSession();
		session.setAttribute("LOGIN",false);
		if( session.getAttribute("LOGIN").equals(false)) {
			User user = new User(session.getAttribute("email").toString(),session.getAttribute("type").toString());	
			user.LOGIN="false";
			String json="";
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			json = ow.writeValueAsString(user);
			

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(json);
			out.flush(); 
		}
		else
		{
			System.out.println("session log out = true " + session.getAttribute("LOGIN").toString());
		}
		
	}

}
