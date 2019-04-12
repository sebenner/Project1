package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.RequestHelper;

public class MasterServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Yay");
		RequestHelper.process(req, resp);
		//resp.sendRedirect(RequestHelper.process(req, resp));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Yay2");
		String tURL = RequestHelper.process(req, resp);
		System.out.println(tURL);
		req.getRequestDispatcher(tURL).forward(req, resp);
	}
}
