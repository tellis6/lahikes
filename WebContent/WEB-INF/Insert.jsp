<%@page import="javax.swing.JOptionPane"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>

<%
	String driverName = "com.mysql.jdbc.Driver";
	String userName = "root";
	String dbName = "hike";
	String passwords = "1234";
	String tableName = "hike_info";

	String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + passwords;
	Connection conn = DriverManager.getConnection(url);
	Statement stmt = null;
	
	String trailName = request.getParameter("TrailName");
	String trailSum = request.getParameter("Summary");
	String longitude = request.getParameter("Longitude");
	String latitude = request.getParameter("Latitude");
	String length = request.getParameter("Length");
	String condition = request.getParameter("Conditions");
	String sql = "insert into hike.hike_info values(?,?,?,?,?,?)";
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		stmt = conn.createStatement();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, trailName);
		ps.setString(2, trailSum);
		ps.setString(3, longitude);
		ps.setString(4, latitude);
		ps.setString(5, length);
		ps.setString(6, condition);
		ps.executeUpdate();
		JOptionPane.showMessageDialog(null, "success");
		request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
	}

	catch (Exception ex) {
		out.println(ex.getMessage());
		System.out.println("failed");
	}

	
%>

