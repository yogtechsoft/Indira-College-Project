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
						ResultSet dd = st.executeQuery("SELECT id,insitiute_name FROM `tbl_user_register_details` WHERE emailId='" + email +"' and password='" + pass + "'");
						 HttpSession session = request.getSession();
						while(dd.next()) {
							id =dd.getInt("id");
						}
						 session.setAttribute("userId", id);
						 name.setAttribute("roleId",roleId );
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
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
						name.setAttribute("instituteName",isb );
						name.setAttribute("instid",instid );
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
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
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
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
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
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
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
						 session.setAttribute("userId", id);
						name.setAttribute("roleId",roleId );
						name.setAttribute("role",role );
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
