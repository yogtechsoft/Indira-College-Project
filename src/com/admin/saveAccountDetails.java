package com.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/saveAccountDetails")
public class saveAccountDetails extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String contractorId = request.getParameter("customerName");
		String contractorWorkDetails = request.getParameter("contractorWork");
		String campus = request.getParameter("campus");
		String building = request.getParameter("buildingArea");
		String floor = request.getParameter("floor");
		String workDetails = request.getParameter("workDetails");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String totalCost = request.getParameter("totalCost");
		String advancePayment = request.getParameter("advancePayment");
		String date = request.getParameter("date");
		HttpSession hs=request.getSession();
		try {
			
			String contName="";
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT ss.id,ss.contractorName FROM `tblnewconstructionworkdetails` lp INNER JOIN tblcontractordetails ss on ss.id=lp.contractorId where contractorId="+contractorId+" group by ss.contractorName");
				while(captchResultSet.next()) {
					 contName=captchResultSet.getString(2);
				}
				
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into `tbl_account_details`(contractorName,campus,building,floor,workdetails,startdate,enddate,totalamount,advancepayment,date,contractorid)"
					+ "values('" + contName+ "','" + campus + "','" + building + "','" + floor + "','" + workDetails + "','" + startDate + "','"+ endDate + "','" + totalCost + "','" + advancePayment + "','" + date + "'," + contractorId + ")");
			if (addCustomer > 0) {
				String message="Data Added successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("AccountInformationDetails.jsp");
			} else {
				String message="fail";
				hs.setAttribute("message", message);
				response.sendRedirect("AccountInformationDetails.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
