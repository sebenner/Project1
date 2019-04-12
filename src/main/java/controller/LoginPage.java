package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.ERSUser;
import repository.UserRepositoryImpl;

//@WebServlet({"/login"/*,"/users"*/})
public class LoginPage /*extends HttpServlet*/{

	public static String login(HttpServletRequest req, HttpServletResponse resp) {
		ERSUser user = null;
		/*Enumeration<String> pNames = req.getParameterNames();
		while (pNames.hasMoreElements()){
			System.out.println("req" + pNames.nextElement());
		}*/
		
		//if(req.getParameter("requsername") == null) {	//login
		System.out.println("requsername = " + req.getParameter("requsername"));
		UserRepositoryImpl rep = new UserRepositoryImpl();
		user = rep.loginUser(req.getParameter("email"), req.getParameter("password"));
		System.out.println("user = " + user);
		/*else {											//register
			System.out.println(req.getParameter("username"));
			UserRepositoryImpl rep = new UserRepositoryImpl();
			user = rep.registerUser(req.getParameter("username"), req.getParameter("password"), 
											req.getParameter("firstname"), req.getParameter("lastname"), 
											req.getParameter("email"), "Employee");
			System.out.println(user);

		}*/
		System.out.println(user);
		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("uId", user.getId());
			System.out.println("role = " + user.getRole());
			if (user.getRole().equals("Employee")) {
				return "EmployeeHome.html";
			}
			else {
				return "ManagerHome.html";
			}
			//return "Home.html";
		}
		else {
			return "Login.html";
		}
	}
	
	
	/*protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserRepositoryImpl rep = new UserRepositoryImpl();
		ERSUser user = rep.LoginUser(req.getParameter("email"), req.getParameter("password"));
		if (user != null) {
			
		}*/
		//HttpSession session=req.getSession();
		//resp.
		//PrintWriter writer=resp.getWriter();
		//resp.setContentType("text/html");
		/*writer.println("<form action='login' method='post'>");
		writer.print("<input type='text' name='username' display='none'>");
		writer.print("<input type='password' name='password'>");
		writer.println("<input type='submit' >");
		writer.println("</form>");*/
		/*if(session.getAttribute("name")!= null) {
			session.invalidate();
		}*/
		/*
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<form action='login' method='get'>");
		writer.println("<div class='form-group'>");
		writer.print("<label for='exampleInputEmail1'>Email address</label>");
		writer.print("<input type='email' class='form-control' id='exampleInputEmail1' aria-describedby='emailHelp' placeholder='Enter email' required>");
		writer.println("</div>");
		writer.println("<div class='form-group'>");
		writer.print("<label for='exampleInputPassword1'>Password</label>");
		writer.print("<input type='password' class='form-control' id='exampleInputPassword1' placeholder='Password' required>");
		writer.println("</div>");
		writer.println("<button type='submit' class='btn btn-primary'>Submit</button>");
		writer.println("</form>");
		writer.println("<script src='https://code.jquery.com/jquery-3.3.1.slim.min.js' integrity='sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo' crossorigin='anonymous'></script>");
		writer.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js' integrity='sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1' crossorigin='anonymous'></script>");
		writer.println("<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js' integrity='sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM' crossorigin='anonymous'></script>");
		writer.println("</body>");
		writer.println("</html>");*/
		/*RequestDispatcher rd = req.getRequestDispatcher("Login.html");
        rd.forward(req, resp);*/
	//}
	
	//protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//HttpSession session=req.getSession();
		//PrintWriter writer=resp.getWriter();
		//resp.setContentType("text/html");
		/*if(session.getAttribute("name")!= null) {
			session.invalidate();
		}*/
		/*UserRepositoryImpl rep = new UserRepositoryImpl();
		ERSUser user = rep.RegisterUser(req.getParameter("username"), req.getParameter("password"), 
										req.getParameter("firstname"), req.getParameter("lastname"), 
										req.getParameter("email"), "Employee");
		if (user != null) {
			
		}
		//RequestDispatcher rd = req.getRequestDispatcher("Login.html");
        //rd.forward(req, resp);
	}*/
}
