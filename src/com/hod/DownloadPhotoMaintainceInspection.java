package com.hod;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DatabaseConnection;

@WebServlet("/DownloadPhotoMaintainceInspection")
public class DownloadPhotoMaintainceInspection extends HttpServlet{
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileId = request.getParameter("contractorId");
        String contractorWork = request.getParameter("siteWorkDetails");
        String id=request.getParameter("id");
       
        try {
           
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT file_name,file_data FROM `tblmaintaincesiteinspect` where contractorId="+fileId+ " and siteWorkDetails='"+contractorWork+"' and id="+id+" ");

			if (captchResultSet.next()) {
				String fileName = captchResultSet.getString("file_name");
	            Blob blob = captchResultSet.getBlob("file_data");
	            
		            File file = new File(fileName);  //for UAT
					//File file = new File("/home/yogtechs/upload/INVOICE DETAILS_"+customerName+".pdf"); //for PROD
					FileInputStream fileIn = new FileInputStream(file);
					ServletOutputStream out11 = response.getOutputStream();

					byte[] outputByte = new byte[4096];
					//copy binary contect to output stream
					while(fileIn.read(outputByte, 0, 4096) != -1)
					{
						out11.write(outputByte, 0, 4096);
					}
					
					StringBuffer sb = new StringBuffer("whatever string you like");
					InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

					byte[] outputByte1 = new byte[4096];
					//copy binary contect to output stream
					while(in.read(outputByte1, 0, 4096) != -1)
					{
						out11.write(outputByte1, 0, 4096);
					}
					fileIn.close();
					out11.flush();
					out11.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
