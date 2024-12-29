package com.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Student extends HttpServlet {

	private static final long serialVersionUID = -2233943487605673925L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String gender = req.getParameter("gender");
		String mobileNumber = req.getParameter("mobilenumber");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		String url = "jdbc:mysql://localhost:3306/student";
		String un = "root";
		String pwd = "root";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("sucessfully class loaded");
			con = DriverManager.getConnection(url, un, pwd);
			System.out.println("sucessfully create connection");

			String query = "insert into stdDetails (firstname, lastname, gender, mobileNumber, email, password) values (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, gender);
			pstmt.setString(4, mobileNumber);
			pstmt.setString(5, email);
			pstmt.setString(6, password);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
