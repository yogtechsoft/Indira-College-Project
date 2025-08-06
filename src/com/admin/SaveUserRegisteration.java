package com.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/SaveUserRegisteration")
public class SaveUserRegisteration extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String firstName  = request.getParameter("firstName");
		String midleName = request.getParameter("midleName");
		String lastName = request.getParameter("lastName");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String InstituteName = request.getParameter("InstituteName");
		String roleMaster = request.getParameter("roleMaster");
		String workExperience = request.getParameter("workExperience");
		String emailId = request.getParameter("emailId");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String pinCode = request.getParameter("pinCode");
		String date = request.getParameter("date");
		HttpSession hs=request.getSession();
		String roleName="";
		String instituteName="";
		String password="";
		String finaName="";
		String is_active="";
		try {
			
			if(InstituteName.equals("20")) {
				
				instituteName="Other";
				
				
			}else {
				 instituteName="";
				ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * from mst_institute_name where id ="+InstituteName);
					while(captchResultSet.next()) {
						instituteName=captchResultSet.getString(2);
					}	
						
			}
			
			 roleName="";
				ResultSet role = DatabaseConnection.getResultFromSqlQuery("SELECT * from mst_role_master where id ="+roleMaster);
					while(role.next()) {
						roleName=role.getString(2);
					}
					
			
			 password="Test@123";
				
			 finaName = firstName + " " + midleName + " " + lastName;
			
			 is_active="Y";
			
					
					
		
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into `tbl_user_register_details`(first_name,middle_name,last_name,date_of_birth,insitiute_name,role,work_experience,emailId,address,city,pincode,institute_id,role_id,password,date)"
					+ "values('" + firstName+ "','" + midleName + "','" + lastName + "','" + dateOfBirth + "','" + instituteName + "','" + roleName + "','"+ workExperience + "','" + emailId + "','" + address + "','" + city + "','" + pinCode + "'," + InstituteName + "," + roleMaster +",'" + password +"','"+date+"')");
			if (addCustomer > 0) {
				int addAdminLoginDetails = DatabaseConnection.insertUpdateFromSqlQuery(
						"insert into  tbladmin(added_date,email,password,name,role,is_Active)"
						+ "values('" +date+ "','" + emailId + "','" + password +"','"+finaName+"','"+roleMaster+"','"+is_active+"')");
				if (addAdminLoginDetails > 0) {
					String str="Registration Successfully!";
				}
				String message="Data Added successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("RegisterUser.jsp");
			} else {
				String message="fail";
				hs.setAttribute("message", message);
				response.sendRedirect("RegisterUser.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
