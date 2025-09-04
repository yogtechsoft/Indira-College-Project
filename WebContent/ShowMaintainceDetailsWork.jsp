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
					<h4 class="header-line">Show Construction work Progress Details 
					</h4>
								<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
  
      
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
				<select class="form-control" id="fetchContractorName" name="fetchContractorName" onchange="showContractorDetailsWork();" >
				<option>Select</option>
				<%
				int ip = 0;
				ResultSet contractorName = DatabaseConnection.getResultFromSqlQuery("SELECT ss.id,ss.contractorName FROM `tblmaintanceconstructiondetails` lp INNER JOIN tblmaintancecontractorname ss on ss.id=lp.contractorId where lp.isWorkCompleted='N' group by ss.contractorName;");
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
						<label>Select Contractor Work</label> 
						<select class="form-control" id="contractorWork" name="contractorWork" onchange="showSiteInspectionDetails();">
						
						</select>
					</div>
				</div>
				
				
			</div>
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="panel panel-success">
						<div class="panel-heading">Inspection Report Details</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="getSupplierValue">
									<thead>
										<tr>
											<th>Site Working Address</th>
											<th>Campus</th>
											 <th>Building</th>
											<th>Floor</th>
											<th>Work Start Date</th>
											<th>Work End Date</th>
											<th>Modified By</th>
											<th>Date</th>
											<th>Daily Status Details</th>
											<th>View Photo</th>
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
			<div class="row">
				
				
				
				
		</div>
			<div class="row">
			
				
				
				
				
			</div>
		
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
	
	function showContractorDetailsWork(){
		$.ajax({
			url : 'FetchDataMaintainceContractorDetails',
			data : {
				customerName : $('#fetchContractorName').val()
				},
			success : function(responseText) {
               
				
				var dataTablesObj = $.parseJSON(responseText);
				$("#contractorWork").html("");
				
				$("#contractorWork").append("<option>Select</option>")
				for(var i=0;i<=dataTablesObj.length-1;i++){
					
					$("#contractorWork").append("<option value='"+dataTablesObj[i].CconstructionSiteDetails+"'>"+dataTablesObj[i].CconstructionSiteDetails+"</option>");
				}
				
			}
		});
	}
	
	function showSiteInspectionDetails(){
		$.ajax({
			url : 'FetchSiteInspectionMaintaince',
			data : {
				fetchContractorName : $('#fetchContractorName').val(),
				contractorWork:$("#contractorWork").val()
				},
			success : function(responseText) {
               
                $("#getSupplierValue").find("tr:not(:first)").remove();
				var dataTablesObj = $.parseJSON(responseText);
				var contractorName=$('#fetchContractorName').val();
				var contractorWork=$("#contractorWork").val()
			
				for(var i=0;i<=dataTablesObj.length;i++){
					var id=dataTablesObj[i].id;
					$("#fetchValue").append("<tr><td>"+dataTablesObj[i].siteWorkDetails+"</td><td>"+dataTablesObj[i].campus+"</td><td>"+dataTablesObj[i].building+"</td><td>"+dataTablesObj[i].floor+"</td><td>"+dataTablesObj[i].startDate+"</td><td>"+dataTablesObj[i].endDate+"</td><td>"+dataTablesObj[i].modifiedBy+"</td><td>"+dataTablesObj[i].todayDate+"</td><td>"+dataTablesObj[i].dailyTaskDetails+"</td><td><a href='DownloadPhotoMaintainceInspection?contractorId=" + contractorName + 
				            "&siteWorkDetails=" + contractorWork + "&id=" + id + " ' target='_blank'>View Photo</a></td></tr>")
				}
				
			}
		});
	}
	</script>
	
</body>
</html>
