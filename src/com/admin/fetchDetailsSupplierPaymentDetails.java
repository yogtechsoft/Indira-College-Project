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
import com.model.fetchSupplierPaymentModel;

@WebServlet("/fetchDetailsSupplierPaymentDetails")
public class fetchDetailsSupplierPaymentDetails extends  HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String customerId = request.getParameter("customerName");
			List<fetchSupplierPaymentModel> getDetails=new ArrayList<fetchSupplierPaymentModel>();
			String custName=null;
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * FROM tblsupplier_payment_details WHERE SupplierName='"+customerId+"'");
			while (captchResultSet.next()) {
				fetchSupplierPaymentModel details=new fetchSupplierPaymentModel();
				details.setSupplierName(captchResultSet.getString(2));
				details.setTotalQuantity(captchResultSet.getString(3));
				details.setAmountoBePaid(captchResultSet.getString(5));
				details.setDate(captchResultSet.getString(6));
				
				
				ResultSet totalPaymentDetails = DatabaseConnection.getResultFromSqlQuery("SELECT SUM(AmountToPePaid) FROM tblsupplier_payment_details WHERE SupplierName='"+customerId+"'");
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
