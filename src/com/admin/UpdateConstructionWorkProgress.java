package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.log.SysoLogger;
import com.model.WorkProgressModel;

@WebServlet("/UpdateConstructionWorkProgress")
public class UpdateConstructionWorkProgress extends  HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("uname");
		try {
			String contractorDetails = request.getParameter("customerName");
			String workDetails=request.getParameter("workDetails");
			String totalCompletedWork="";
			String totalPendingWork="";
			//fetch work details
			String contractorWork = request.getParameter("contractorWork");
			String campusName = request.getParameter("campusName");
			String building = request.getParameter("building");
			String floor = request.getParameter("floor");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String workStatus = request.getParameter("workStatus");
			
			
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT totalWorkComplete,totalPendingWork FROM tblnewconstructionworkdetails where workDetails='"+contractorWork+"' and contractorId="+contractorDetails);
			while (captchResultSet.next()) {
				totalCompletedWork=	captchResultSet.getString(1);
				totalPendingWork= captchResultSet.getString(2);
			}
			Date today = new Date(); // Get current date
	        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        String formattedDate = formatter.format(today);
			
			int update = DatabaseConnection.insertUpdateFromSqlQuery
			("update tblnewconstructionworkdetails set totalWorkComplete='"+workDetails+"' WHERE  workDetails='"+contractorWork+"' and contractorId="+contractorDetails);
			if(update == 1) {
				String str="Record Update Succrssfully";				
				//insert siteInspection Details
				int i = DatabaseConnection.insertUpdateFromSqlQuery("insert into tblsiteinspectiondetails(siteWorkDetails,campus,building,floor,startDate,endDate,dailyTaskDetails,contractorId,todayDate,modifiedBy) "
						+ "values('" + contractorWork+ "','" + campusName + "','"+building+"','" + floor + "','" + startDate + "','"+endDate+"','"+workStatus+"','"+contractorDetails+"','"+formattedDate+"','"+username+"')");
				if (i > 0) {
					String success = "Record Updated successfully.";
					session.setAttribute("message", success);
					response.sendRedirect("PieChart.jsp");
				}
				
			}
			
			
			
	       // response.getWriter();
			//new Gson().toJson(getDetails, response.getWriter());

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
