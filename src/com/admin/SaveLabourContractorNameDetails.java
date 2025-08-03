package com.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/SaveLabourContractorNameDetails")
public class SaveLabourContractorNameDetails extends  HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String contractorName=request.getParameter("contractorName");
		String workDetails=request.getParameter("workDetails");
		HttpSession hs=request.getSession();

		try {
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into tbl_labour_contractor_details(contractorName,workDetails)values('" + contractorName+ "','" + workDetails + "')");
			if (addCustomer > 0) {
				String message="Customer register successfully.";
				//hs.setAttribute("message", message);
				response.sendRedirect("AddLabourContractor.jsp");
			} else {
				String message="Customer registration fail";
				//hs.setAttribute("message", message);
				response.sendRedirect("AddLabourContractor.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
