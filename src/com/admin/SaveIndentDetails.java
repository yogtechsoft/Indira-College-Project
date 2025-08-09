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

@WebServlet("/SaveIndentDetails")
public class SaveIndentDetails extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String InstituteName  = request.getParameter("InstituteName");
		String indenterName = request.getParameter("indenterName");
		String department = request.getParameter("department");
		String date = request.getParameter("date");
		String discription = request.getParameter("discription");
		String materialRequired = request.getParameter("materialRequired");
		String quantity = request.getParameter("quantity");
		String reasonWork = request.getParameter("reasonWork");
		String specificAgency = request.getParameter("specificAgency");
		String indentValue = request.getParameter("indentValue");
		String deliveryRequired = request.getParameter("deliveryRequired");
		String workCompletion = request.getParameter("workCompletion");
		String previousRef = request.getParameter("previousRef");
		String remark = request.getParameter("remark");
		HttpSession hs=request.getSession();
		String roleName="";
		String instituteName="";
		String password="";
		String finaName="";
		String is_active="";
		 Integer userId = (Integer) hs.getAttribute("userId");

		try {
			
				 instituteName="";
				ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * from mst_institute_name where id ="+InstituteName);
					while(captchResultSet.next()) {
						instituteName=captchResultSet.getString(2);
					}	
					
					String status="Waiting For Approval";
			
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into `tbl_indent_save_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id)"
					+ "values('" + instituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+status+"',"+userId+")");
			if (addCustomer > 0) {
				
				String message="Data Added successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("CreateIndent.jsp");
			} else {
				String message="fail";
				hs.setAttribute("message", message);
				response.sendRedirect("CreateIndent.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
