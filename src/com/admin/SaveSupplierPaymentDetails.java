package com.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/SaveSupplierPaymentDetails")
public class SaveSupplierPaymentDetails extends  HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String supplierNameDetails=request.getParameter("supplierNameDetails");
		String totalQuantity=request.getParameter("totalQuantity");
		String totalAmount=request.getParameter("totalAmount");
		String paidAmount=request.getParameter("paidAmount");
		String date=request.getParameter("date");
		HttpSession hs=request.getSession();

		try {
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into tblsupplier_payment_details(SupplierName,TotalQuantity,totalAmount,AmountToPePaid,date)values('" + supplierNameDetails+ "','" + totalQuantity + "','"+totalAmount+"','"+paidAmount+"','"+date+"')");
			if (addCustomer > 0) {
				String message="Addedd Successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("SupplierPaymentDetails.jsp");
			} else {
				String message="There Some Problem";
				hs.setAttribute("message", message);
				response.sendRedirect("SupplierPaymentDetails.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
