package com.admin;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.connection.DatabaseConnection;

@WebServlet("/SaveCustomerDetails")
public class SaveCustomerDetails extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		int id = rnd.nextInt(9000) + 10000;
		System.out.println("Customer Id  "+id);
		while (salt.length() < 3) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String code = salt.toString();
		String pname = request.getParameter("pname");
		String siteAddress = request.getParameter("address");
		String description = request.getParameter("description");
		String mbileNo = request.getParameter("mobileNo");
		String totalArea = request.getParameter("totalArea");
		String totalRate = request.getParameter("totalRate");
		String totalAmount = request.getParameter("totalAmount");
		String date = request.getParameter("date");
		HttpSession hs=request.getSession();
		try {
			

			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into tbl_customer_register(customerName,siteAddress,siteDescription,ownerMobile,Area,Rate,totalAmount,date)values('" + pname
							+ "','" + siteAddress + "','" + description + "','" + mbileNo + "','" + totalArea + "','" + totalRate + "','"
							+ totalAmount + "','" + date + "')");
			if (addCustomer > 0) {
				String message="Customer register successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("admin-all-orders.jsp");
			} else {
				String message="Customer registration fail";
				hs.setAttribute("message", message);
				response.sendRedirect("admin-all-orders.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
