package com.mainance.details;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/SaveMaintainceWorkDetails")
public class SaveMaintainceWorkDetails extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String contractorName  = request.getParameter("fetchContractorName");
		String mobileNo  = request.getParameter("mobileNo");
		String address  = request.getParameter("address");
		String constructionWorkType  = request.getParameter("constructionWorkType");
		String yearExpereince  = request.getParameter("yearExperience");
		String gstNo  = request.getParameter("gstNo");
		String totalCompletedProject  = request.getParameter("totalCompletedProject");
		String campusName  = request.getParameter("campusName");
		String buildingArea  = request.getParameter("buildingArea");
		String floor  = request.getParameter("floor");
		String workDetails  = request.getParameter("workDetails");
		String startDate  = request.getParameter("startDate");
		String endDate  = request.getParameter("endDate");
		String totalCost  = request.getParameter("totalCost");
		String flag="N";
		HttpSession hs=request.getSession();
		String totalCOmpletedWork="0";
		String totalPendingWork="100";
		
		
		try {
			//fetch customer name from customer table
			String contName="";
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("select contractorName from tblmaintancecontractorname where id="+contractorName);
				while(captchResultSet.next()) {
					 contName=captchResultSet.getString(1);
				}
		
			int saveConstruction = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into  tblmaintanceconstructiondetails(ContractorName,mobileNo,address,ConstructionWorkType,"
					+ "YearOfExperience,gstNo,completedProject,campus,building,floor,workDetails,startDate,endDate,totalCost,isWorkCompleted,totalWorkComplete,totalPendingWork,contractorId)"
					+ "values('" + contName
							+ "','" + mobileNo + "','" + address + "','" + constructionWorkType + "','" + yearExpereince + "','" 
					+ gstNo + "', '"+totalCompletedProject+"', '"+campusName+"', '"+buildingArea+"','"+floor+"','"+workDetails+"'"
							+ ",'"+startDate+"','"+endDate+"','"+totalCost+"','"+flag+"','"+totalCOmpletedWork+"','"+totalPendingWork+"',"+contractorName+")");
			if (saveConstruction > 0) {
				String message="Record Added successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("MaitainceContractorDetails.jsp");
			} else {
				String message="Some Problem Occurs!";
				hs.setAttribute("message", message);
				response.sendRedirect("MaitainceContractorDetails.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
