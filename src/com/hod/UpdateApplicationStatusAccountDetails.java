package com.hod;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/UpdateApplicationStatusAccountDetails")
public class UpdateApplicationStatusAccountDetails extends HttpServlet {
	
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
		String budgetProvisionYear = request.getParameter("budgetProvisionYear");
		String balanceProvision = request.getParameter("balanceProvision");
		String provisionAmount = request.getParameter("provisionAmount");
		String expensesAmount = request.getParameter("expensesAmount");
		String balanceProposed = request.getParameter("balanceProposed");
		String expensesTillDate = request.getParameter("expensesTillDate");
		String budgetId = request.getParameter("budgetId");
		
		String status="Approved";
		int role=5;
		String waiting="Waiting for Approval";
		String fileName="";
		Blob fileData = null;
		int nextlevelapplicationroleId=6;
		String asas="Approved";
		int getBudgetId=0;
		String budgetName=""; 
		String year="";
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
				
						ResultSet data = DatabaseConnection.getResultFromSqlQuery("SELECT fileName,fileData from tbl_account_department_application_received_details where id="+srNO);
						while(data.next()) {
							fileName=data.getString(1);
							fileData=data.getBlob(2);
						}
						
						ResultSet fetchBudgetId = DatabaseConnection.getResultFromSqlQuery("SELECT id,budget_type from mst_budget_type where id ="+budgetId+"");
						while(fetchBudgetId.next()) {
							getBudgetId=fetchBudgetId.getInt(1);
							budgetName=fetchBudgetId.getString(2);
						}	
						
						if(budgetProvisionYear.equals("1")) {
							year="2025-26";
						}
						
				if(budgetId.equals("2") || budgetId.equals("5")) {
					int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_account_department_application_save_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,hod_status_remark,hod_approved_date,provision_year,balance_provision,balance_provision_amount,proposed_expenses_amount,balance_after_proposed_amount,expenses_till_date,budget_type,budget_id)"
						+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+status+"',"+userId+","+role+",'"+asas+"','"+dateDetails+"','"+year+"','"+balanceProvision+"','"+provisionAmount+"','"+expensesAmount+"','"+balanceProposed+"','"+expensesTillDate+"','"+budgetName+"',"+budgetId+")");
					if(addCustomer>0) {
						
					}
					nextlevelapplicationroleId=8;
					int value = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_cp_department_application_received_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,hod_status_remark,hod_approved_date,filename,filedata,provision_year,balance_provision,balance_provision_amount,proposed_expenses_amount,balance_after_proposed_amount,expenses_till_date,budget_type,budget_id)"
							+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+waiting+"',"+userId+","+nextlevelapplicationroleId+",'"+""+"','"+""+"','"+fileName+"','"+fileData+"','"+year+"','"+balanceProvision+"','"+provisionAmount+"','"+expensesAmount+"','"+balanceProposed+"','"+expensesTillDate+"','"+budgetName+"',"+budgetId+")");
					if(value>0) {
						
					}
				}else {
					int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_account_department_application_save_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,hod_status_remark,hod_approved_date,provision_year,balance_provision,balance_provision_amount,proposed_expenses_amount,balance_after_proposed_amount,expenses_till_date,budget_type,budget_id)"
						+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+status+"',"+userId+","+role+",'"+asas+"','"+dateDetails+"','"+year+"','"+balanceProvision+"','"+provisionAmount+"','"+expensesAmount+"','"+balanceProposed+"','"+expensesTillDate+"','"+budgetName+"',"+budgetId+")");
					if(addCustomer>0) {
						
					}
					
					int value = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into `tbl_purchase_department_application_received_details`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,hod_status_remark,hod_approved_date,filename,filedata,provision_year,balance_provision,balance_provision_amount,proposed_expenses_amount,balance_after_proposed_amount,expenses_till_date,budget_type,budget_id)"
							+ "values('" +InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+waiting+"',"+userId+","+nextlevelapplicationroleId+",'"+""+"','"+""+"','"+fileName+"','"+fileData+"','"+year+"','"+balanceProvision+"','"+provisionAmount+"','"+expensesAmount+"','"+balanceProposed+"','"+expensesTillDate+"','"+budgetName+"',"+budgetId+")");
					if(value>0) {
						
					}
				}
				
				int i = statement.executeUpdate("UPDATE tbl_account_department_application_received_details set status='Approved',hod_status_remark='Approved',hod_approved_date ='"+dateDetails+"' where id="+srNO);
					String message="Application Approved successfully";
					hs.setAttribute("message", message);
					
					response.sendRedirect("AccountDepartmentApplicationDetails.jsp");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
