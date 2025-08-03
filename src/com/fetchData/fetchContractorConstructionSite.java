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
import com.model.ContractorBasicDetailsModel;

@WebServlet("/fetchContractorConstructionSite")
public class fetchContractorConstructionSite extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String contractorId = request.getParameter("customerName");
			List<ConstructionSiteModel> getDetails=new ArrayList<ConstructionSiteModel>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery
			("SELECT workDetails FROM `tblnewconstructionworkdetails` WHERE contractorId="+contractorId);
			while (captchResultSet.next()) {
				ConstructionSiteModel st=new ConstructionSiteModel();
				st.setCconstructionSiteDetails(captchResultSet.getString(1));
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
