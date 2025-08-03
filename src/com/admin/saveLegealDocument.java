package com.admin;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.connection.DatabaseConnection;

@WebServlet("/saveLegealDocument")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) 
public class saveLegealDocument extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String contractorId = request.getParameter("customerName");
		String sansactionPlan = request.getParameter("sactionPlan");
		 String filename = request.getParameter("filename");
	        Part filePart = request.getPart("pdf");  // <input type="file" name="pdf">
	        InputStream inputStream = filePart.getInputStream();
		HttpSession hs=request.getSession();
		try {
			
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into `tbl_advocate_details`(file_name,file_sansaction_plan)"
					+ "values('" + sansactionPlan+ "','" + inputStream + "')");
			if (addCustomer > 0) {
				String message="Data Added successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("AccountInformationDetails.jsp");
			} else {
				String message="fail";
				hs.setAttribute("message", message);
				response.sendRedirect("AccountInformationDetails.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
