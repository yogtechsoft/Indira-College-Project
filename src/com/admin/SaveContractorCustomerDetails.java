package com.admin;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/SaveContractorCustomerDetails")
public class SaveContractorCustomerDetails extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String contractorName  = request.getParameter("contractorNameDetails");
		String worktypeName = request.getParameter("workTypeName");
		String area = request.getParameter("area");
		String rate = request.getParameter("Rate");
		String description = request.getParameter("description");
		String materialDate = request.getParameter("materialDate");
		String totalAmount = request.getParameter("totalAmount");
		String customerId = request.getParameter("customerName");
		String custname=null;
		HttpSession hs=request.getSession();
		
		try {
			//fetch customer name from customer table
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("select * from tbl_customer_register where id="+customerId);
			while (captchResultSet.next()) {
				custname = captchResultSet.getString(2);
			}		
		
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into tbl_save_labour_contractor_details(contractor_name,work_name,area,rate,description,date,total_amount,customerId,customer_name)"
					+ "values('" + contractorName
							+ "','" + worktypeName + "','" + area + "','" + rate + "','" + description + "','" + materialDate + "', '"+totalAmount+"', '"+customerId+"', '"+custname+"')");
			if (addCustomer > 0) {
				String message="Record Added successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("AddLabourContractor.jsp");
			} else {
				String message="Some Problem Occurs!";
				hs.setAttribute("message", message);
				response.sendRedirect("AddLabourContractor.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
