package com.fetchData;

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

import com.connection.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.WorkProgressModel;

@WebServlet("/FetchConstructionWorkDetails")
public class FetchConstructionWorkDetails extends  HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String contractorDetails = request.getParameter("customerName");
			String work = request.getParameter("contractorWork");

			
			List<WorkProgressModel> ed=new ArrayList<WorkProgressModel>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery
("SELECT lp.totalWorkComplete,lp.totalPendingWork FROM tblnewconstructionworkdetails lp INNER JOIN tblcontractordetails ss on ss.id=lp.contractorId where contractorId="+contractorDetails+" and workDetails='"+work+"' group by ss.contractorName");
			while (captchResultSet.next()) {
				WorkProgressModel sd=new WorkProgressModel();
				sd.setGrpahtotalWorkCompleted(captchResultSet.getString(1));
				sd.setGrpahtotalWorkPending(captchResultSet.getString(2));
				
				ed.add(sd);

			}
	
			
			//for total amount
			
			
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(ed);
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
