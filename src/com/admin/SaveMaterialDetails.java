package com.admin;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/SaveMaterialDetails")
public class SaveMaterialDetails extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customerId = request.getParameter("customerName");
		String supplierName = request.getParameter("supplierName");
		String materialName = request.getParameter("materialName");
		String quantity = request.getParameter("Quantity");
		String rate = request.getParameter("Rate");
		String transportCharge = request.getParameter("transportCharge");
		String totalAmount = request.getParameter("totalAmount");
		String date = request.getParameter("materialDate");
		String custname=null;
		
		
		HttpSession hs=request.getSession();
		try {
			
			//fetch customer name from customer table
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("select * from tbl_customer_register where id="+customerId);
			while (captchResultSet.next()) {
				custname = captchResultSet.getString(2);
			}		
		
			
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into tblmaterialdetails(supplierName,materialName,Quantity,Rate,totalAmount,date,customerId,customerName,transportCharges)values('" + supplierName
							+ "','" + materialName + "','" + quantity + "','" + rate + "','" + totalAmount + "','" + date + "', '"+customerId+"', '"+custname+"', '"+transportCharge+"')");
			if (addCustomer > 0) {
				String message="Record Added successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("AddMaterialDetails.jsp");
			} else {
				String message="Record Added successfully";
				hs.setAttribute("message", message);
				response.sendRedirect("AddMaterialDetails.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
