<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
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
	User user = new User();
	//获取表单数据
	user.setUserName(request.getParameter("username"));
	user.setPassword(request.getParameter("password"));
	user.setPhoneNo(request.getParameter("phoneNo"));
	user.setSex(request.getParameter("sex"));
	user.setRegDate(new java.util.Date());
	user.setPicName(request.getParameter("uploadFile"));
	
	out.print(request.getParameter("uploadFile"));
	
 	String sql = "insert into users (username,password,phone_no,sex,reg_date,pic_head) VALUES (?,?,?,?,?,?)";
	
	InputStream in = new FileInputStream("F:/"+user.getPicName());
	out.print("user.getPicName()======="+user.getPicName());
	
	int id = base.insetrReturnId(sql,user.getUserName(),user.getPassword(),user.getPhoneNo(),user.getSex(),new java.sql.Date(user.getRegDate().getTime()),in);
	if(id > 0 ){
		out.print("执行插入成功！");
		out.print("主键"+id);
	}else{
		out.print("出错！");
	} 
	%>

</body>
</html>