package com.hod;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/UpdateApplicationStatus")
public class UpdateApplicationStatus extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String srNO = request.getParameter("srNo");
		String action=request.getParameter("actionRemark");
		String dateDetails=request.getParameter("dateDetails");
		String statusRmk=request.getParameter("statusRemark");
		HttpSession hs=request.getSession();
		String InstituteName  = request.getParameter("instituteName");
		String indenterName = request.getParameter("indenterName");
		String department = request.getParameter("department");
		String date = request.getParameter("date");
		String discription = request.getParameter("workDescription");
		String materialRequired = request.getParameter("materialRequired");
		String quantity = request.getParameter("quantity");
		String reasonWork = request.getParameter("reasonWork");
		String specificAgency = request.getParameter("specificAgency");
		String indentValue = request.getParameter("indentValue");
		String deliveryRequired = request.getParameter("deliveryRequired");
		String workCompletion = request.getParameter("workCompletion");
		String previousRef = request.getParameter("previousRef");
		String remark = request.getParameter("remark");
		String status="Waiting For Approval";
		int role=3;
		 Integer userId = (Integer) hs.getAttribute("userId");
		try {
			Connection con = DatabaseConnection.getConnection();
			Statement statement = con.createStatement();
			if(action.equals("1")) {
				
				 int instituteId=0;
					ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * from mst_institute_name where institue_name ='"+InstituteName+"'");
						while(captchResultSet.next()) {
							instituteId=captchResultSet.getInt(1);
						}	
				
				String asas="Approved";
				int i = statement.executeUpdate("UPDATE tbl_indent_save_details set status='Approved',hod_status_remark='Approved',hod_approved_date ='"+dateDetails+"' where id="+srNO);
					String message="Application Approved successfully";
					hs.setAttribute("message", message);
					
					int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_hod_application_save_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,hod_status_remark,hod_approved_date)"
							+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+status+"',"+userId+","+role+",'"+asas+"','"+dateDetails+"')");
					if(addCustomer>0) {
						
					}
					
					int insert = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_project_department_application_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,institute_id,hod_status_remark,hod_approved_date)"
							+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+status+"',"+userId+","+role+","+instituteId+",'"+asas+"','"+dateDetails+"')");
					if(insert>0) {
						
					}
					response.sendRedirect("ViewIndentForHOD.jsp");
				
			}else {
				String asas="Approved";

				int i = statement.executeUpdate("UPDATE tbl_indent_save_details set status='Waiting', hod_status_remark='"+statusRmk+"',hod_approved_date ='"+dateDetails+"' where id="+srNO);
					String message="Application Rejected successfully";
					
					int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_hod_application_save_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,hod_status_remark,hod_approved_date)"
							+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+asas+"',"+userId+","+role+",'"+statusRmk+"','"+dateDetails+"')");
					if(addCustomer>0) {
						
					}
					hs.setAttribute("message", message);
					response.sendRedirect("ViewIndentForHOD.jsp");
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
}
