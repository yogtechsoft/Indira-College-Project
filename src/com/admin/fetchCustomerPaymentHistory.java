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
import com.model.CustomerPaymentHistoryModel;
import com.model.SupplierMaterialDetails;

@WebServlet("/fetchCustomerPaymentHistory")
public class fetchCustomerPaymentHistory extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String customerName = request.getParameter("customerName");
			List<CustomerPaymentHistoryModel> getDetails=new ArrayList<CustomerPaymentHistoryModel>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * FROM  tbl_customer_payment_details WHERE customerId="+customerName);
			while(captchResultSet.next()) {
				CustomerPaymentHistoryModel details=new CustomerPaymentHistoryModel();
				details.setCustomerNameDetails(captchResultSet.getString("customerName"));
				details.setSiteAddressDetails(captchResultSet.getString("siteAddress"));
				details.setDescriptionDetails(captchResultSet.getString("description"));
				details.setOwnerNoMobileDetails(captchResultSet.getString("ownerMobileNo"));
				details.setAreaDetails(captchResultSet.getString("area"));
				details.setRateDetails(captchResultSet.getString("rate"));
				details.setAmountToBePaid(captchResultSet.getString("enterPaidAmount"));
				details.setDateDetails(captchResultSet.getString("date"));
				
				ResultSet totalPaymentDetails = DatabaseConnection.getResultFromSqlQuery("SELECT SUM(enterPaidAmount) FROM tbl_customer_payment_details WHERE customerId="+customerName);
				if(totalPaymentDetails.next()) {
					details.setTotalSumAmount(totalPaymentDetails.getString(1));
				}
				getDetails.add(details);
			}
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(getDetails);
			out.print(json);
			out.flush();
	        out.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
