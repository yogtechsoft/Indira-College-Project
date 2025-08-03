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
			<div class="alert alert-danger" id="success">Record added successfully.</div>
			<%
				}
			%>
				<div class="col-md-12">
					<h4 class="header-line">Contractor Register &nbsp <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">Add Contractor</button>
					</h4>
								<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Contractor Details</h4>
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
<form role="form" action=NewConstruction method="post">

	<div class="row">
		
		<br><br>		
	</div>
			<div class="row">
				
			<div class="col-md-3">
				<div class="form-group">
				<label>Select Contractor Name</label> 
				<select class="form-control" id="fetchContractorName" name="fetchContractorName" onchange="fetchContractorBasicDetails()" >
				<option>Select</option>
				<%
				int ip = 0;
				ResultSet contractorName = DatabaseConnection.getResultFromSqlQuery("SELECT id,contractorName FROM tblcontractordetails");
				while (contractorName.next()) {
					ip++;
				%>
					<option value="<%=contractorName.getString(1)%>"><%=contractorName.getString(2)%></option> 
					<%
						}
					%>
				</select>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Mobile No</label> 
						 <input class="form-control" type="text" name="mobileNoText" id="mobileNoText" placeholder="Enter Mobile No" required="required" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Address</label> 
						 <input class="form-control" type="text" name="addressText" id="addressText" placeholder="Enter Address" required="required" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Construction Work Type</label> 
						 <input class="form-control" type="text" name="constructionWorkType" id="constructionWorkType" placeholder="Enter Work Type" required="required" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label>Year Of Experience</label> 
						<input class="form-control" type="number" name="yearExperience" id="yearExperience" placeholder="Year of Experience" required="required" />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>GST No</label> 
						<input class="form-control" type="text" name="gstNo" id="gstNo" placeholder="Enter GST No" />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Total Completed Project</label>
						<input class="form-control" type="text" name="totalCompletedProject" id="totalCompletedProject" placeholder="Total Completed Project" />
					</div>
				</div>
		</div>
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label>Campus</label> 
						<select class="form-control"  name="campusName" id="campusName"  required="required">
							<option value="0">Select</option>
							<option value="Computer / IT">Computer / IT</option>
							<option value="EXTC">EXTC</option>
							<option value="Mechnical">Mechnical</option>
						</select>
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Building</label> 
						<input class="form-control" type="text" name="buildingArea" id="buildingArea" placeholder="Enter Building " />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Floor</label>
						<input class="form-control" type="text" name="floor" id="floor" placeholder="Enter Floor" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Work Details</label>
						<input class="form-control" type="text" name="workDetails" id="workDetails" placeholder="Enter Work Details" />
					</div>
				</div>
		</div>
			<div class="row">
			
				<div class="col-md-3">
					<div class="form-group">
						<label>Start Date</label> 
						<input class="form-control" type="date" name="startDate" id="startDate" />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>End Date</label> 
						<input class="form-control" type="date" name="endDate" id="endDate" />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Total Cost</label> 
						<input class="form-control" type="text" name="totalCost" id="totalCost" placeholder="Enter Total Cost " />
					</div>
				</div>
			</div>
		<button type="submit" class="btn btn-success">Save</button>
		</form>
						
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
