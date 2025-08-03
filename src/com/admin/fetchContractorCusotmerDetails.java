package com.admin;

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
import com.model.ContractorBasicDetailsModel;
import com.model.ContractorCustomerModel;
import com.model.SupplierMaterialDetails;

@WebServlet("/fetchContractorCusotmerDetails")
public class fetchContractorCusotmerDetails extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String contractorId = request.getParameter("contractorName");
			List<ContractorBasicDetailsModel> getDetails=new ArrayList<ContractorBasicDetailsModel>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("select mobileNo,address from tblcontractordetails where id="+contractorId);
			while (captchResultSet.next()) {
				ContractorBasicDetailsModel st=new ContractorBasicDetailsModel();
				st.setMobileNo(captchResultSet.getString(1));
				st.setAddress(captchResultSet.getString(2));
				
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
