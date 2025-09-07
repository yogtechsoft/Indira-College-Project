package com.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String pass = request.getParameter("upass");
			String vercode = request.getParameter("vercode");
			String captchaDB = null;
			HttpSession hs = request.getSession();
			HttpSession name = request.getSession();

			String tokens = UUID.randomUUID().toString();
			Random random = new Random();
			int newRandomCaptcha = random.nextInt(9000) + 10000;
			Connection con = DatabaseConnection.getConnection();
			Statement st = con.createStatement();
			String roleId ="";
			String isActive="";
			String userName="";
			String password="";
			String displayName="";
			int id=0;
			int role=0;
			String isb="";
			int instid=0;
			int instituteCorporateId=0;
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("select * from tblcaptcha");
			if (captchResultSet.next()) {
				captchaDB = captchResultSet.getString(2);
			}
			if (captchaDB.equals(vercode)) {
				ResultSet resultset = st.executeQuery("select * from tbladmin where email='" + email + "' AND password='" + pass + "'");
				while(resultset.next()) {
					 roleId =resultset.getString("role");
					 isActive=resultset.getString("is_Active");
					 userName =resultset.getString("email");
					 password =resultset.getString("password");
					 displayName=resultset.getString("name");
					 
				}
				
				if(roleId.equals("1") ) {
					if (email.equals(userName) && pass.equals(password)) {
						hs.setAttribute("uname",displayName );
						ResultSet dd = st.executeQuery("SELECT id,insitiute_name,institute_id,role_id FROM `tbl_user_register_details` WHERE emailId='" + email +"' and password='" + pass + "'");
						 HttpSession session = request.getSession();
						int roleIdmAster=0;
						 while(dd.next()) {
							id =dd.getInt("id");
							instituteCorporateId=dd.getInt("institute_id");
							roleIdmAster=dd.getInt("role_id");
						}
						 session.setAttribute("userId", id);
						 name.setAttribute("roleId",roleIdmAster );
						 name.setAttribute("institute",instituteCorporateId );
						 response.sendRedirect("dashboard.jsp?_tokens='" + tokens + "'");
					} else {
						String message = "You have enter wrong credentials";
						hs.setAttribute("credential", message);
						response.sendRedirect("admin-login.jsp");
						int update = DatabaseConnection.insertUpdateFromSqlQuery("update tblcaptcha set captcha='"+ newRandomCaptcha + "'");
					}
				}else if(isActive.equals("Y") && roleId.equals("2")) {
					if (email.equals(userName) && pass.equals(password)) {
						name.setAttribute("uname",displayName );
						ResultSet institute = st.executeQuery("SELECT id,insitiute_name,role_id,institute_id FROM `tbl_user_register_details` WHERE emailId='" + email +"' and password='" + pass + "'");
						 HttpSession session = request.getSession();
						while(institute.next()) {
							id =institute.getInt("id");
							role=institute.getInt("role_id");
							isb=institute.getString("insitiute_name");
							instid=institute.getInt("institute_id");
						}
						ResultSet status = st.executeQuery("select count(status) FROM `tbl_indent_save_details` WHERE status='Waiting For Approval' and institute_id="+instid+"");
						int statusCount=0;
						while(status.next()) {
							statusCount=status.getInt(1);
						}

						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
						name.setAttribute("instituteName",isb );
						name.setAttribute("instid",instid );
						name.setAttribute("statusCount", statusCount);
						response.sendRedirect("dashboard.jsp?_tokens='" + tokens + "'");
					} else {
						String message = "You have enter wrong credentials";
						name.setAttribute("credential", message);
						response.sendRedirect("admin-login.jsp");
						int update = DatabaseConnection.insertUpdateFromSqlQuery("update tblcaptcha set captcha='"+ newRandomCaptcha + "'");
					}
				}else if(isActive.equals("Y") && roleId.equals("3")) {
					if (email.equals(userName) && pass.equals(password)) {
						name.setAttribute("uname",displayName );
						ResultSet institute = st.executeQuery("SELECT id,role_id FROM `tbl_user_register_details` WHERE emailId='" + email +"' and password='" + pass + "'");
						 HttpSession session = request.getSession();
						while(institute.next()) {
							id =institute.getInt("id");
							role=institute.getInt("role_id");
						}
						ResultSet projectStatus = st.executeQuery("select count(status) FROM `tbl_project_department_application_details` WHERE status='Waiting For Approval' and role_id="+roleId+"");
						int statusCount1=0;
						while(projectStatus.next()) {
							statusCount1=projectStatus.getInt(1);
						}
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
						name.setAttribute("statusCount", statusCount1);
						response.sendRedirect("dashboard.jsp?_tokens='" + tokens + "'");
					} else {
						String message = "You have enter wrong credentials";
						name.setAttribute("credential", message);
						response.sendRedirect("admin-login.jsp");
						int update = DatabaseConnection.insertUpdateFromSqlQuery("update tblcaptcha set captcha='"+ newRandomCaptcha + "'");
					}
				}else if(isActive.equals("Y") && roleId.equals("4")) {
					if (email.equals(userName) && pass.equals(password)) {
						name.setAttribute("uname",displayName );
						ResultSet institute = st.executeQuery("SELECT id,role_id FROM `tbl_user_register_details` WHERE emailId='" + email +"' and password='" + pass + "'");
						 HttpSession session = request.getSession();
						while(institute.next()) {
							id =institute.getInt("id");
							role=institute.getInt("role_id");
						}
						ResultSet directorStatus = st.executeQuery("select count(status) FROM `tbl_director_department_application_received_dewtails` WHERE status='Waiting For Approval' and role_id="+roleId+"");
						int statusCount2=0;
						while(directorStatus.next()) {
							statusCount2=directorStatus.getInt(1);
						}
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
						name.setAttribute("statusCount", statusCount2);

						response.sendRedirect("dashboard.jsp?_tokens='" + tokens + "'");
					} else {
						String message = "You have enter wrong credentials";
						name.setAttribute("credential", message);
						response.sendRedirect("admin-login.jsp");
						int update = DatabaseConnection.insertUpdateFromSqlQuery("update tblcaptcha set captcha='"+ newRandomCaptcha + "'");
					}
				}
				else if(isActive.equals("Y") && roleId.equals("5")) {
					if (email.equals(userName) && pass.equals(password)) {
						name.setAttribute("uname",displayName );
						ResultSet institute = st.executeQuery("SELECT id,role_id FROM `tbl_user_register_details` WHERE emailId='" + email +"' and password='" + pass + "'");
						 HttpSession session = request.getSession();
						while(institute.next()) {
							id =institute.getInt("id");
							role=institute.getInt("role_id");
						}
						ResultSet DmdirectorStatus = st.executeQuery("select count(status) FROM `tbl_project_director_excutor_application_details` WHERE status='Waiting For Approval' and role_id="+roleId+"");
						int statusCount3=0;
						while(DmdirectorStatus.next()) {
							statusCount3=DmdirectorStatus.getInt(1);
						}
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
						name.setAttribute("statusCount", statusCount3);

						response.sendRedirect("dashboard.jsp?_tokens='" + tokens + "'");
					} else {
						String message = "You have enter wrong credentials";
						name.setAttribute("credential", message);
						response.sendRedirect("admin-login.jsp");
						int update = DatabaseConnection.insertUpdateFromSqlQuery("update tblcaptcha set captcha='"+ newRandomCaptcha + "'");
					}
				}else if(isActive.equals("Y") && roleId.equals("6")) {
					if (email.equals(userName) && pass.equals(password)) {
						name.setAttribute("uname",displayName );
						ResultSet institute = st.executeQuery("SELECT id,role_id FROM `tbl_user_register_details` WHERE emailId='" + email +"' and password='" + pass + "'");
						 HttpSession session = request.getSession();
						while(institute.next()) {
							id =institute.getInt("id");
							role=institute.getInt("role_id");
						}
						ResultSet purchaseDepartment = st.executeQuery("select count(status) FROM `tbl_purchase_department_application_received_details` WHERE status='Waiting For Approval' and role_id="+roleId+"");
						int statusCount6=0;
						while(purchaseDepartment.next()) {
							statusCount6=purchaseDepartment.getInt(1);
						}
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
						name.setAttribute("statusCount", statusCount6);
						response.sendRedirect("dashboard.jsp?_tokens='" + tokens + "'");
					} else {
						String message = "You have enter wrong credentials";
						name.setAttribute("credential", message);
						response.sendRedirect("admin-login.jsp");
						int update = DatabaseConnection.insertUpdateFromSqlQuery("update tblcaptcha set captcha='"+ newRandomCaptcha + "'");
					}
				}else if(isActive.equals("Y") && roleId.equals("7")) {
					if (email.equals(userName) && pass.equals(password)) {
						name.setAttribute("uname",displayName );
						ResultSet institute = st.executeQuery("SELECT id,role_id FROM `tbl_user_register_details` WHERE emailId='" + email +"' and password='" + pass + "'");
						 HttpSession session = request.getSession();
						while(institute.next()) {
							id =institute.getInt("id");
							role=institute.getInt("role_id");
						}
						ResultSet accountDepartment = st.executeQuery("select count(status) FROM `tbl_account_department_application_received_details` WHERE status='Waiting For Approval' and role_id="+roleId+"");
						int statusCount4=0;
						while(accountDepartment.next()) {
							statusCount4=accountDepartment.getInt(1);
						}
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
						name.setAttribute("statusCount", statusCount4);

						response.sendRedirect("dashboard.jsp?_tokens='" + tokens + "'");
					} else {
						String message = "You have enter wrong credentials";
						name.setAttribute("credential", message);
						response.sendRedirect("admin-login.jsp");
						int update = DatabaseConnection.insertUpdateFromSqlQuery("update tblcaptcha set captcha='"+ newRandomCaptcha + "'");
					}
				}
				else if(isActive.equals("Y") && roleId.equals("8")) {
					if (email.equals(userName) && pass.equals(password)) {
						name.setAttribute("uname",displayName );
						ResultSet institute = st.executeQuery("SELECT id,role_id FROM `tbl_user_register_details` WHERE emailId='" + email +"' and password='" + pass + "'");
						 HttpSession session = request.getSession();
						while(institute.next()) {
							id =institute.getInt("id");
							role=institute.getInt("role_id");
						}
						ResultSet cppDepartment = st.executeQuery("select count(status) FROM `tbl_cp_department_application_received_details` WHERE status='Waiting For Approval' and role_id="+roleId+"");
						int statusCount9=0;
						while(cppDepartment.next()) {
							statusCount9=cppDepartment.getInt(1);
						}
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
						name.setAttribute("statusCount", statusCount9);
						response.sendRedirect("dashboard.jsp?_tokens='" + tokens + "'");
					} else {
						String message = "You have enter wrong credentials";
						name.setAttribute("credential", message);
						response.sendRedirect("admin-login.jsp");
						int update = DatabaseConnection.insertUpdateFromSqlQuery("update tblcaptcha set captcha='"+ newRandomCaptcha + "'");
					}
				}
				else {
					hs.setAttribute("uname",displayName );
					response.sendRedirect("dashboard.jsp?_tokens='" + tokens + "'");
					name.setAttribute("roleId",roleId );
				}
				
			} else {
				String message = "You have enter invalid verification code";
				hs.setAttribute("verificationCode", message);
				response.sendRedirect("admin-login.jsp");
				int update = DatabaseConnection.insertUpdateFromSqlQuery("update tblcaptcha set captcha='"+ newRandomCaptcha + "'");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
