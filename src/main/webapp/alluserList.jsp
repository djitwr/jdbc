<%@page import="java.util.Arrays"%>
<%@page import="cn.stack.model.User"%>
<%@page import="java.util.List"%>
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
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>全部用户数据：</legend>
	

<%	
	BaseDao base = new BaseDao();
	//sql
	String sql = "select id id,username userName,password password,phone_no phoneNo,sex sex,reg_date regDate from users;";
	List<User> list = base.getListData(User.class, sql);
	
	if(list != null){
		out.println(Arrays.toString(list.toArray()));
	}else{
		out.print("无法查询到用户！");
	}
%>
</fieldset>

</body>
</html>