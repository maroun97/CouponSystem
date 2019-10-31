package com.cmw.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;
@SuppressWarnings("serial")
@WebServlet("/Login")
public class Login extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String type=request.getParameter("type").toString();
		String email=request.getParameter("email").toString();
		String pass=request.getParameter("pass").toString();
		System.out.println(type);
		System.out.println(email);
		System.out.println(pass);
		if(type.equals("admin")) {
			adminLogin(email,pass,session,response);
		}
		else {
			if(type.equals("company")) {
				companyLogin(email,pass,session,response);
			}
			else {
				if(type.equals("customer")) {
					customerLogin(email,pass,session,response);
				}
				else {
					System.err.println("type not found");
					System.out.println("couldn't login");
					System.out.println("please check type it must be all in small chars");
				}
			}
		}
		
		
			
	}

	private void customerLogin(String email, String pass, HttpSession session, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {

		CustomerFacade customerFacade=new CustomerFacade();
		
		if(customerFacade.login(email, pass)) {
			session.setAttribute("LOGIN",true);
			session.setAttribute("type","customer");
			session.setAttribute("email",email);
			User user = new User(email,"customer");
			session.setAttribute("customerFacade", customerFacade);

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
			System.err.println("login faild");
	

		
	}

	private void companyLogin(String email, String pass, HttpSession session, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {

		CompanyFacade companyFacade=new CompanyFacade();
		
		if(companyFacade.login(email, pass)) {
			session.setAttribute("LOGIN",true);
			session.setAttribute("type","company");
			session.setAttribute("email",email);
			User user = new User(email,"company");
			session.setAttribute("companyFacade", companyFacade);

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
			System.err.println("login faild");
	
		
	}

	private void adminLogin(String email, String pass, HttpSession session, HttpServletResponse response) throws IOException {     
		
		AdminFacade adminFacade=new AdminFacade();
		
		if(adminFacade.login(email, pass)) {
			session.setAttribute("LOGIN",true);
			session.setAttribute("type","admin");
			session.setAttribute("email",email);
			User user = new User(email,"admin");

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
			System.err.println("login faild");
	
		
		
		
		
	}

}
