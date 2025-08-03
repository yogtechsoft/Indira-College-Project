package com.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/SaveContractorDetails")
public class SaveContractorDetails extends  HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession hs=request.getSession();
		try {
			
			//fetch customer name from customer table
			String contractorName = request.getParameter("contractorName");
			String mobileNo = request.getParameter("mobileNo");
			String address = request.getParameter("address");

			
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into tblcontractordetails(contractorName,mobileNo,address)values('" + contractorName
							+ "','" + mobileNo + "','"+address+"')");
			if (addCustomer > 0) {
				String message="Contractor Added successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("ContractorRegister.jsp");
			} else {
				String message="Contractor Not Added successfully";
				hs.setAttribute("message", message);
				response.sendRedirect("ContractorRegister.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
