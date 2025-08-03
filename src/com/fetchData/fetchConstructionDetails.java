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
import com.model.ConstructionDetailsModel;
import com.model.ConstructionSiteModel;

@WebServlet("/fetchConstructionDetails")
public class fetchConstructionDetails extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String contractorId = request.getParameter("customerName");
			String workDetailsName = request.getParameter("contractorWork");
			List<ConstructionDetailsModel> getDetails=new ArrayList<ConstructionDetailsModel>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery
			("SELECT campus,building,floor,workDetails,startDate,endDate,totalCost FROM `tblnewconstructionworkdetails` WHERE `contractorId`="+contractorId+" and workDetails='"+workDetailsName+"'");
			while (captchResultSet.next()) {
				ConstructionDetailsModel cm=new ConstructionDetailsModel();
				cm.setCampus(captchResultSet.getString(1));
				cm.setBuilding(captchResultSet.getString(2));
				cm.setFloor(captchResultSet.getString(3));
				cm.setWorkDetails(captchResultSet.getString(4));
				cm.setStartDate(captchResultSet.getString(5));
				cm.setEndDate(captchResultSet.getString(6));
				cm.setTotalCost(captchResultSet.getString(7));
				getDetails.add(cm);			
			}
			//for total amount
			
			
			
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
