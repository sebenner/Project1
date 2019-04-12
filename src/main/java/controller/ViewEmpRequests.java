package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import repository.EmpRequests;
import repository.UserRepositoryImpl;

public class ViewEmpRequests {

	public static String view(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		final Logger logger = Logger.getLogger("log");
		UserRepositoryImpl rep = new UserRepositoryImpl();
		HttpSession session = req.getSession();
		List<EmpRequests> empReqList = rep.getEmployeesRequests((int) session.getAttribute("uId"));
		String output = new Gson().toJson(empReqList);
		resp.setContentType("application/json");
		PrintWriter writer=resp.getWriter();
		logger.info("get Employees Requests 4");
		logger.info(output);
		writer.write(output);
		return "";
	}

}
