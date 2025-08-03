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
import com.model.SupplierMaterialDetails;

@WebServlet("/fetchMaterialDataAgainstSupplier")
public class fetchMaterialDataAgainstSupplier extends  HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String supplierDetails = request.getParameter("supplierNameRecord");
			String customerId=request.getParameter("customerName");
			List<SupplierMaterialDetails> getDetails=new ArrayList<SupplierMaterialDetails>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * FROM  tblmaterialdetails WHERE supplierName='"+supplierDetails+"' AND customerId="+customerId);
			while(captchResultSet.next()) {
				SupplierMaterialDetails st=new SupplierMaterialDetails();
				st.setSuppliername(captchResultSet.getString(2));
				st.setMaterialName(captchResultSet.getString(3));
				st.setQuantity(captchResultSet.getString(4));
				st.setRate(captchResultSet.getString(5));
				st.setTransportCharge(captchResultSet.getString(10));
				st.setTotalAmount(captchResultSet.getString(6));
				st.setDate(captchResultSet.getString(7));
				
				ResultSet fetchSupplierDetails = DatabaseConnection.getResultFromSqlQuery
						("SELECT SUM(totalAmount) FROM `tblmaterialdetails` WHERE supplierName='"+supplierDetails+"' AND customerId="+customerId);
				if(fetchSupplierDetails.next()) {
					st.setSumTotalAmount(fetchSupplierDetails.getString(1));
				}
				getDetails.add(st);
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
