package com.fetchData;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.connection.DatabaseConnection;

@WebServlet("/UpdateMaintainceConstruction")
public class UpdateMaintainceConstruction extends  HttpServlet {
	
	//private final String UPLOAD_DIRECTORY = "/home/jarandes/upload/";
		private final String UPLOAD_DIRECTORY = "D:/product/document/";
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("uname");
		try {
			String contractorDetails = "";
			String workDetails="";
			String totalCompletedWork="";
			String totalPendingWork="";
			//fetch work details
			String contractorWork = "";
			String campusName = "";
			String building = "";
			String floor = "";
			String startDate = "";
			String endDate = "";
			String workStatus = "";
			String contractorName="";
			String imageName="";
			HttpSession hs=request.getSession();
			
			try {

				if (ServletFileUpload.isMultipartContent(request)) {
					List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					for (FileItem item : multiparts) {
						
						if (!item.isFormField()) {
							imageName = new File(item.getName()).getName();
							item.write(new File(UPLOAD_DIRECTORY + File.separator + imageName));
							FileItem contractorDetailsName = (FileItem) multiparts.get(0);
							contractorDetails = contractorDetailsName.getString();
							
							FileItem contractorNameDetails = (FileItem) multiparts.get(1);
							contractorWork = contractorNameDetails.getString();
							
							FileItem workDetailsName = (FileItem) multiparts.get(2);
							workDetails = workDetailsName.getString();
							
							FileItem collegeCampus = (FileItem) multiparts.get(3);
							campusName = collegeCampus.getString();
							
							FileItem buildingName = (FileItem) multiparts.get(4);
							building = buildingName.getString();
							
							FileItem floorName = (FileItem) multiparts.get(5);
							floor = floorName.getString();
							
							FileItem startDateName = (FileItem) multiparts.get(6);
							startDate = startDateName.getString();
							
							FileItem endDateName = (FileItem) multiparts.get(7);
							endDate = endDateName.getString();

							FileItem workStatusDaily = (FileItem) multiparts.get(8);
							workStatus = workStatusDaily.getString();
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String imagePath = UPLOAD_DIRECTORY + imageName;

			 Integer userId = (Integer) hs.getAttribute("userId");
			 
			 
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT totalWorkComplete,totalPendingWork FROM tblmaintanceconstructiondetails where workDetails='"+contractorWork+"' and contractorId="+contractorDetails);
			while (captchResultSet.next()) {
				totalCompletedWork=	captchResultSet.getString(1);
				totalPendingWork= captchResultSet.getString(2);
			}
			Date today = new Date(); // Get current date
	        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        String formattedDate = formatter.format(today);
			
			int update = DatabaseConnection.insertUpdateFromSqlQuery
			("update tblmaintanceconstructiondetails set totalWorkComplete='"+workDetails+"' WHERE  workDetails='"+contractorWork+"' and contractorId="+contractorDetails);
			if(update == 1) {
				String str="Record Update Succrssfully";				
				//insert siteInspection Details
				int i = DatabaseConnection.insertUpdateFromSqlQuery("insert into tblmaintaincesiteinspect(siteWorkDetails,campus,building,floor,startDate,endDate,dailyTaskDetails,contractorId,todayDate,modifiedBy,file_name,file_data) "
						+ "values('" + contractorWork+ "','" + campusName + "','"+building+"','" + floor + "','" + startDate + "','"+endDate+"','"+workStatus+"','"+contractorDetails+"','"+formattedDate+"','"+username+"','"+imagePath+"','"+imagePath+"')");
				if (i > 0) {
					String success = "Record Updated successfully.";
					session.setAttribute("message", success);
					response.sendRedirect("UpdateMaintanceWorkDetails.jsp");
				}
				
			}
			
			
			
	       // response.getWriter();
			//new Gson().toJson(getDetails, response.getWriter());

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

