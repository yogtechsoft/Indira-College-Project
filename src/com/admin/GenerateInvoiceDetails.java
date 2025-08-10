package com.admin;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.connection.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

@WebServlet("/GenerateInvoiceDetails")
public class GenerateInvoiceDetails extends HttpServlet{
	
    private final int ARBITARY_SIZE = 1048;

    
	public static PdfPCell getCell(String text, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    return cell;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String customerId = request.getParameter("srNo");
			Document document = new Document();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT * FROM `tbl_indent_save_details` WHERE id="+customerId);
			String customerName=null;
			String address=null;
			String mobileNo=null;
			String area=null;
			String rate=null;
			String totalAmpunt=null;
			String instituteName="";
			String date="";
			String indenterName="";
			String department="";
			String workDescription="";
			String material_workload="";
			String quantity="";
			String locationRework="";
			String specificAgency="";
			String estimatedIndentValue="";
			String deliveryDate="";
			String work_completion="";
			String previous_if_any="";
			String anyOther="";
			while(captchResultSet.next()) {
				instituteName=captchResultSet.getString("institute_name");
				date=captchResultSet.getString("date");
				indenterName=captchResultSet.getString("indenter_name");
				department=captchResultSet.getString("department");
				workDescription=captchResultSet.getString("work_descrption");
				material_workload=captchResultSet.getString("material_required");
				quantity=captchResultSet.getString("qunatitiy");
				locationRework=captchResultSet.getString("location_reason_work");
				specificAgency=captchResultSet.getString("specific_agency");
				estimatedIndentValue=captchResultSet.getString("estimated_indent_value");
				deliveryDate=captchResultSet.getString("delivery_requred");
				work_completion=captchResultSet.getString("workCompletion");
				previous_if_any=captchResultSet.getString("previous_indent");
				anyOther=captchResultSet.getString("other_remark");


			}

			 PdfWriter.getInstance(document, new FileOutputStream("D:/bkp/INVOICE DETAILS_"+customerName+".pdf"));  //for UAT
			 //PdfWriter.getInstance(document, new FileOutputStream("/home/yogtechs/upload/INVOICE DETAILS_"+customerName+".pdf"));//for Prod


			 document.open();
			 //Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			 //Chunk chunk = new Chunk("Hello World", font);
            Font fontSize_10 =  FontFactory.getFont(FontFactory.TIMES, 18f);
            Font fontSize_12 =  FontFactory.getFont(FontFactory.TIMES, 20f);
            Font fontSize_11 =  FontFactory.getFont(FontFactory.TIMES, 18f);
            Font fontSize_16 =  FontFactory.getFont(FontFactory.COURIER_BOLD, 17f);
            
          //for invoice Generated
			
			Random random = new Random();
			int newRandomCaptcha = random.nextInt(9000) + 10000;

          
            Paragraph invoice = new Paragraph("Shree Chanakya Education Society",fontSize_12);
            invoice.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			 document.add(invoice);

			 Paragraph paragraph01 = new Paragraph("Indira Group Of Institutes",fontSize_10);
	         paragraph01.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			 document.add(paragraph01);
			 
			 Paragraph paragraph1 = new Paragraph("85/5A Tathwade Pune 411 044",fontSize_11);
	         paragraph1.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			 document.add(paragraph1);
			 
			 Paragraph paragraph12 = new Paragraph("Work Indent",fontSize_11);
	         paragraph12.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			 document.add(paragraph12);
	         document.add(Chunk.NEWLINE); // space after title

	         LineSeparator ls = new LineSeparator();
		        document.add(new Chunk(ls));

			 String pattern = "dd-MM-yyyy";
			 String dateInString =new SimpleDateFormat(pattern).format(new Date());
		    PdfPTable table = new PdfPTable(1);
		    table.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.setWidthPercentage(100);
		    table.addCell(("INSTITUTE/COLLEGE : "+instituteName)); 
            // Add table in document
	        document.add(table);
	        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	        PdfPTable srno = new PdfPTable(4);
	        srno.setHorizontalAlignment(Element.ALIGN_CENTER);
	        srno.setWidthPercentage(100);
	        
	        srno.addCell(("Sr No : "+customerId)); 
	        srno.addCell(("Date : "+date)); 
	        srno.addCell(("Indenter : "+indenterName)); 
	        srno.addCell(("Department : "+department)); 
            // Add table in document
	        document.add(srno);
	        
	        PdfPTable work_Description = new PdfPTable(3);
	        work_Description.setHorizontalAlignment(Element.ALIGN_CENTER);
	        work_Description.setWidthPercentage(100);
	        
	        work_Description.addCell(("Work Description : "+workDescription)); 
	        work_Description.addCell(("Material Required (IF KNOW) : "+material_workload)); 
	        work_Description.addCell(("Quantity : "+quantity));
	        
	        document.add(work_Description);

	        PdfPTable location = new PdfPTable(1);
	        location.setHorizontalAlignment(Element.ALIGN_CENTER);
	        location.setWidthPercentage(100);
	        location.addCell(("Location for Reason For Work : "+locationRework));
	        document.add(location);
	        
	        PdfPTable agency = new PdfPTable(1);
	        agency.setHorizontalAlignment(Element.ALIGN_CENTER);
	        agency.setWidthPercentage(100);
	        agency.addCell(("Specific Agency,If Any : "+specificAgency));
	        document.add(agency);
	        
	        PdfPTable indentEstimatedValue = new PdfPTable(1);
	        indentEstimatedValue.setHorizontalAlignment(Element.ALIGN_CENTER);
	        indentEstimatedValue.setWidthPercentage(100);
	        indentEstimatedValue.addCell(("Estimated Indent Value .(RS) : "+estimatedIndentValue));
	        document.add(indentEstimatedValue);
	        
	        PdfPTable delivery_Date = new PdfPTable(1);
	        delivery_Date.setHorizontalAlignment(Element.ALIGN_CENTER);
	        delivery_Date.setWidthPercentage(100);
	        delivery_Date.addCell(("Delivery Required By (Date) : "+deliveryDate));
	        document.add(delivery_Date);
	        
	        PdfPTable workcompletion_by = new PdfPTable(1);
	        workcompletion_by.setHorizontalAlignment(Element.ALIGN_CENTER);
	        workcompletion_by.setWidthPercentage(100);
	        workcompletion_by.addCell(("Work Completion By (Date) : "+work_completion));
	        document.add(workcompletion_by);
	        
	        PdfPTable previous = new PdfPTable(1);
	        previous.setHorizontalAlignment(Element.ALIGN_CENTER);
	        previous.setWidthPercentage(100);
	        previous.addCell(("Previous Indent Ref. (If Any) : "+previous_if_any));
	        document.add(previous);
	        
	        PdfPTable other_remark = new PdfPTable(1);
	        other_remark.setHorizontalAlignment(Element.ALIGN_LEFT);
	        other_remark.setWidthPercentage(100);
	        other_remark.addCell(("Any Other Remark : "+anyOther));
	        document.add(other_remark);
	         document.add(Chunk.NEWLINE); // space after title

	        
			PdfPTable footerTable = new PdfPTable(4); // 4 columns
            footerTable.setWidthPercentage(100);
            footerTable.setWidths(new float[]{2, 2, 2, 3}); // adjust width ratios

            footerTable.addCell(getFooterCell("Indentor"));
            footerTable.addCell(getFooterCell("HOD"));
            footerTable.addCell(getFooterCell("Director"));
            footerTable.addCell(getFooterCell("AUTHORIZED SIGNATORY"));

            document.add(footerTable);

			
			 
	        
	        Paragraph paragraph15 = new Paragraph("",fontSize_16);
	        document.add(new Phrase("\n \n"));
	        paragraph15.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
			document.add(paragraph15);
			Paragraph paragraph16 = new Paragraph("",fontSize_11);
		    document.add(new Phrase("\n"));
		    paragraph16.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
		    document.add(paragraph16);
			
		    //fetch Customer Details
			/*
			PdfPTable table2 = new PdfPTable(3);
			table2.setWidthPercentage(100);
			table2.addCell(getCell("Customer Name :-" +customerName, PdfPCell.ALIGN_LEFT));
			table2.addCell(getCell("Address :-" +address, PdfPCell.ALIGN_CENTER));
			table2.addCell(getCell("Mobile No :-"+mobileNo, PdfPCell.ALIGN_RIGHT));
			document.add(table2);
			
			Paragraph paragraph17 = new Paragraph("",fontSize_11);
		    document.add(new Phrase("\n"));
		    paragraph17.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
		    
		    document.add(paragraph16);
			PdfPTable table3 = new PdfPTable(3);
			table3.setWidthPercentage(100);
			table3.addCell(getCell("Area :-"+area, PdfPCell.ALIGN_LEFT));
			table3.addCell(getCell("Rate :-"+rate, PdfPCell.ALIGN_CENTER));
			table3.addCell(getCell("Total Amount:- :-"+totalAmpunt, PdfPCell.ALIGN_RIGHT));
			document.add(table3);

			Paragraph paragraph18 = new Paragraph("",fontSize_11);
		    document.add(new Phrase("\n"));
		    paragraph18.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
		    
		    Paragraph paragraph19 = new Paragraph("Billing Invoice Details",fontSize_16);
		    document.add(new Phrase("\n"));
		    paragraph19.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
			document.add(paragraph19);

		    PdfPTable table111 = new PdfPTable(4); // Create 3 columns in table.
		    table111.setWidthPercentage(100);
		    document.add(new Phrase("\n"));

		    //fetch customer payment details
		    String areaDetails=null;
		    String rateDetails=null;
		    String totalAmt=null;
			ResultSet paymentDetails = DatabaseConnection.getResultFromSqlQuery("SELECT SUM(enterPaidAmount) FROM tbl_customer_payment_details WHERE customerId="+customerId);
			while(paymentDetails.next()) {
				totalAmt=paymentDetails.getString(1);
			}
           // Create cells
           PdfPCell cell1 = new PdfPCell(new Paragraph("Description"));
           PdfPCell cell2 = new PdfPCell(new Paragraph("Area"));
           PdfPCell cell3 = new PdfPCell(new Paragraph("Rate"));
           PdfPCell cell31 = new PdfPCell(new Paragraph("Total Amount"));
           
           cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
           cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
           cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
           cell31.setBackgroundColor(BaseColor.LIGHT_GRAY);
           
           PdfPCell cell4 = new PdfPCell(new Paragraph("Unit Fee"));
           PdfPCell cell5 = new PdfPCell(new Paragraph(area));
           PdfPCell cell6 = new PdfPCell(new Paragraph(rate));
           PdfPCell cell7 = new PdfPCell(new Paragraph(totalAmt));

           PdfPCell cell8 = new PdfPCell(new Paragraph("Total Amount"));
           PdfPCell cell9 = new PdfPCell(new Paragraph("-"));
           PdfPCell cell10 = new PdfPCell(new Paragraph("-"));
           PdfPCell cell11 = new PdfPCell(new Paragraph(totalAmt));

           
           // Add cells in table
           table111.addCell(cell1);
           table111.addCell(cell2);
           table111.addCell(cell3);
           table111.addCell(cell31);
           table111.addCell(cell4);
           table111.addCell(cell5);
           table111.addCell(cell6);
           table111.addCell(cell7);

           table111.addCell(cell8);
           table111.addCell(cell9);
           table111.addCell(cell10);
           table111.addCell(cell11);

           // Add table in document
           document.add(table111);
           
           Paragraph paragraph20 = new Paragraph("Singture",fontSize_10);
		    document.add(new Phrase("\n \n"));
		    paragraph20.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
		    document.add(paragraph20);
		    
		    Paragraph paragraph21 = new Paragraph("Date :-"+dateInString,fontSize_10);
		    document.add(new Phrase("\n"));
		    paragraph21.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
		    document.add(paragraph21);
		    
		    Paragraph paragraph22 = new Paragraph("Location :-Pune",fontSize_10);
		    document.add(new Phrase("\n"));
		    paragraph22.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
		    document.add(paragraph22);
		    
		    Paragraph paragraph23 = new Paragraph("Note:-If you have any query please contact with account dpeartment",fontSize_10);
		    document.add(new Phrase("\n"));
		    paragraph23.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
		    document.add(paragraph23);*/
		    
		    Rectangle rect= new Rectangle(577,825,18,15);
		    rect.enableBorderSide(1);
		    rect.enableBorderSide(2);
		    rect.enableBorderSide(4);
		    rect.enableBorderSide(8);
		    rect.setBorder(2);
		    rect.setBorderColor(BaseColor.BLACK);
		    rect.setBorder(Rectangle.BOX);
		    rect.setBorderWidth(2);
		    document.add(rect);
		    
			 //document.add(chunk);
			 document.close();   

			
			//for total amount
				 
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
			"attachment;filename=INVOICE DETAILS_"+customerName+".pdf");
			
			File file = new File("D:/bkp/INVOICE DETAILS_"+customerName+".pdf");  //for UAT
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
			ServletOutputStream out12 = response.getOutputStream();

			byte[] outputByte1 = new byte[4096];
			//copy binary contect to output stream
			while(in.read(outputByte1, 0, 4096) != -1)
			{
				out12.write(outputByte1, 0, 4096);
			}
			fileIn.close();
			out11.flush();
			out11.close();
			
			

			//File srcFile = new File("D:/bkp/INVOICEDETAILS_"+customerName+".pdf");
		    //FileUtils.copyFile(srcFile, response.getOutputStream());
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(customerName);
			out.print(json);
			 
			out.flush();
	        out.close();
	       // response.getWriter();
			//new Gson().toJson(getDetails, response.getWriter());

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private PdfPCell getFooterCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
}
