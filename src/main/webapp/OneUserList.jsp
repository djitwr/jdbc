<%@page import="cn.stack.model.User"%>
<%@page import="cn.stack.dao.BaseDao"%>
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
	String userid = request.getParameter("uid");
	String sql = "select id id,username userName,password password,phone_no phoneNo,sex sex,reg_date regDate from users where id=?";
	
	User user = base.getOneData(User.class, sql, userid);
	if(user != null){
		out.print(user);
	}else{
		out.print("无法查询到此用户！");
	}
	%>
</body>
</html>