package com.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/SaveCustomerPaymentDetails")
public class SaveCustomerPaymentDetails extends  HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String customerNameDetails=request.getParameter("customerNameDetails");
		String siteAddress=request.getParameter("siteAddress");
		String description=request.getParameter("description");
		String mobileNo=request.getParameter("mobileNo");
		String area=request.getParameter("area");
		String rate=request.getParameter("rate");
		String totalAmount=request.getParameter("totalAmount");
		String enterAmount=request.getParameter("enterAmount");
		String date=request.getParameter("date");
		String customerId=request.getParameter("customerName");
		HttpSession hs=request.getSession();

		try {
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into tbl_customer_payment_details(customerName,siteAddress,description,ownerMobileNo,area,rate,totalAmount,enterPaidAmount,date,customerId)"
					+ "values('" + customerNameDetails+ "','" + siteAddress + "','"+description+"','"+mobileNo+"','"+area+"','"+rate+"','"+totalAmount+"','"+enterAmount+"','"+date+"','"+customerId+"')");
			if (addCustomer > 0) {
				String message="Payment Completed Successfully!";
				hs.setAttribute("message", message);
				response.sendRedirect("CustomerPaymentDetails.jsp");
			} else {
				String message="Some Problem !";
				hs.setAttribute("message", message);
				response.sendRedirect("CustomerPaymentDetails.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
