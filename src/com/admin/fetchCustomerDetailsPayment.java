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
import com.model.CustomerDetailsModel;

@WebServlet("/fetchCustomerDetailsPayment")
public class fetchCustomerDetailsPayment extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String customerId = request.getParameter("customerName");
			List<CustomerDetailsModel> getDetails=new ArrayList<CustomerDetailsModel>();
			String custName=null;
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * FROM tbl_customer_register WHERE id="+customerId);
			while (captchResultSet.next()) {
				CustomerDetailsModel details=new CustomerDetailsModel();
				details.setCustomerName(captchResultSet.getString("customerName"));
				details.setSiteAddress(captchResultSet.getString("siteAddress"));
				details.setDescription(captchResultSet.getString("siteDescription"));
				details.setOwnerNoMobile(captchResultSet.getString("ownerMobile"));
				details.setArea(captchResultSet.getString("Area"));
				details.setRate(captchResultSet.getString("Rate"));
				details.setTotalAmount(captchResultSet.getString("totalAmount"));
				
				// for payment history details
				ResultSet getCustomerName = DatabaseConnection.getResultFromSqlQuery("SELECT * FROM tbl_customer_register WHERE id="+customerId);
				while(getCustomerName.next()) {
					custName=getCustomerName.getString("customerName");
				}
				
				
				ResultSet totalPaymentDetails = DatabaseConnection.getResultFromSqlQuery("SELECT SUM(enterPaidAmount) FROM tbl_customer_payment_details WHERE customerId="+customerId);
					if(totalPaymentDetails.next()) {
						details.setTotalSumAmount(totalPaymentDetails.getString(1));
					}
				getDetails.add(details);
				
			}
			//for total amount
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
