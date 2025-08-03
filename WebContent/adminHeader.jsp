<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<section class="menu-section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="navbar-collapse collapse ">
					<ul id="menu-top" class="nav navbar-nav navbar-right">
						<li><a href="dashboard.jsp">Home</a></li>
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

						<li><a href="#" class="dropdown-toggle" id="ddlmenuItem"
							data-toggle="dropdown"><font color="#ff8c00"><%=session.getAttribute("uname")%>&nbsp;<i
								class="fa fa-angle-down"></i></font></a>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="ddlmenuItem">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="admin-my-account.jsp">My Accounts</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="admin-change-own-password.jsp">Change Password</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>