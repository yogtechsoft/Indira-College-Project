<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
	<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<section class="menu-section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="navbar-collapse collapse ">
					<ul id="menu-top" class="nav navbar-nav navbar-right">
						<li><a href="dashboard.jsp">Home</a></li>
						<c:if test = "${roleId !=3 && roleId !=2 && roleId !=4}">	
						<li><a href="CreateIndent.jsp">Create Indent</a></li>
						<li><a href="ViewIndentReport.jsp">View Intent Details</a></li>
						</c:if>
						<c:if test = "${ roleId ==2}">
						<li><a href="ViewIndentForHOD.jsp">View Indent</a></li>
						</c:if>
						<c:if test = "${ roleId ==4}">
						<li><a href="ViewIndentForHOD.jsp">View Indent Details</a></li>
						</c:if>
					<c:if test = "${roleId !=1 && roleId !=2 && roleId !=4}">	
						<li><a href="#" class="dropdown-toggle" id="ddlmenuItem"
							data-toggle="dropdown">Construction Details <i class="fa fa-angle-down"></i></a>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="ddlmenuItem">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="ContractorRegister.jsp">New Construction Details</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="PieChart.jsp">Update Construction work</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="FetchConstructionDetails.jsp">Show Construction Details</a></li>	
									
							</ul></li>
						<li><a href="#" class="dropdown-toggle" id="ddlmenuItem"
							data-toggle="dropdown"> Building Reparing Work<i class="fa fa-angle-down"></i></a>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="ddlmenuItem">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="MaitainceContractorDetails.jsp">Add Maintaince / Building Work</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="UpdateMaintanceWorkDetails.jsp">Update Maintaince  Work</a></li>	
								
							</ul></li>

					<!-- 	<li><a href="#" class="dropdown-toggle" id="ddlmenuItem"
							data-toggle="dropdown"><i class="fa fa-angle-down"></i></a>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="ddlmenuItem">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="PropertyMaintainceWork.jsp">Assign Property Work</a></li>
							</ul></li> -->
						<li><a href="#" class="dropdown-toggle" id="ddlmenuItem"
							data-toggle="dropdown">Account Details <i class="fa fa-angle-down"></i></a>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="ddlmenuItem">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="AccountInformationDetails.jsp">Account Information Details</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
									href="ShowAccountDetails.jsp">Show Account Details Summary</a></li>
								
							</ul></li>
							
							<li><a href="#" class="dropdown-toggle" id="ddlmenuItem"
						data-toggle="dropdown">Advocate Details <i class="fa fa-angle-down"></i></a>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="ddlmenuItem">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="AdvocateWork.jsp">Legal Work Details</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
									href="SupplierPaymentDetails.jsp">Advocate Work Details</a></li>
							</ul></li>
					</c:if>	
						<li><a href="#" class="dropdown-toggle" id="ddlmenuItem"
							data-toggle="dropdown"><font color="#ff8c00"><%=session.getAttribute("uname")%>&nbsp;<i
								class="fa fa-angle-down"></i></font></a>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="ddlmenuItem">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="admin-my-account.jsp">My Accounts</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="admin-change-own-password.jsp">Change Password</a></li>
							<c:if test = "${roleId !=1 && roleId !=2 && roleId !=4}">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="RegisterUser.jsp">Register User</a></li>
							</c:if>	
							<c:if test = "${roleId == 3}">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="IndentDetailsProject.jsp">View Indent Report</a></li>
							</c:if>		
							</ul>
							</li>		
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>