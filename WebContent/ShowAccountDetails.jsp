<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.connection.*"%>
<%@ page import="java.sql.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>



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

   
<script type="text/javascript"> 
/* window.onchange = function() { 
	var chart = new CanvasJS.Chart("chartContainer", {
		theme: "light2",
		animationEnabled: true,
		exportFileName: "New Year Resolutions",
		exportEnabled: true,
		title:{
			text: "Top Categories of New Year's Resolution"
		},
		data: [ 
			{ 
				type: "pie", 
				showInLegend: true, 
				toolTipContent: "{label} <br/> {y} %", 
				indexLabel: "{y} %", 
				dataPoints: [ 
					{ label: "Samsung",  y: 30.3, legendText: "Samsung"}, 
					{ label: "Apple",    y: 19.1, legendText: "Apple"  }, 
					{ label: "Huawei",   y: 4.0,  legendText: "Huawei" }, 
					{ label: "LG",       y: 3.8,  legendText: "LG Electronics"}, 
					{ label: "Lenovo",   y: 3.2,  legendText: "Lenovo" }, 
					{ label: "Others",   y: 39.6, legendText: "Others" } 
				] 
			} 
			] 
	
}); 
chart.render();
}*/
</script> 
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
					<h4 class="header-line">Account Information Details
					</h4>
				
</div>
<form role="form" action=saveAccountDetails method="post">

	<div class="row">
		
		<br><br>		
	</div>
		<div class="row">
			
		<div class="col-md-3">
			<div class="form-group">
				<label>Select Contractor Name</label> 
				<select class="form-control" id="customerName" name="customerName" onchange="fetchDailyWorkProgress();">
				<option>Select</option>
				<%
				int ip = 0;
				ResultSet contractorName = DatabaseConnection.getResultFromSqlQuery("SELECT ss.id,ss.contractorName FROM `tblnewconstructionworkdetails` lp INNER JOIN tblcontractordetails ss on ss.id=lp.contractorId where lp.isWorkCompleted='N' group by ss.contractorName");
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
				<select class="form-control" id="contractorWork" name="contractorWork" onchange="fetchDetailsForInspection();" required="required">
				
				</select>
			</div>
		</div>		
		</div>
		
		<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="panel panel-success">
						<div class="panel-heading">Account Summary Details</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="getSupplierValue">
									<thead>
										<tr>
											<th>Contractor Name</th>
											<th>Campus</th>
											 <th>Building</th>
											<th>Floor</th>
											<th>Work Details</th>
											<th>Start Date</th>
											<th>End Date</th>
											<th>Total Amount</th>
											<th>Advance Payment</th>
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
		
		</form>
						
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
	
	
	
		<%-- $("#showProgress").click(function(){
			var x=$("#customerName").val();
			alert(x);
			 <%
			  String contractorValue=request.getParameter("customerName");
			 System.out.println(contractorValue);
				Gson gsonObj = new Gson();
				Map<Object,Object> map = null;
				List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
				int index = 0;
				ResultSet totalProduct = DatabaseConnection.getResultFromSqlQuery(
				"SELECT totalWorkComplete,totalPendingWork FROM tblnewconstructionworkdetails where id="+contractorValue);
				while (totalProduct.next()) {
					index++;
					map = new HashMap<Object,Object>(); map.put("label", "Total Completed Work"); map.put("y", totalProduct.getString(1)); map.put("exploded", true); list.add(map);
					map = new HashMap<Object,Object>(); map.put("label", "Total Pending Work"); map.put("y", totalProduct.getString(2)); list.add(map);
				}
				String dataPoints = gsonObj.toJson(list);
				%> 
		});
		 --%>
		
		 
		/*  $("#btnUpdate").click(function(){
			 
			 $.ajax({
					url : 'UpdateConstructionWorkProgress',
					data : {
						customerName : $('#customerName').val(),
						workDetails : $('#workDetails').val(),
						contractorWork:$("#contractorWork").val(),
						campusName:$("#campusName").val(),
						building:$("#building").val(),
						floor:$("#floor").val(),
						startDate:$("#startDate").val(),
						endDate:$("#endDate").val(),
						workStatus:$("#workStatus").val()
						
					},
					success : function(responseText) {
		                
						var dataTablesObj = $.parseJSON(responseText);
						alert(responseText);
						window.location.href='PieChart.jsp';
						
					}
				});
			
		 }); */

	
	
	</script>
	
	
<script type="text/javascript">
  
/*  window.onchange = function() { 
	 
	 var dx=$("#customerName").val();
	
	 $.ajax({
			url : 'FetchContractorWorkProgressDetails',
			data : {
				customerName : $('#customerName').val()
			},
			success : function(responseText) {
				var dataTablesObj = $.parseJSON(responseText);
				var chart = new CanvasJS.Chart("chartContainer", {
					theme: "light2",
					animationEnabled: true,
					exportFileName: "New Year Resolutions",
					exportEnabled: true,
					title:{
						text: "Construction Work Progress Details"
					},
					data: [ 
						{ 
							type: "pie", 
							showInLegend: true, 
							toolTipContent: "{label} <br/> {y} %", 
							indexLabel: "{y} %", 
							dataPoints: [ 
								{ label: "Total Completed Work",  y: dataTablesObj[0].totalWorkCompleted, legendText: "Total Completed Work"}, 
								{ label: "Total Pending Work",    y: dataTablesObj[0].totalWorkPending, legendText: "Total Pending Work"  }, 
							] 
						} 
						] 
				});
				
				chart.render();

				 
			}
		});
	
	 
	
	 
	}  */

	
	function fetchDailyWorkProgress(){
		/* $.ajax({
			url : 'FetchContractorWorkProgressDetails',
			data : {
				customerName : $('#customerName').val()
			},
			success : function(responseText) {
				var dataTablesObj = $.parseJSON(responseText);
				var chart = new CanvasJS.Chart("chartContainer", {
					theme: "light2",
					animationEnabled: true,
					exportFileName: "New Year Resolutions",
					exportEnabled: true,
					title:{
						text: "Construction Work Progress Details"
					},
					data: [ 
						{ 
							type: "pie", 
							showInLegend: true, 
							toolTipContent: "{label} <br/> {y} %", 
							indexLabel: "{y} %", 
							dataPoints: [ 
								{ label: "Total Completed Work",  y: dataTablesObj[0].totalWorkCompleted, legendText: "Total Completed Work"}, 
								{ label: "Total Pending Work",    y: dataTablesObj[0].totalWorkPending, legendText: "Total Pending Work"  }, 
							] 
						} 
						] 
				});
				
				chart.render();

				 
			}
		}); */
		
		//fetch work contractor details
		
		
			
			
			$.ajax({
				url : 'fetchConstructionSiteWork',
				data : {
					customerName : $('#customerName').val()
					},
				success : function(responseText) {
	               
					
					var dataTablesObj = $.parseJSON(responseText);
					$("#contractorWork").html("");
					
					$("#contractorWork").append("<option>Select</option>")
					for(var i=0;i<=dataTablesObj.length-1;i++){
						
						$("#contractorWork").append("<option value='"+dataTablesObj[i].workDetailsName+"'>"+dataTablesObj[i].workDetailsName+"</option>");
					}
					
				}
			});

		
		
}	
	
	function fetchDetailsForInspection(){
		
		$.ajax({
			url : 'fetchAccountSummaryDetails',
			data : {
				customerName : $('#customerName').val(),
				contractorWork: $("#contractorWork").val()
				},
			success : function(responseText) {
               
				
				 $("#getSupplierValue").find("tr:not(:first)").remove();
					var dataTablesObj = $.parseJSON(responseText);
					for(var i=0;i<=dataTablesObj.length;i++){
						$("#fetchValue").append("<tr><td>"+dataTablesObj[i].contractorName+"</td><td>"+dataTablesObj[i].campus+"</td><td>"+dataTablesObj[i].building+"</td><td>"+dataTablesObj[i].floor+"</td><td>"+dataTablesObj[i].workdetails+"</td><td>"+dataTablesObj[i].startdate+"</td><td>"+dataTablesObj[i].enddate+"</td><td>"+dataTablesObj[i].totalamount+"</td><td>"+dataTablesObj[i].advancepayment+"</td><td>"+dataTablesObj[i].date+"</td></tr>")
					}
			}
		});
		
		
		// fetch New Construction Details
		
		$.ajax({
			url : 'fetchMaintaincePieChartDetails',
			data : {
				customerName : $('#customerName').val(),
				contractorWork:$('#contractorWork').val()
			},
			success : function(responseText) {
				var dataTablesObj = $.parseJSON(responseText);
				var chart = new CanvasJS.Chart("chartContainer", {
					theme: "light2",
					animationEnabled: true,
					exportFileName: "New Year Resolutions",
					exportEnabled: true,
					title:{
						text: "Maintaince / Building Work Progress Details"
					},
					data: [ 
						{ 
							type: "pie", 
							showInLegend: true, 
							toolTipContent: "{label} <br/> {y} %", 
							indexLabel: "{y} %", 
							dataPoints: [ 
								{ label: "Total Completed Work",  y: dataTablesObj[0].maintainceTotalWorkCompleted, legendText: "Total Completed Work"}, 
								{ label: "Total Pending Work",    y: dataTablesObj[0].maintainceTotalWorkPending, legendText: "Total Pending Work"  }, 
							] 
						} 
						] 
				});
				
				chart.render();

				 
			}
		});
	}
	
	$("#advancePayment").change(function(){
		var totalCost=$("#totalCost").val();
		var advancePaidAmount=$("#advancePayment").val();
		if(advancePaidAmount>totalCost){
			alert("Advance amount can not be greater than total Amount");
			$("#advancePayment").val("");
		}
	});
	
	
</script>
	
		
</body>
</html>
