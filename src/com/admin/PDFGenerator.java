package com.admin;

import java.io.FileOutputStream;
import java.util.Random;

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

 class PDFGenerator {
	
	public static PdfPCell getCell(String text, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    return cell;
	}
	
	public static void main(String[] args) {
		
		 
		 try {
			 Document document = new Document();
			 

			 PdfWriter.getInstance(document, new FileOutputStream("D:/bkp/iTextHelloWorld.pdf"));

			 document.open();
			 //Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			 //Chunk chunk = new Chunk("Hello World", font);
             Font fontSize_10 =  FontFactory.getFont(FontFactory.TIMES, 15f);
             Font fontSize_12 =  FontFactory.getFont(FontFactory.TIMES, 20f);
             Font fontSize_11 =  FontFactory.getFont(FontFactory.TIMES, 10f);
             Font fontSize_16 =  FontFactory.getFont(FontFactory.COURIER_BOLD, 17f);
             
           //for invoice Generated
 			
 			Random random = new Random();
 			int newRandomCaptcha = random.nextInt(9000) + 10000;

           
             Paragraph invoice = new Paragraph("INVOICE",fontSize_12);
             invoice.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
			 document.add(invoice);

			 Paragraph paragraph01 = new Paragraph("Yogeshwar Technology & Solution",fontSize_10);
	         paragraph01.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
			 document.add(paragraph01);
			 
			 Paragraph paragraph1 = new Paragraph("City:-Mumbai,State:-Mahashatra,Zip:-400604",fontSize_11);
	         paragraph1.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
			 document.add(paragraph1);
			 
			 Paragraph paragraph12 = new Paragraph("Address:-near Hinjwadi phase 1 pune",fontSize_11);
	         paragraph12.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
			 document.add(paragraph12);
			 
		    PdfPTable table = new PdfPTable(2);
		    table.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.setWidthPercentage(160 / 5.23f);
		    table.addCell(("Invoice No")); 
	        table.addCell(""+(newRandomCaptcha)); 
	        table.addCell(("Date")); 
	        table.addCell(("14/2/1993")); 
	        table.addCell(("Customer Id")); 
	        table.addCell(("2322")); 
	        table.addCell(("Location")); 
	        table.addCell(("Pune")); 
             // Add table in document
	        document.add(table);
	      
	        LineSeparator ls = new LineSeparator();
	        document.add(new Chunk(ls));
	        
	        Paragraph paragraph15 = new Paragraph("Customer Details",fontSize_16);
	        document.add(new Phrase("\n \n"));
	        paragraph15.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
			document.add(paragraph15);
		        
			
			
			
			
			Paragraph paragraph16 = new Paragraph("",fontSize_11);
		    document.add(new Phrase("\n"));
		    paragraph16.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
		    document.add(paragraph16);
			
			PdfPTable table2 = new PdfPTable(3);
			table2.setWidthPercentage(100);
			table2.addCell(getCell("Customer Name :-Abhishek Phalke", PdfPCell.ALIGN_LEFT));
			table2.addCell(getCell("Address :-thane near kamgar hospital", PdfPCell.ALIGN_CENTER));
			table2.addCell(getCell("City :-Banglore", PdfPCell.ALIGN_RIGHT));
			document.add(table2);
			
			Paragraph paragraph17 = new Paragraph("",fontSize_11);
		    document.add(new Phrase("\n"));
		    paragraph17.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
		    
		    document.add(paragraph16);
			PdfPTable table3 = new PdfPTable(3);
			table3.setWidthPercentage(100);
			table3.addCell(getCell("Area :-1222", PdfPCell.ALIGN_LEFT));
			table3.addCell(getCell("Rate :-1000", PdfPCell.ALIGN_CENTER));
			table3.addCell(getCell("Total Amount:- :-2450000", PdfPCell.ALIGN_RIGHT));
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

            // Create cells
            PdfPCell cell1 = new PdfPCell(new Paragraph("Description"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Quantity"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Unit Price"));
            PdfPCell cell31 = new PdfPCell(new Paragraph("Total Amount"));
            
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell31.setBackgroundColor(BaseColor.LIGHT_GRAY);
            
            PdfPCell cell4 = new PdfPCell(new Paragraph("Unit Fee"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("1"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("2000"));
            PdfPCell cell7 = new PdfPCell(new Paragraph("1250000"));

            PdfPCell cell8 = new PdfPCell(new Paragraph("Total Amount"));
            PdfPCell cell9 = new PdfPCell(new Paragraph("-"));
            PdfPCell cell10 = new PdfPCell(new Paragraph("-"));
            PdfPCell cell11 = new PdfPCell(new Paragraph("1250000"));

            
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
		    
		    Paragraph paragraph21 = new Paragraph("Date:-",fontSize_10);
		    document.add(new Phrase("\n"));
		    paragraph21.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
		    document.add(paragraph21);
		    
		    Paragraph paragraph22 = new Paragraph("Location:-",fontSize_10);
		    document.add(new Phrase("\n"));
		    paragraph22.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
		    document.add(paragraph22);
		    
		    Paragraph paragraph23 = new Paragraph("Note:-If you have any query please contact with account dpeartment",fontSize_10);
		    document.add(new Phrase("\n"));
		    paragraph23.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
		    document.add(paragraph23);
		    
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
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 
	}
	
	
	
	
}
