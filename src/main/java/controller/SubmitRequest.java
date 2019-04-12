package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.UserRepositoryImpl;

public class SubmitRequest {

	public static String submit(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		if (session == null) {
			return "Login.html";
		}
		else {
			UserRepositoryImpl rep = new UserRepositoryImpl();
			rep.submitRequest(Float.parseFloat(req.getParameter("reimbAmount")), req.getParameter("reimbDescription"), (int) session.getAttribute("uId"),req.getParameter("reimbType"));
		    return "SubmitRequest.html";
		}
		//return "Login.html";
	}

}
