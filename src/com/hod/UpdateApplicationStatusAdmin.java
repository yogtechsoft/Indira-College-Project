package com.hod;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/UpdateApplicationStatusAdmin")
public class UpdateApplicationStatusAdmin extends  HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String srNO = request.getParameter("srNo");
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

		HttpSession hs=request.getSession();

		try {
			Connection con = DatabaseConnection.getConnection();
			Statement statement = con.createStatement();
				int i = statement.executeUpdate("UPDATE tbl_indent_save_details set institute_name='"+InstituteName+"', indenter_name='"+indenterName+"', department='"+department+"', date='"+date+"', work_descrption='"+discription+"', material_required='"+materialRequired+"', qunatitiy='"+quantity+"', location_reason_work='"+reasonWork+"', specific_agency='"+specificAgency+"', estimated_indent_value='"+indentValue+"', delivery_requred='"+deliveryRequired+"', workCompletion='"+workCompletion+"', previous_indent='"+previousRef+"', other_remark='"+remark+"', status='"+status+"' where id="+srNO);
				String message="Application Approved successfully";
				hs.setAttribute("message", message);
				response.sendRedirect("ViewIndentReport.jsp");
				
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
