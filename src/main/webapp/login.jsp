<%@page import="cn.stack.model.User"%>
<%@page import="cn.stack.dao.BaseDao"%>
<%@page import="cn.stack.jdbc.MyDBUtils"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.mysql.cj.jdbc.Driver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layui/css/layui.css"
	tppabs="${pageContext.request.contextPath}/layui/css/layui.css"
	media="all">
<script src="${pageContext.request.contextPath}/layui/layui.js"
	charset="utf-8"></script>
<html>
<head>
<meta charset="UTF-8">
<title>用户登陆</title>
</head>
<body>
	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String sql = "select username,password from users where username=? and password=?;";

		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;

		try {
			//连接数据库
			conn = MyDBUtils.getInstance().getConnection();
			//执行sql
			stat = conn.prepareStatement(sql);
			stat.setString(1, username);
			stat.setString(2, password);
			out.print(sql);

			rs = stat.executeQuery();

			if (rs.next()) {
		out.print("登陆成功");
			} else {
		out.print("登陆失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
	%>

</body>
</html>