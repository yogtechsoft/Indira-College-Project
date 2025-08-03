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
import com.model.fetchSupplierDetailsModel;

@WebServlet("/fetchSupplierDetailsForPayment")
public class fetchSupplierDetailsForPayment extends  HttpServlet{
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String supplierName = request.getParameter("customerName");
			List<fetchSupplierDetailsModel> getDetails=new ArrayList<fetchSupplierDetailsModel>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT (supplierName),sum(totalAmount),sum(Quantity) FROM `tblmaterialdetails` WHERE supplierName='"+supplierName+"'");
			while(captchResultSet.next()) {
				fetchSupplierDetailsModel st=new fetchSupplierDetailsModel();
				st.setSupplierName(captchResultSet.getString(1));
				st.setQuantity(captchResultSet.getString(3));
				st.setTotalAmount(captchResultSet.getString(2));
				
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
