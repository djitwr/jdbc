<%@page import="cn.stack.dao.BaseDao"%>
<%@page import="cn.stack.model.User"%>
<%@page import="cn.stack.jdbc.MyDBUtils"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	BaseDao base = new BaseDao();
	String userId = request.getParameter("uid");
	User user = new User();
	//获取表单数据
	user.setUserName(request.getParameter("username"));
	user.setPassword(request.getParameter("password"));
	user.setPhoneNo(request.getParameter("phoneNo"));
	user.setSex(request.getParameter("sex"));
	
	String sql = "update users set username=?,password=?,phone_no=?,sex=? where id="+userId;
	
	int count = base.iud(sql, user.getUserName(),user.getPassword(),user.getPhoneNo(),user.getSex());
	if (count > 0) {
		out.print("修改成功！");
	}else{
		out.print("出错！");
	}
	%>

</body>
</html>