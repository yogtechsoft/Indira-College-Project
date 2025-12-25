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
<title>Construction</title>
<!-- BOOTSTRAP CORE STYLE  -->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME STYLE  -->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLE  -->
<link href="assets/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
	
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>

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
					<h4 class="header-line">Create Indent
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
<form role="form" action=SaveIndentDetails method="POST">

	<div class="row">
		
		<br><br>		
	</div>
		<div class="row">
				
			<div class="col-md-3">
					<div class="form-group">
						<label>Select Institute Name</label> 
							<select class="form-control" id="InstituteName" name="InstituteName" required>
								<option value="">Select</option>
									<%
										int ip = 0;
									HttpSession asad = request.getSession();
									 Integer userId = (Integer) session.getAttribute("userId");
										ResultSet contractorName = DatabaseConnection.getResultFromSqlQuery("SELECT institute_id,insitiute_name FROM `tbl_user_register_details` WHERE `id`="+userId);
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
						<label>Indenter Name</label> 
						 <input class="form-control" type="text" name="indenterName" id="indenterName" placeholder="Enter Indenter Name" required="required" value="${uname }" readonly="readonly" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Department</label> 
						 <input class="form-control" type="text" name="department" id="department" placeholder="Enter Department" required="required" />
					</div>
				</div>
				
			</div>
			<div class="row">
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Work Description</label>
 						<textarea id="discription" name="discription" rows="5" cols="40" style="width: 307px;height: 60px;" required="required"></textarea><br><br>					
 					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Material Required (IF KNOW)</label>
						<input class="form-control" type="text" name="materialRequired" id="materialRequired" placeholder="Enter Material Required" required="required" />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Quantity</label>
						<input class="form-control" type="text" name="quantity" id="quantity" placeholder="Enter Quantity" required="required" />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Location And Reason For Work</label>
						<input class="form-control" type="text" name="reasonWork" id="reasonWork" placeholder="Enter Location And Reason For Work" required="required" />
					</div>
				</div>
				
				
		</div>
			<div class="row">
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Specific Agency</label>
						<input class="form-control" type="text" name="specificAgency" id="specificAgency" placeholder="Enter Specific Agency" required="required"/>
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Estimated Indent Value .(RS)</label>
						<input class="form-control" type="text" name="indentValue" id="indentValue" placeholder="Enter Indent Value" required="required" />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Delivery Required By (Date)</label>
						<input class="form-control" type="date" name="deliveryRequired" id="deliveryRequired" placeholder="Enter Delivery Details" required="required" />
					</div>
				</div>
				
				<div class="col-md-3">
					<div class="form-group">
						<label>Work Completion By (Date)</label>
						<input class="form-control" type="date" name="workCompletion" id="workCompletion" placeholder="Enter Work Completion Details" required="required" />
					</div>
				</div>
				
		</div>
		<div class="row">
			<div class="col-md-3">
				<div class="form-group">
					<label>Previous Indent Ref (IF ANY)</label>
					<input class="form-control" type="text" name="previousRef" id="previousRef" placeholder="Enter previous Ref" required="required" />
				</div>
			</div>
			<div class="col-md-3">
				<div class="form-group">
					<label>Any Other Remark</label>
					<input class="form-control" type="text" name="remark" id="remark" placeholder="Enter Remark"  required="required" />
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
		
		/* $(document).ready(function () {
		    var today = new Date().toISOString().split('T')[0];
		    $('#deliveryRequired').attr('min', today);
		}); */
		
		var today = new Date();
		today.setDate(today.getDate() + 1);
		$('#deliveryRequired').attr('min', today.toISOString().split('T')[0]);
		$('#workCompletion').attr('min', today.toISOString().split('T')[0]);
	
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
