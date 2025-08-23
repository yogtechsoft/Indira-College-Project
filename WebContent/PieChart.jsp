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
					<h4 class="header-line">Contractor Register
					</h4>
				
</div>
<form role="form" action=UpdateConstructionWorkProgress method="post">

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
				<select class="form-control" id="contractorWork" name="contractorWork" onchange="fetchDetailsForInspection();">
				
				</select>
			</div>
		</div>
		
	
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Enter Total Work Completed in Number</label> 
				<input class="form-control" type="number" name="workDetails" id="workDetails">
				
			</div>
		</div>
				
		</div>
		
		<div class="row">
				<h4>Site Inspection Details</h4>
			<div class="col-md-3">
				<div class="form-group">
					<label>Campus</label> 
					<input class="form-control" type="text" name="campusName" id="campusName">
				</div>
			</div>
			<div class="col-md-3">
				<div class="form-group">
					<label>Building</label> 
					<input class="form-control" type="text" name="building" id="building">
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="form-group">
					<label>Floor</label> 
					<input class="form-control" type="text" name="floor" id="floor">
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="form-group">
					<label>Start Date</label> 
					<input class="form-control" type="date" name="startDate" id="startDate">
				</div>
			</div>
		</div>
		
		<div class="row">
		
			<div class="col-md-3">
				<div class="form-group">
					<label>End Date</label> 
					<input class="form-control" type="date" name="endDate" id="endDate">
				</div>
			</div>
		<div class="col-md-3">	
			<div class="form-group">
					<label>Daily Work Status Details</label> 
					<input class="form-control" type="text" name="workStatus" id="workStatus">
				</div>
		</div>
		<div class="col-md-3">
			<div class="form-group" style="margin-top:28px;">
				
						<button type="submit"  class="btn btn-success" id="btnUpdate" name="btnUpdate" >Update Work Progress</button>
				
			</div>
		</div>
		</div>
		
		
			<div class="row">
				
			<div id="chartContainer"  style="width: 100%; height: 300px;display: block;"></div> 
			<script src="https://cdn.canvasjs.com/canvasjs.min.js"></script>
			
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
				url : 'fetchContractorConstructionSite',
				data : {
					customerName : $('#customerName').val()
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
	
	function fetchDetailsForInspection(){
		
		$.ajax({
			url : 'fetchConstructionWorkInspectionDetails',
			data : {
				customerName : $('#customerName').val(),
				contractorWork: $("#contractorWork").val()
				},
			success : function(responseText) {
               
				
				var dataTablesObj = $.parseJSON(responseText);
				
				
				
				for(var i=0;i<=dataTablesObj.length-1;i++){
					
					$("#campusName").val(dataTablesObj[i].campus);
					$("#building").val(dataTablesObj[i].building);
					$("#floor").val(dataTablesObj[i].floor);
					
					$("#campusName").attr("readonly",true);
					$("#building").attr("readonly",true);
					$("#floor").attr("readonly",true);

					
				
				}
				


				
			}
		});
		
		
		// fetch New Construction Details
		
		$.ajax({
			url : 'FetchConstructionWorkDetails',
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
						text: "Construction Work Progress Details"
					},
					data: [ 
						{ 
							type: "pie", 
							showInLegend: true, 
							toolTipContent: "{label} <br/> {y} %", 
							indexLabel: "{y} %", 
							dataPoints: [ 
								{ label: "Total Completed Work",  y: dataTablesObj[0].GrpahtotalWorkCompleted, legendText: "Total Completed Work"}, 
								{ label: "Total Pending Work",    y: dataTablesObj[0].GrpahtotalWorkPending, legendText: "Total Pending Work"  }, 
							] 
						} 
						] 
				});
				
				chart.render();

				 
			}
		});
	}
	
	
</script>
	
		
</body>
</html>
