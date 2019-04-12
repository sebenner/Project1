package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout {

	public static String logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession currSession = req.getSession(false);
		if (currSession != null) {
			currSession.invalidate();
		}
		resp.sendRedirect("Login.html");
		return "Login.html";
	}

}
