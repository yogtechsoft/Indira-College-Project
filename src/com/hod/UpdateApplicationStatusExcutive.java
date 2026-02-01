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

@WebServlet("/UpdateApplicationStatusExcutive")
public class UpdateApplicationStatusExcutive extends HttpServlet {

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
		int role=5;
		int nextlevelId=7;
		String waiting="Waiting for Approval";
		String fileName="";
		Blob fileData = null;
		 LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

	        String dateAndTime = now.format(formatter);
		int nextlevelapplicationroleId=7;
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
				
						ResultSet data = DatabaseConnection.getResultFromSqlQuery("SELECT fileName,fileData from tbl_project_director_excutor_application_details where id="+srNO);
						while(data.next()) {
							fileName=data.getString(1);
							fileData=data.getBlob(2);
						}	
						String roleName="";
						ResultSet roleMaster = DatabaseConnection.getResultFromSqlQuery("SELECT * from mst_role_master where id ="+nextlevelId);
						while(roleMaster.next()) {
							roleName=roleMaster.getString(2);
						}
						
						ResultSet insitutueId = DatabaseConnection.getResultFromSqlQuery("select id from mst_institute_name where institue_name='"+InstituteName+"'");
						int institueid=0;
						while(insitutueId.next()) {
							institueid=insitutueId.getInt(1);
						}
						
						ResultSet indentDetails = DatabaseConnection.getResultFromSqlQuery("select * from tbl_indent_save_details where role_id="+role+" and institute_id="+institueid);
						int IntentPrimaryId=0;
						while(indentDetails.next()) {
							IntentPrimaryId=indentDetails.getInt("id");
						}
						
						String ds="Approved";
						int a = statement.executeUpdate("UPDATE tbl_indent_save_details set status='Approved',hod_status_remark='Approved',hod_approved_date ='"+dateDetails+"',tracking_status='"+roleName+"',role_id="+nextlevelId+" where id="+IntentPrimaryId);
						if(a>0) {
							String ass="Record Updated!";
						}
						
						
						
				String asas="Approved";
				int i = statement.executeUpdate("UPDATE tbl_project_director_excutor_application_details set status='Approved',hod_status_remark='Approved',hod_approved_date ='"+dateDetails+"' where id="+srNO);
					String message="Application Approved successfully";
					hs.setAttribute("message", message);
					
					int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_project_manging_director_executive_application_save_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,hod_status_remark,hod_approved_date,dirctor_remark,approved_date)"
							+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + dateAndTime + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+status+"',"+userId+","+role+",'"+asas+"','"+dateDetails+"','"+statusRmk+"','"+dateAndTime+"')");
					if(addCustomer>0) {
						
					}
					
					int value = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_account_department_application_received_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,hod_status_remark,hod_approved_date,filename,filedata)"
							+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+waiting+"',"+userId+","+nextlevelapplicationroleId+",'"+""+"','"+""+"','"+fileName+"','"+fileData+"')");
					if(value>0) {
						
					}
					response.sendRedirect("ViewIndentReportForProjectExcutive.jsp");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
