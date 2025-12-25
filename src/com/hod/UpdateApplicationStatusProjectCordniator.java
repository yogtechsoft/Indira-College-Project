package com.hod;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/UpdateApplicationStatusProjectCordniator")
public class UpdateApplicationStatusProjectCordniator extends HttpServlet {
	
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
		String status="Approved";
		int role=4;
		String waiting="Waiting for Approval";
		String fileName="";
		Blob fileData = null;
		int nextlevelapplicationroleId=4;
		 Integer userId = (Integer) hs.getAttribute("userId");
		 LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

	        String dateAndTime = now.format(formatter);
		try {
			Connection con = DatabaseConnection.getConnection();
			Statement statement = con.createStatement();
			if(action.equals("1")) {
				
				 int instituteId=0;
					ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * from mst_institute_name where institue_name ='"+InstituteName+"'");
						while(captchResultSet.next()) {
							instituteId=captchResultSet.getInt(1);
						}	
				
						ResultSet data = DatabaseConnection.getResultFromSqlQuery("SELECT name,file_data from tbl_project_cordniator_application_received_details where id="+srNO);
						while(data.next()) {
							fileName=data.getString(1);
							fileData=data.getBlob(2);
						}	
						
						
				String asas="Approved";
				int i = statement.executeUpdate("UPDATE tbl_project_cordniator_application_received_details set status='Approved',hod_status_remark='Approved',hod_approved_date ='"+dateDetails+"' where id="+srNO);
					String message="Application Approved successfully";
					hs.setAttribute("message", message);
					
					int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_project_cordniator_application_save_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,hod_status_remark,hod_approved_date)"
							+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + dateAndTime + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+status+"',"+userId+","+role+",'"+asas+"','"+dateDetails+"')");
					if(addCustomer>0) {
						
					}
					
					int value = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_director_department_application_received_dewtails`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,hod_status_remark,hod_approved_date,name,file_data)"
							+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+waiting+"',"+userId+","+nextlevelapplicationroleId+",'"+""+"','"+""+"','"+fileName+"','"+fileData+"')");
					if(value>0) {
						
					}
					int statusRemark = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_project_cordniator_application_status_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,institute_id,hod_status_remark,hod_approved_date,name,file_data)"
							+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + dateAndTime + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+"Approved"+"',"+userId+","+nextlevelapplicationroleId+","+instituteId+",'"+statusRmk+"','"+dateDetails+"','"+fileName+"','"+fileData+"')");
					if(statusRemark>0) {
						
					}
					response.sendRedirect("ProjectCoordnitaorIntendReport.jsp");
				
			}else {
				
				 int instituteId=0;
					ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * from mst_institute_name where institue_name ='"+InstituteName+"'");
						while(captchResultSet.next()) {
							instituteId=captchResultSet.getInt(1);
						}	
				
						ResultSet data = DatabaseConnection.getResultFromSqlQuery("SELECT name,file_data from tbl_project_cordniator_application_received_details where id="+srNO);
						while(data.next()) {
							fileName=data.getString(1);
							fileData=data.getBlob(2);
						}
						
						int i = statement.executeUpdate("UPDATE tbl_project_cordniator_application_received_details set status='Approved',hod_status_remark='Approved',hod_approved_date ='"+dateDetails+"' where id="+srNO);
						String message="Application Rejected successfully";
						hs.setAttribute("message", message);
					
						int value = DatabaseConnection.insertUpdateFromSqlQuery(
								"insert into `tbl_project_cordniator_application_status_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,institute_id,hod_status_remark,hod_approved_date,name,file_data)"
								+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+"Rejected"+"',"+userId+","+nextlevelapplicationroleId+","+instituteId+",'"+statusRmk+"','"+dateDetails+"','"+fileName+"','"+fileData+"')");
						if(value>0) {
							
						}
						response.sendRedirect("ProjectCoordnitaorIntendReport.jsp");	
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
