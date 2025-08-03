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
import com.model.ConstuctionInspectionModel;

@WebServlet("/fetchMaintainceWorkInspection")
public class fetchMaintainceWorkInspection extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String customerSetails = request.getParameter("customerName");
			String contractorWorkDetails=request.getParameter("contractorWork");
			List<ConstuctionInspectionModel> getDetails=new ArrayList<ConstuctionInspectionModel>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery
			("SELECT campus,building,floor FROM tblmaintanceconstructiondetails WHERE workDetails='"+contractorWorkDetails+"' and contractorId="+customerSetails);
			while (captchResultSet.next()) {
				ConstuctionInspectionModel st=new ConstuctionInspectionModel();
				st.setMainainceCampus(captchResultSet.getString(1));
				st.setMaintainceBuilding(captchResultSet.getString(2));
				st.setMaintainceFloor(captchResultSet.getString(3));
				getDetails.add(st);
				
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
