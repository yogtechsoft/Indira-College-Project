package com.hod;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.connection.DatabaseConnection;

@WebServlet("/saveIndentProjectDetails")
public class saveIndentProjectDetails extends  HttpServlet {
	//private final String UPLOAD_DIRECTORY = "/home/jarandes/upload/";
	private final String UPLOAD_DIRECTORY = "D:\\product\\document";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String srNO = "";
		String action=request.getParameter("actionRemark");
		String dateDetails=request.getParameter("dateDetails");
		String statusRmk=request.getParameter("statusRemark");
		HttpSession hs=request.getSession();
		String InstituteName  ="";
		String indenterName = "";
		String department = "";
		String date ="";
		String discription = "";
		String materialRequired = "";
		String quantity = "";
		String reasonWork = "";
		String specificAgency = "";
		String indentValue = "";
		String deliveryRequired = "";
		String workCompletion = "";
		String previousRef = "";
		String remark = "";
		String actionRemark = "";
		String hodApprovedDate="";

		String roleName="";
		String instituteName="";
		String password="";
		String finaName="";
		String is_active="";
		int role=4;
		String Hod_status="";
		String imageName="";
		String productName="";
		
		try {

			if (ServletFileUpload.isMultipartContent(request)) {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : multiparts) {
					
					if (!item.isFormField()) {
						imageName = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + imageName));
						FileItem srNo = (FileItem) multiparts.get(0);
						srNO = srNo.getString();
						
						FileItem instiName = (FileItem) multiparts.get(1);
						InstituteName = instiName.getString();
						
						FileItem indentor = (FileItem) multiparts.get(2);
						indenterName = indentor.getString();
						
						FileItem department1 = (FileItem) multiparts.get(3);
						department = department1.getString();
						
						FileItem date1 = (FileItem) multiparts.get(4);
						date = date1.getString();
						
						FileItem work_description = (FileItem) multiparts.get(5);
						discription = work_description.getString();

						FileItem material_Required = (FileItem) multiparts.get(6);
						materialRequired = material_Required.getString();

						FileItem qt = (FileItem) multiparts.get(7);
						quantity = qt.getString();
						
						FileItem locationReason = (FileItem) multiparts.get(8);
						reasonWork = locationReason.getString();
						
						FileItem specific_agncy = (FileItem) multiparts.get(9);
						specificAgency = specific_agncy.getString();
						
						FileItem estimatedIndentValue = (FileItem) multiparts.get(10);
						indentValue = estimatedIndentValue.getString();
						
						FileItem deliveryRequireedBY = (FileItem) multiparts.get(11);
						deliveryRequired = deliveryRequireedBY.getString();

						FileItem workCompletd = (FileItem) multiparts.get(12);
						workCompletion = workCompletd.getString();
						
						FileItem previous = (FileItem) multiparts.get(13);
						previousRef = previous.getString();
						
						FileItem otherRemark = (FileItem) multiparts.get(14);
						remark = otherRemark.getString();
						
						FileItem actionRMk = (FileItem) multiparts.get(15);
						actionRemark = actionRMk.getString();
						
						FileItem datee = (FileItem) multiparts.get(17);
						hodApprovedDate = datee.getString();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String imagePath = UPLOAD_DIRECTORY + imageName;

		 Integer userId = (Integer) hs.getAttribute("userId");

		try {
			
			Connection con = DatabaseConnection.getConnection();
			Statement statement = con.createStatement();
			
				int asad=0;
				ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * from mst_institute_name where institue_name ='"+InstituteName+"'");
					while(captchResultSet.next()) {
						asad=captchResultSet.getInt(1);
					}	
					
					String status="Waiting For Approval";
					String app="";
					if(actionRemark.equals("1")) {
						app="Approved";
					}
					
					int i = statement.executeUpdate("UPDATE tbl_project_department_application_details set status='Approved',hod_status_remark='Approved',hod_approved_date ='"+hodApprovedDate+"' where id="+srNO);
					
					
					int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into `tbl_save_project_dept_application`(institute_name,indenter_name,department,date,work_descrption,material_required,qunatitiy,location_reason_work,specific_agency,estimated_indent_value,delivery_requred,workCompletion,previous_indent,other_remark,status,user_id,role_id,institute_id,hod_status_remark,hod_approved_date,name,file_data)"
					+ "values('" + InstituteName+ "','" + indenterName + "','" + department + "','" + date + "','" + discription + "','" + materialRequired + "','"+ quantity + "','" + reasonWork + "','" + specificAgency + "','" + indentValue + "','" + deliveryRequired + "','"+ workCompletion + "','" + previousRef +"','"+ remark +"','"+status+"',"+userId+","+role+","+asad+",'"+app+"','"+hodApprovedDate+"','"+imagePath+"','"+imagePath+"')");
			if (addCustomer > 0) {
				
				String message="Data Added successfully.";
				hs.setAttribute("message", message);
				response.sendRedirect("IndentDetailsProject.jsp");
			} else {
				String message="fail";
				hs.setAttribute("message", message);
				response.sendRedirect("IndentDetailsProject.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
