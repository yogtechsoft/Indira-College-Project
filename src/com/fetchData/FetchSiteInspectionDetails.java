package com.fetchData;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.ConstructionSiteModel;
import com.model.ShowConstructionSiteInspectionDetailsModel;

@WebServlet("/FetchSiteInspectionDetails")
public class FetchSiteInspectionDetails extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String contractorId = request.getParameter("fetchContractorName");
			String workTitleDetails=request.getParameter("contractorWork");
			List<ShowConstructionSiteInspectionDetailsModel> getDetails=new ArrayList<ShowConstructionSiteInspectionDetailsModel>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery
			("SELECT siteWorkDetails,campus,building,floor,startDate,endDate,modifiedBy,todayDate,dailyTaskDetails "
			+ "FROM tblsiteinspectiondetails WHERE contractorId="+contractorId+" and siteWorkDetails='"+workTitleDetails+"'");
			
			while (captchResultSet.next()) {
				ShowConstructionSiteInspectionDetailsModel st=new ShowConstructionSiteInspectionDetailsModel();
				st.setSiteWorkDetails(captchResultSet.getString(1));
				st.setCampus(captchResultSet.getString(2));
				st.setBuilding(captchResultSet.getString(3));
				st.setFloor(captchResultSet.getString(4));
				st.setStartDate(captchResultSet.getString(5));
				st.setEndDate(captchResultSet.getString(6));
				st.setModifiedBy(captchResultSet.getString(7));
				st.setTodayDate(captchResultSet.getString(8));
				st.setDailyTaskDetails(captchResultSet.getString(9));
				getDetails.add(st);
				
			}
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(getDetails);
			out.print(json);
			out.flush();
	        out.close();
	       // response.getWriter();
			//new Gson().toJson(getDetails, response.getWriter());

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
