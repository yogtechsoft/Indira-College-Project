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
import com.model.ContractorCustomerModel;
import com.model.ContractorDetailsModel;

@WebServlet("/contractorWorkingDetails")
public class contractorWorkingDetails extends  HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String customerId = request.getParameter("supplierNameRecord");
			String custId=request.getParameter("customerName");
			List<ContractorDetailsModel> getDetails=new ArrayList<ContractorDetailsModel>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * FROM `tbl_save_labour_contractor_details` WHERE contractor_name='"+customerId+"' AND customerId="+custId);
			while (captchResultSet.next()) {
				ContractorDetailsModel st=new ContractorDetailsModel();
				st.setContractorName(captchResultSet.getString("contractor_name"));
				st.setWorkType(captchResultSet.getString("work_name"));
				st.setArea(captchResultSet.getString("area"));
				st.setRate(captchResultSet.getString("rate"));
				st.setDescription(captchResultSet.getString("description"));
				st.setDate(captchResultSet.getString("date"));
				st.setTotalAmount(captchResultSet.getString("total_amount"));
				
				ResultSet fetchSupplierDetails = DatabaseConnection.getResultFromSqlQuery
						("SELECT SUM(total_amount) FROM `tbl_save_labour_contractor_details` WHERE contractor_name='"+customerId+"' AND customerId="+custId);
				if(fetchSupplierDetails.next()) {
					st.setSumTotalAmount(fetchSupplierDetails.getString(1));
				}
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

