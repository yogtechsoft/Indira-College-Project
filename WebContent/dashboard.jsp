<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.connection.*"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
<title>Construction ERP</title>
<!-- BOOTSTRAP CORE STYLE  -->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME STYLE  -->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLE  -->
<link href="assets/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
	
	
	<style>
    .notification {
            position: relative;
            display: inline-block;
            cursor: pointer;
        }
        .notification .badge {
            position: absolute;
            top: -8px;
            right: -8px;
            padding: 4px 7px;
            border-radius: 50%;
            background: red;
            color: white;
            font-size: 12px;
        }
    </style>

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
				<a href="admin-logout.jsp" class="btn btn-danger pull-right">LOG ME OUT</a>
			</div>
			
			<div class="right-div">
				<div class="notification">
			        <i class="fa-solid fa-bell fa-2x"></i>
			        <span class="badge">
			            <%= session.getAttribute("statusCount") != null ? session.getAttribute("statusCount") : 0 %>
			        </span>
			    </div>
			    
		   </div> 
		</div>
	</div>
	<jsp:include page="adminHeader.jsp"></jsp:include>
	<!-- MENU SECTION END-->
	<div class="content-wrapper">
		<div class="container">
			<div class="row pad-botm">
				<div class="col-md-12">
					<h4 class="header-line"> DASHBOARD</h4> 
				</div>

			</div>
		<c:if test = "${ roleId ==1}">
			<div class="row">
			
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-info back-widget-set text-center">
						<i class="fa fa-history fa-5x"></i>
						 <%
						 Integer userId = (Integer) session.getAttribute("userId");	

							ResultSet totalProduct=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_indent_save_details where user_id="+userId);
							totalProduct.next();
							int allProducts=totalProduct.getInt(1);
						%> 
						<h3>
							 <%=allProducts %> 
						</h3>
						Total No Application
					</div>
				</div>
		
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
							ResultSet totalCustomer=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_indent_save_details where user_id="+userId+" and status='Approved'");
							totalCustomer.next();
							int allCustomer=totalCustomer.getInt(1);
						%> 
						<h3> <%=allCustomer --%>
						
						</h3>
						Total No Of Approved Application
					</div>
				</div>
				
				

			</div>
	</c:if>		
	
	<c:if test = "${ roleId ==2}">
			<div class="row">
			
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-info back-widget-set text-center">
						<i class="fa fa-history fa-5x"></i>
						 <%
						 Integer adminUserId = (Integer) session.getAttribute("userId");	

							ResultSet totalProduct1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_hod_application_save_details where user_id="+adminUserId);
							totalProduct1.next();
							int allProducts1=totalProduct1.getInt(1);
						%> 
						<h3>
							 <%=allProducts1 %> 
						</h3>
						Total No Application
					</div>
				</div>
		
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
							ResultSet totalCustomer1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_hod_application_save_details where user_id="+adminUserId+" and status='Approved'");
							totalCustomer1.next();
							int allCustomer1=totalCustomer1.getInt(1);
						%> 
						<h3> <%=allCustomer1 --%>
						
						</h3>
						Total No Of Approved Application
					</div>
				</div>
				
				
				
				

			</div>
	</c:if>	
	
	<c:if test = "${ roleId ==3}">
			<div class="row">
			
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-info back-widget-set text-center">
						<i class="fa fa-history fa-5x"></i>
						 <%
						 Integer adminUserId = (Integer) session.getAttribute("userId");	

							ResultSet totalProduct1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_save_project_dept_application where user_id="+adminUserId);
							totalProduct1.next();
							int allProducts1=totalProduct1.getInt(1);
						%> 
						<h3>
							 <%=allProducts1 %> 
						</h3>
						Total No Application
					</div>
				</div>
		
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
						 
						 Integer projectUserId = (Integer) session.getAttribute("userId");	
							ResultSet totalCustomer1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_save_project_dept_application where user_id="+projectUserId+" and status='Approved'");
							totalCustomer1.next();
							int allCustomer1=totalCustomer1.getInt(1);
						%> 
						<h3> <%=allCustomer1 --%>
						
						</h3>
						Total No Of Approved Application
					</div>
				</div>
				
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
							ResultSet workOrderCreate=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_work_completion_report_project_department where  status='report uploaded'");
						 workOrderCreate.next();
							int wp=workOrderCreate.getInt(1);
						%> 
						<h3> <%=wp --%>
						
						</h3>
						Total No of Work Completion
					</div>
				</div>
				
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
							ResultSet poReport=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_work_completion_report_project_department ");
						 poReport.next();
							int po=poReport.getInt(1);
						%> 
						<h3> <%=po --%>
						
						</h3>
						Total No of PO Report 
					</div>
				</div>
				
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
							ResultSet newConstruction=DatabaseConnection.getResultFromSqlQuery("select count(*) from tblnewconstructionworkdetails ");
						 newConstruction.next();
							int newConst=newConstruction.getInt(1);
						%> 
						<h3> <%=newConst --%>
						
						</h3>
						Total No of New Construction
					</div>
				</div>
				
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
							ResultSet maintaince=DatabaseConnection.getResultFromSqlQuery("select count(*) from tblmaintanceconstructiondetails ");
						 maintaince.next();
							int mp=maintaince.getInt(1);
						%> 
						<h3> <%=mp --%>
						
						</h3>
						Total No Building Maintaince Work 
					</div>
				</div>
				

			</div>
	</c:if>	
	
	<c:if test = "${ roleId ==4}">
			<div class="row">
			
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-info back-widget-set text-center">
						<i class="fa fa-history fa-5x"></i>
						 <%
						 Integer adminUserId = (Integer) session.getAttribute("userId");	

							ResultSet totalProduct1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_director_department_application_received_dewtails where user_id="+adminUserId);
							totalProduct1.next();
							int allProducts1=totalProduct1.getInt(1);
						%> 
						<h3>
							 <%=allProducts1 %> 
						</h3>
						Total No Received Application
					</div>
				</div>
		
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
						 
						 Integer projectUserId = (Integer) session.getAttribute("userId");	
							ResultSet totalCustomer1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_director_department_application_received_dewtails where user_id="+projectUserId+" and status='Approved'");
							totalCustomer1.next();
							int allCustomer1=totalCustomer1.getInt(1);
						%> 
						<h3> <%=allCustomer1 --%>
						
						</h3>
						Total No Of Approved Application
					</div>
				</div>
				
			</div>
	</c:if>	
	
	<c:if test = "${ roleId ==5}">
			<div class="row">
			
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-info back-widget-set text-center">
						<i class="fa fa-history fa-5x"></i>
						 <%
						 Integer adminUserId = (Integer) session.getAttribute("userId");	

							ResultSet totalProduct1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_project_director_excutor_application_details");
							totalProduct1.next();
							int allProducts1=totalProduct1.getInt(1);
						%> 
						<h3>
							 <%=allProducts1 %> 
						</h3>
						Total No Received Application
					</div>
				</div>
		
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
						 
						 Integer projectUserId = (Integer) session.getAttribute("userId");	
							ResultSet totalCustomer1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_project_director_excutor_application_details");
							totalCustomer1.next();
							int allCustomer1=totalCustomer1.getInt(1);
						%> 
						<h3> <%=allCustomer1 --%>
						
						</h3>
						Total No Of Approved Application
					</div>
				</div>
				
			</div>
	</c:if>		
	
	<c:if test = "${ roleId ==7}">
			<div class="row">
			
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-info back-widget-set text-center">
						<i class="fa fa-history fa-5x"></i>
						 <%
						 Integer adminUserId = (Integer) session.getAttribute("userId");	

							ResultSet totalProduct1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_account_department_application_received_details");
							totalProduct1.next();
							int allProducts1=totalProduct1.getInt(1);
						%> 
						<h3>
							 <%=allProducts1 %> 
						</h3>
						Total No Received Application
					</div>
				</div>
		
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
						 
						 Integer projectUserId = (Integer) session.getAttribute("userId");	
							ResultSet totalCustomer1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_account_department_application_received_details");
							totalCustomer1.next();
							int allCustomer1=totalCustomer1.getInt(1);
						%> 
						<h3> <%=allCustomer1 --%>
						
						</h3>
						Total No Of Approved Application
					</div>
				</div>
				
			</div>
	</c:if>	
	
	<c:if test = "${ roleId ==6}">
			<div class="row">
			
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-info back-widget-set text-center">
						<i class="fa fa-history fa-5x"></i>
						 <%
						 Integer adminUserId = (Integer) session.getAttribute("userId");	

							ResultSet totalProduct1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_purchase_department_application_received_details");
							totalProduct1.next();
							int allProducts1=totalProduct1.getInt(1);
						%> 
						<h3>
							 <%=allProducts1 %> 
						</h3>
						Total No Received Application
					</div>
				</div>
		
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
						 
						 Integer projectUserId = (Integer) session.getAttribute("userId");	
							ResultSet totalCustomer1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_purchase_department_application_received_details");
							totalCustomer1.next();
							int allCustomer1=totalCustomer1.getInt(1);
						%> 
						<h3> <%=allCustomer1 --%>
						
						</h3>
						Total No Of Approved Application
					</div>
				</div>
				
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
						 
							ResultSet puchasePoCreated=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_create_po_report");
						 puchasePoCreated.next();
							int poCreated=puchasePoCreated.getInt(1);
						%> 
						<h3> <%=poCreated --%>
						
						</h3>
						Total No Of Po Report
					</div>
				</div>
				
			</div>
	</c:if>	
	<c:if test = "${ roleId ==8}">
			<div class="row">
			
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-info back-widget-set text-center">
						<i class="fa fa-history fa-5x"></i>
						 <%
						 Integer adminUserId = (Integer) session.getAttribute("userId");	

							ResultSet totalProduct1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_cp_department_application_received_details");
							totalProduct1.next();
							int allProducts1=totalProduct1.getInt(1);
						%> 
						<h3>
							 <%=allProducts1 %> 
						</h3>
						Total No Received Application
					</div>
				</div>
		
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="alert alert-success back-widget-set text-center">
						<i class="fa fa-users fa-5x"></i>
						 <%
						 
						 Integer projectUserId = (Integer) session.getAttribute("userId");	
							ResultSet totalCustomer1=DatabaseConnection.getResultFromSqlQuery("select count(*) from tbl_cp_department_application_received_details");
							totalCustomer1.next();
							int allCustomer1=totalCustomer1.getInt(1);
						%> 
						<h3> <%=allCustomer1 --%>
						
						</h3>
						Total No Of Approved Application
					</div>
				</div>
			</div>
	</c:if>	
		<div class="row">
			
				
				
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
	
	</script>
	<script>
	function toggleNotifications() {
	    var box = $("#count").val();
	    alert(box);
	}
	
	</script>
	<script>
		(function(i, s, o, g, r, a, m) {
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
	</script>
	<%
		} else {
	response.sendRedirect("admin-login.jsp");
	}
	%>
</body>
</html>
