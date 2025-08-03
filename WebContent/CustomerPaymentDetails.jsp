<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.connection.*"%>
<%@ page import="java.sql.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<!-- Mirrored from www.binarytheme.com/BTlivedemos/2014/08/29/horizontal-admin/ by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 Jul 2019 14:46:41 GMT -->
<head>
<meta name="advend_user_key" content="316794">
                <script>
                window.advend = { key: "44fdfc87745d5b3a2c8198c4fb9bf775" };
                </script>
                <script src="https://dashboard.advend.co/advend.js" async></script>
                <script src="https://dashboard.advend.co/tracking.js" async></script>
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
			<div class="row pad-botm">
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
					<h4 class="header-line">Customer Payment Details
					</h4>
					<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
     
      
    </div>
  </div>
  
</div>
<form role="form" action=SaveCustomerPaymentDetails method="post">

	<div class="row">
		<div class="col-md-4">
			<div class="form-group">
				<label>Select Customer Name</label> 
				
				<select class="form-control" name="customerName" id="customerName">
				<option>Select</option>
				<%
				int index = 0;
				ResultSet totalProduct = DatabaseConnection
					.getResultFromSqlQuery("select * from tbl_customer_register");
				while (totalProduct.next()) {
					index++;
				%>
					<option value="<%=totalProduct.getInt(1)%>"><%=totalProduct.getString(2)%></option> 
					<%
						}
					%>
				</select>
			</div>
		</div>
		<br><br>		
	</div>
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label>Customer Name</label> 
						<input class="form-control" type="text" name="customerNameDetails" id="customerNameDetails" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Site Address</label> 
						<input class="form-control" type="text" name="siteAddress" id="siteAddress" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Description</label> 
						 <input class="form-control" type="text" name="description" id="description" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Owner Mobile No</label> 
						<input class="form-control" type="text" name="mobileNo" id="mobileNo" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label>Area</label> 
						<input class="form-control" type="text" name="area" id="area" />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Rate</label> 
						<input class="form-control" type="text" name="rate" id="rate"  />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Total Amount</label> 
						<input class="form-control" type="text" name="totalAmount" id="totalAmount"  />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Enter Amount</label> 
						<input class="form-control" type="text" name="enterAmount" id="enterAmount"  />
					</div>
				</div>
				
				
		</div>
		<div class="row">
			<div class="col-md-3">
					<div class="form-group">
						<label>Date</label> 
						<input class="form-control" type="date" name="date" id="date"  />
					</div>
				</div>
		</div>
		<button type="submit" class="btn btn-success" id="saveValue" name="saveValue" onclick="return confirm('Are you sure Do you want to add this Details?');">Save</button>
		</form>
						
		</div>
		<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="panel panel-success">
						<div class="panel-heading">Customer Payment History</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<button type="button" class="btn btn-success" id="showHistory" name="showHistory">Show Payment History</button>												
										</div>
									</div>	
								<form action="GenerateInvoiceDetails" method="get">
									<div class="col-md-3">
										<div class="form-group">
											<button type="submit" class="btn btn-success" id="generateInvoice" name="generateInvoice">Generate Invoice</button>												
											<input type="hidden" id="custName" name="custName">
											
										</div>
									</div>
								</form>		
									<div class="col-md-3" style="float:right;">
										<div class="form-group">
												<label>Total Amount:-</label> 
												<input class="form-control" type="text" name="amount" id="amount" readonly="readonly" />
										</div>
									</div>	
								</div>
								<table class="table table-striped table-bordered table-hover" id="getSupplierValue">
									<thead>
										<tr>
											<th>Customer Name</th>
											<th>Site Address</th>
											 <th>Description</th>
											<th>Owner Mobile No</th>
											<th>Area</th>
											<th>Rate</th>
											<th>Amount to Be Paid</th>
											<th>Date</th>
										</tr>
									</thead>
									
									<tbody id="fetchValue">
										
									</tbody>
									
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
		
			$("#enterAmount").change(function(){
				var totalAmount=$("#totalAmount").val();
				var enteredAmount=$("#enterAmount").val();
				if(parseFloat(enteredAmount)>parseFloat(totalAmount)){
					alert("Amount Cannot be Greater Than total Amount!");
				}
			});
		
		
	
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
		$("#Rate").change(function(){
			var quantity=$("#area").val();
			var rate=$("#Rate").val();
			
			var calculation=parseFloat(quantity)*parseFloat(rate);
			$("#totalAmount").val(calculation);
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
			url : 'fetchCustomerDetailsPayment',
			data : {
				customerName : $('#customerName').val()
			},
			success : function(responseText) {
                $("#fetchValue").find("tr:not(:first)").remove();
				var cust=$('#customerName').val();
				$("#custName").val(cust);
				var dataTablesObj = $.parseJSON(responseText);
				
				$("#customerNameDetails").val(dataTablesObj[0].customerName);
				$("#siteAddress").val(dataTablesObj[0].siteAddress);
				$("#description").val(dataTablesObj[0].description);
				$("#mobileNo").val(dataTablesObj[0].ownerNoMobile);
				$("#area").val(dataTablesObj[0].area);
				$("#rate").val(dataTablesObj[0].rate);
				$("#totalAmount").val(dataTablesObj[0].totalAmount);
				$('#customerNameDetails').prop('readonly', true);
				$('#siteAddress').prop('readonly', true);
				$('#description').prop('readonly', true);
				$('#mobileNo').prop('readonly', true);
				$('#area').prop('readonly', true);
				$('#rate').prop('readonly', true);
				$('#totalAmount').prop('readonly', true);
				
				var totalAmount=$("#totalAmount").val();
				var enterPaidAMount=(dataTablesObj[0].totalSumAmount);
				if(parseFloat(totalAmount)==parseFloat(enterPaidAMount)){
					alert("Amount Already Paid And Invoice Generated!");
					$('#enterAmount').prop('readonly', true);
					$('#saveValue').prop('readonly', true);
					$('#generateInvoice').prop('disabled',true);
				}else{
					$('#enterAmount').prop('readonly', false);
					$('#saveValue').prop('readonly', false);
					$('#generateInvoice').prop('disabled',false);

				}
			}
		});
	});
	
	//show payment history 
	
	$("#showHistory").click(function(){
		var customerValue=$("#customerName").val();
		if(customerValue=='Select'){
			alert("Please Select Customer Name!");
		}
		else{
			$.ajax({
				url : 'fetchCustomerPaymentHistory',
				data : {
					customerName : $('#customerName').val()
				},
				success : function(responseText) {
	                $("#getSupplierValue").find("tr:not(:first)").remove();

					var dataTablesObj = $.parseJSON(responseText);
					
					for(var i=0;i<=dataTablesObj.length;i++){
						$("#fetchValue").append("<tr><td>"+dataTablesObj[i].customerNameDetails+"</td><td>"+dataTablesObj[i].siteAddressDetails+"</td><td>"+dataTablesObj[i].descriptionDetails+"</td><td>"+dataTablesObj[i].ownerNoMobileDetails+"</td><td>"+dataTablesObj[i].areaDetails+"</td><td>"+dataTablesObj[i].rateDetails+"</td><td>"+dataTablesObj[i].amountToBePaid+"</td><td>"+dataTablesObj[i].dateDetails+"</td></tr>")
						$("#amount").val(dataTablesObj[i].totalSumAmount);
					}
				}
			});
		}
		
	});
	
	
	$("#supplierNameRecord").change(function(){
		$.ajax({
			url : 'contractorWorkingDetails',
			data : {
				supplierNameRecord : $('#supplierNameRecord').val()
			},
			success : function(responseText) {
                $("#getSupplierValue").find("tr:not(:first)").remove();
				var dataTablesObj = $.parseJSON(responseText);
				for(var i=0;i<=dataTablesObj.length;i++){
					$("#fetchValue").append("<tr><td>"+dataTablesObj[i].contractorName+"</td><td>"+dataTablesObj[i].workType+"</td><td>"+dataTablesObj[i].area+"</td><td>"+dataTablesObj[i].rate+"</td><td>"+dataTablesObj[i].description+"</td><td>"+dataTablesObj[i].date+"</td><td>"+dataTablesObj[i].totalAmount+"</td></tr>")
					if(dataTablesObj == 0){
						$("#amount").val("");
					}else{
						$("#amount").val(dataTablesObj[i].sumTotalAmount);
					}
				}
				
				
			}
		});
	});
	
	 /* $("#generateInvoice").click(function(){
		var data=$("#customerName").val();
		if(data=='Select'){
			alert("Please select Customer Name");
		}else{
			$.ajax({
				url : 'GenerateInvoiceDetails',
				data : {
					customerName : $('#customerName').val()
				},
				success : function(responseText) {
	               
					alert("Invoice Generated Successfully");
					window.location.href='CustomerPaymentDetails.jsp';
					
				}
			});
		}
		
	}); */
	</script>
	
</body>
</html>
