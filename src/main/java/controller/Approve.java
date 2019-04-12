package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.UserRepositoryImpl;

public class Approve {

	public static String approve(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return "Login.html";
		}
		else {
			UserRepositoryImpl rep = new UserRepositoryImpl();
			System.out.println("Approve" + req.getParameter("Approve"));
			System.out.println("Approve" + req.getParameter("Approve"));
			System.out.println("Deny" + req.getParameter("Deny"));
			//System.out.println(Integer.parseInt(req.getParameter("Approve")));
			//System.out.println(Integer.parseInt(req.getParameter("Deny")));
			System.out.println((int) session.getAttribute("uId"));
			if (req.getParameter("Approve") != null) {
				rep.updateRequest(Integer.parseInt(req.getParameter("Approve")), (int) session.getAttribute("uId"), "Approved");
			}
			else {
				rep.updateRequest(Integer.parseInt(req.getParameter("Deny")), (int) session.getAttribute("uId"), "Denied");
			}
			System.out.println("Approve" + req.getParameter("Approve"));
			System.out.println("Deny" + req.getParameter("Deny"));
			//rep.updateRequest(rId, (int) session.getAttribute("uId"), status);//(Float.parseFloat(req.getParameter("reimbAmount")), req.getParameter("reimbDescription"), (int) session.getAttribute("uId"),req.getParameter("reimbType"));
		    return "ManagerHome.html";
		}
	}

}
