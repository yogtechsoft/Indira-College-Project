<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.connection.*"%>
<%@ page import="java.sql.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<!-- Mirrored from www.binarytheme.com/BTlivedemos/2014/08/29/horizontal-admin/ by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 Jul 2019 14:46:41 GMT -->
<head>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<title>Construction Management System</title>
<!-- BOOTSTRAP CORE STYLE  -->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME STYLE  -->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLE  -->
<link href="assets/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

</head>
<body>
	<%
		if (session.getAttribute("uname") != null && session.getAttribute("uname") != "") {
	%>
	<div class="navbar navbar-inverse set-radius-zero">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index-2.html"> <img
					src="assets/img/logo.png" />
				</a>

			</div>

			<div class="right-div">
				<a href="admin-logout.jsp" class="btn btn-danger pull-right">LOG
					ME OUT</a>
			</div>
		</div>
	</div>
	<jsp:include page="adminHeader.jsp"></jsp:include>
	<!-- MENU SECTION END-->
	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="">
			<%
				String message = (String) session.getAttribute("message");
				if (message != null) {
				session.removeAttribute("message");
			%>
			<div class="alert alert-danger" id="success">Record Updated successfully.</div>
			<%
				}
			%>
				<div class="col-md-12">
					<h4 class="header-line">View Indent Details
					</h4>
					
						
	<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Register User Details</h4>
        </div>
        <div class="modal-body">
	        <form role="form" action=SaveContractorDetails method="post">
	        
	         <div class="row">
	       			<div class="col-md-4">
							<div class="form-group">
								<label>Enter Contractor Name</label> 
								<input class="form-control" type="text" name="contractorName" id="contractorName" placeholder="Enter Contractor Name" required="required"/>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label>Enter Mobile No</label> 
								<input class="form-control" type="text" name="mobileNo" id="mobileNo" placeholder="Enter Mobile No" required="required" />
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label>Enter Address</label> 
								<input class="form-control" type="text" name="address" id="address" placeholder="Enter Mobile No" required="required" />
							</div>
						</div>
						
						
	         </div>
	         <div class="row">
	       		<div class="col-md-4">
					<div class="form-group">
						<button type="submit" class="btn btn-success" onclick="return confirm('Are you sure Do you want to add this Details?');">Save</button>								 
					</div>
				</div>
	         </div>
	         </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>	
					
  
</div>

	<div class="row">
		
		<br><br>		
	</div>
		
		<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="panel panel-success">
						<div class="panel-heading">Indent Report Summary</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="getSupplierValue">
									<thead>
										<tr>
											<th>Institute Name</th>
											<th>Indenter Name</th>
											<th>Department</th>
											<th>Date</th>
											<th>Work Description</th>
											<th>Material Required (IF KNOW)</th>
											<th>Quantity</th>
											<th>Location And Reason For Work</th>
											<th>Specific Agency</th>
											<th>Estimated Indent Value</th>
											<th>Delivery Required By</th>
											<th>Work Completion By</th>
											<th>Previous Indent Ref</th>
											<th>Any Other Remark</th>
											<th>Status</th>
											<th>Hod Remark</th>
											<th>Hod Approve / Rejected Date</th>
											
										</tr>
									</thead>
									<%
									 int ip = 0;
									 HttpSession asad = request.getSession();
									 Integer instId=(Integer) session.getAttribute("instid");
									ResultSet rs = DatabaseConnection.getResultFromSqlQuery("SELECT * FROM `tbl_project_cordniator_application_status_details` WHERE  institute_id="+instId+"");
									while (rs.next()) {
										%>
									<tbody>
										<tr>
											<td><%=rs.getString("institute_name")%></td>
											<td><%=rs.getString("indenter_name")%></td>
											<td><%=rs.getString("department")%></td>
											<td><%=rs.getString("date")%></td>
											<td><%=rs.getString("work_descrption")%></td>
											<td><%=rs.getString("material_required")%></td>
											<td><%=rs.getString("qunatitiy")%></td>
											<td><%=rs.getString("location_reason_work")%></td>
											<td><%=rs.getString("specific_agency")%></td>
											<td><%=rs.getString("estimated_indent_value")%></td>
											<td><%=rs.getString("delivery_requred")%></td>
											<td><%=rs.getString("workCompletion")%></td>
											<td><%=rs.getString("previous_indent")%></td>
											<td><%=rs.getString("other_remark")%></td>
											<td><%=rs.getString("status")%></td>
											<td><%=rs.getString("hod_status_remark")%></td>
											<td><%=rs.getString("hod_approved_date")%></td>
										</tr>
									</tbody>
									<%
										}
									%>
									
								</table>
							</div>
						</div>
					</div>
				</div>

			</div>
	
	</div>

</div>

	</div>
	<!-- CONTENT-WRAPPER SECTION END-->
	<jsp:include page="admin-footer.jsp"></jsp:include>
	<!-- FOOTER SECTION END-->
	<!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
	<!-- CORE JQUERY  -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS  -->
	<script src="assets/js/bootstrap.js"></script>
	<!-- CUSTOM SCRIPTS  -->
	<script src="assets/js/custom.js"></script>
	<script>
		$("#myInput").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#searchTable tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
	
		//show table ajax call
		
			
		
		
	
		/* (function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})
				(
						window,
						document,
						'script',
						'../../../../../../www.google-analytics.com/analytics.js',
						'ga');

		ga('create', 'UA-58127580-1', 'auto');
		ga('send', 'pageview');
		 */
		
		
		
	</script>
	<%
		} else {
	response.sendRedirect("admin-login.jsp");
	}
	%>
	
	<script>
	
	 window.onload = function() {
         document.getElementById("instituteNameDetails").style.display = "none"; 
         document.getElementById("indentName").style.display = "none"; 
         document.getElementById("materialKnow").style.display = "none"; 
         document.getElementById("estimated").style.display = "none"; 
         document.getElementById("OtherRemark").style.display = "none"; 
         document.getElementById("remarkLabel").style.display = "none"; 
         document.getElementById("downlaodBtn").style.display = "none"; 
         document.getElementById("btnSave").style.display = "none"; 


     };
     
     $("#downloadPDF").click(function(){
    	 var srNo = $('#srNo').val();
    	    window.location.href = 'GenerateInvoiceDetails?srNo=' + srNo;
     });
    
     
	$("#srNo").change(function(){
		
		$.ajax({
			url : 'fetchIndentDetailsforHOD',
			data : {
				srNo:$("#srNo").val()
			},
			success : function(responseText) { 
				 $("#getSupplierValue").find("tr:not(:first)").remove();     
		        
		         var xasd=$("#srNo").val();
		         if(xasd == '0'){
		        	 $("#instituteNameDetails").hide();
			         $("#indentName").hide();
			         $("#materialKnow").hide();
			         $("#estimated").hide();
			         $("#OtherRemark").hide();
			         $("#downlaodBtn").hide();
			         $("#btnSave").hide();
		         }else {
		        	 $("#instituteNameDetails").show();
			         $("#indentName").show();
			         $("#materialKnow").show();
			         $("#estimated").show();
			         $("#OtherRemark").show();
			         $("#downlaodBtn").show();
			         $("#btnSave").show();
		         }
		         
					var dataTablesObj = $.parseJSON(responseText);
					
				$("#instituteName").val(dataTablesObj[0].instituteName);
				$("#indenterName").val(dataTablesObj[0].indenterName);
				$("#department").val(dataTablesObj[0].department);
				$("#date").val(dataTablesObj[0].date);
				$("#workDescription").val(dataTablesObj[0].workDescription);
				$("#materialRequired").val(dataTablesObj[0].materialRequired);
				$("#quantity").val(dataTablesObj[0].quantity);
				$("#reasonWork").val(dataTablesObj[0].reasonWork);
				$("#specificAgency").val(dataTablesObj[0].specificAgency);
				$("#indentValue").val(dataTablesObj[0].indentValue);
				$("#deliveryRequired").val(dataTablesObj[0].deliveryRequired);
				$("#workCompletion").val(dataTablesObj[0].workCompletion);
				$("#previousRef").val(dataTablesObj[0].previousRef);
				$("#remark").val(dataTablesObj[0].remark);
			
				
				
				for(var i=0;i<=dataTablesObj.length;i++){
					$("#fetchValue").append("<tr><td>"+dataTablesObj[i].instituteName+"</td><td>"+dataTablesObj[i].indenterName+"</td><td>"+dataTablesObj[i].department+"</td><td>"+dataTablesObj[i].date+"</td><td>"+dataTablesObj[i].workDescription+"</td><td>"+dataTablesObj[i].materialRequired+"</td><td>"+dataTablesObj[i].quantity+"</td><td>"+dataTablesObj[i].reasonWork+"</td><td>"+dataTablesObj[i].specificAgency+"</td><td>"+dataTablesObj[i].indentValue+"</td><td>"+dataTablesObj[i].deliveryRequired+"</td><td>"+dataTablesObj[i].workCompletion+"</td><td>"+dataTablesObj[i].previousRef+"</td><td>"+dataTablesObj[i].remark+"</td><td>"+dataTablesObj[i].hodRemark+"</td><td>"+dataTablesObj[i].hodAppreovedDate+"</td></tr>")
					}
				
				}	
			
			
		});
	});
	
	$("#customerName").change(function(){
		$.ajax({
			url : 'fetchSupplierMaterialDetails',
			data : {
				customerName : $('#customerName').val()
			},
			success : function(responseText) {
                $("#getSupplierValue").find("tr:not(:first)").remove();
				var dataTablesObj = $.parseJSON(responseText);
				for(var i=0;i<=dataTablesObj.length;i++){
					$("#fetchValue").append("<tr><td>"+dataTablesObj[i].suppliername+"</td><td>"+dataTablesObj[i].materialName+"</td><td>"+dataTablesObj[i].Quantity+"</td><td>"+dataTablesObj[i].rate+"</td><td>"+dataTablesObj[i].transportCharge+"</td><td>"+dataTablesObj[i].totalAmount+"</td><td>"+dataTablesObj[i].date+"</td></tr>")
					if(dataTablesObj == 0){
						$("#amount").val("");
					}else{
						$("#amount").val(dataTablesObj[i].sumTotalAmount);
					}
				}
				
				
			}
		});
	});
	
	$("#supplierNameRecord").change(function(){
		$.ajax({
			url : 'fetchMaterialDataAgainstSupplier',
			data : {
				supplierNameRecord : $('#supplierNameRecord').val(),
				customerName : $('#customerName').val()
			},
			success : function(responseText) {
                $("#getSupplierValue").find("tr:not(:first)").remove();
				var dataTablesObj = $.parseJSON(responseText);
				for(var i=0;i<=dataTablesObj.length;i++){
					$("#fetchValue").append("<tr><td>"+dataTablesObj[i].suppliername+"</td><td>"+dataTablesObj[i].materialName+"</td><td>"+dataTablesObj[i].Quantity+"</td><td>"+dataTablesObj[i].rate+"</td><td>"+dataTablesObj[i].transportCharge+"</td><td>"+dataTablesObj[i].totalAmount+"</td><td>"+dataTablesObj[i].date+"</td></tr>")
					if(dataTablesObj == 0){
						$("#amount").val("");
					}else{
						$("#amount").val(dataTablesObj[i].sumTotalAmount);
					}
				}
				
				
			}
		});
	});
	
	function fetchContractorBasicDetails(){
		$.ajax({
			url : 'fetchContractorCusotmerDetails',
			data : {
				contractorName:$("#fetchContractorName").val()
			},
			success : function(responseText) {
				var dataTablesObj = $.parseJSON(responseText);
				
				
				$("#mobileNoText").val(dataTablesObj[0].mobileNo);
				$("#addressText").val(dataTablesObj[0].address);
				
				}	
		});
	}
	</script>
	
</body>
</html>
