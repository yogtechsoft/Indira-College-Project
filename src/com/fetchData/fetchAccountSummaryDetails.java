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
import com.model.AccountSummaryDetails;
import com.model.ConstructionDetailsModel;

@WebServlet("/fetchAccountSummaryDetails")
public class fetchAccountSummaryDetails extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String contractorId = request.getParameter("customerName");
			String workDetailsName = request.getParameter("contractorWork");
			List<AccountSummaryDetails> getDetails=new ArrayList<AccountSummaryDetails>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery
			("SELECT contractorName,campus,building,floor,workDetails,startDate,endDate,totalamount,advancepayment,date FROM `tbl_account_details` WHERE `contractorid`="+contractorId+" and workDetails='"+workDetailsName+"'");
			while (captchResultSet.next()) {
				AccountSummaryDetails am=new AccountSummaryDetails();
				am.setContractorName(captchResultSet.getString(1));
				am.setCampus(captchResultSet.getString(2));
				am.setBuilding(captchResultSet.getString(3));
				am.setFloor(captchResultSet.getString(4));
				am.setWorkdetails(captchResultSet.getString(5));
				am.setStartdate(captchResultSet.getString(6));
				am.setEnddate(captchResultSet.getString(7));
				am.setTotalamount(captchResultSet.getString(8));
				am.setAdvancepayment(captchResultSet.getString(9));
				am.setDate(captchResultSet.getString(10));
				getDetails.add(am);
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
