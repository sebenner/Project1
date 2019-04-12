package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		switch(req.getRequestURI()) {
		case "/Project1/Login.do":
			return LoginPage.login(req,resp);
		case "/Project1/EmployeeHome.do":
			return EmployeeHome.home(req,resp);
		case "/Project1/SubmitRequest.do":
			return SubmitRequest.submit(req,resp);
		case "/Project1/ViewEmpRequests.do":
			return ViewEmpRequests.view(req,resp);
		case "/Project1/ViewAllRequests.do":
			return ViewAllRequests.view(req,resp);
		case "/Project1/Logout.do":
			return Logout.logout(req,resp);
		case "/Project1/Approve.do":
			return Approve.approve(req,resp);
		}
		return null;
	}

}
