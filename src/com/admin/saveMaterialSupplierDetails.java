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

@WebServlet("/saveMaterialSupplierDetails")
public class saveMaterialSupplierDetails extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession hs=request.getSession();
		try {
			
			//fetch customer name from customer table
			String supplierName = request.getParameter("supplierName");
			String materialName = request.getParameter("materialName");
			
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into tblsuppliermaterialdetails(supplierName,materialName)values('" + supplierName
							+ "','" + materialName + "')");
			if (addCustomer > 0) {
				String message="Supplier Added successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("AddMaterialDetails.jsp");
			} else {
				String message="Supplier Not Added successfully";
				hs.setAttribute("message", message);
				response.sendRedirect("AddMaterialDetails.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
