package com.hod;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.IndentReportDetails;

@WebServlet("/fetchIndentDetailsCPDepartment")
public class fetchIndentDetailsCPDepartment extends  HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String srNO = request.getParameter("srNo");
			HttpSession hs=request.getSession();

			List<IndentReportDetails> getDetails=new ArrayList<IndentReportDetails>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * FROM `tbl_cp_department_application_received_details` where id="+srNO);
			while (captchResultSet.next()) {
				IndentReportDetails st=new IndentReportDetails();
				st.setInstituteName(captchResultSet.getString("institute_name"));
				st.setIndenterName(captchResultSet.getString("indenter_name"));
				st.setDepartment(captchResultSet.getString("department"));
				st.setDate(captchResultSet.getString("date"));
				st.setWorkDescription(captchResultSet.getString("work_descrption"));
				st.setMaterialRequired(captchResultSet.getString("material_required"));
				st.setQuantity(captchResultSet.getString("qunatitiy"));
				st.setReasonWork(captchResultSet.getString("location_reason_work"));
				st.setSpecificAgency(captchResultSet.getString("specific_agency"));
				st.setIndentValue(captchResultSet.getString("estimated_indent_value"));
				st.setDeliveryRequired(captchResultSet.getString("delivery_requred"));
				st.setWorkCompletion(captchResultSet.getString("workCompletion"));
				st.setPreviousRef(captchResultSet.getString("previous_indent"));
				st.setRemark(captchResultSet.getString("other_remark"));
				st.setHodRemark(captchResultSet.getString("hod_status_remark"));
				st.setHodAppreovedDate(captchResultSet.getString("hod_approved_date"));
				hs.setAttribute("rmkStatus", captchResultSet.getString("hod_status_remark"));
				st.setFileName(captchResultSet.getString("filedata"));
				st.setBudgetYear(captchResultSet.getString("provision_year"));
				st.setBalanceProvision(captchResultSet.getString("balance_provision"));
				st.setBalanceProvisionAmount(captchResultSet.getString("balance_provision_amount"));
				st.setProposedExpesnseAmount(captchResultSet.getString("proposed_expenses_amount"));
				st.setBalanceAfterProposedAmount(captchResultSet.getString("balance_after_proposed_amount"));
				st.setExpensesTilDate(captchResultSet.getString("expenses_till_date"));
				st.setBudgetType(captchResultSet.getString("budget_type"));
				getDetails.add(st);
				
			}
			//for total amount
			
			
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(getDetails);
			out.print(json);
			out.flush();
	        out.close();
	       // response.getWriter();
			//new Gson().toJson(getDetails, response.getWriter());

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
