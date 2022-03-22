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
	String userId = request.getParameter("uid");
	String sql = "DELETE FROM users WHERE id=?";
	
	int count = base.iud(sql, userId);
	if(count > 0 ){
		out.print("删除成功！");
	}else{
		out.print("删除失败！(不存在此id的用户！)");
	}
	%>

</body>
</html>