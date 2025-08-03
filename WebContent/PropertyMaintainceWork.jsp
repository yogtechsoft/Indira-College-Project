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
					<h4 class="header-line">Property Maintance Work
					</h4>
					<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
           
    </div>
  </div>
  
</div>
<form role="form" action=SaveMaterialDetails method="post">

	<div class="row">
		
		<br><br>		
	</div>
			<div class="row">
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Enter Vendor Name</label> 
						<input class="form-control" name="vendorName" id="vendorName" placeholder="Enter Vendor Name" required="required">
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Vendor Mobile No</label> 
						 <input class="form-control" type="text" name="vendorMobileNo" id="vendorMobileNo" placeholder="Enter Mobile No" required="required" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Vendor Address</label> 
						 <input class="form-control" type="text" name="vendorAddress" id="vendorAddress" placeholder="Enter Address" required="required" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Vendor Work Type</label> 
						 <input class="form-control" type="text" name="vendorWorkType" id="vendorWorkType" placeholder="Enter Work Type" required="required" />
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
						<label>Work Details</label>
						<input class="form-control" type="text" name="workDetails" id="workDetails" placeholder="Enter Work Details" />
					</div>
				</div>
		</div>
			<div class="row">
				
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Select Date </label> 
						<input class="form-control" type="date" name="todayDate" id="todayDate" />
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
		$("#transportCharge").change(function(){
			var quantity=$("#Quantity").val();
			var rate=$("#Rate").val();
			var tranportCharges=$("#transportCharge").val();
			if(tranportCharges ==""){
				$("#transportCharge").val(0);
			}else{
				var totalAmount=parseFloat(rate)*parseFloat(quantity)+parseFloat(tranportCharges);
				$("#totalAmount").val(totalAmount);
			}
		});
		
		
		
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
	</script>
	
</body>
</html>
