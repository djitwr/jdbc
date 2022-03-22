<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.Blob"%>
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
	String uid = request.getParameter("uid");
	String sql = "select pic_head from users where id=?";
	
	Blob blob = base.getOneColumnBlob(sql, uid);
	InputStream in =  blob.getBinaryStream();
	OutputStream output = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+"Resources/abc.png");
	//out.print("路径=="+request.getSession().getServletContext().getRealPath("/")+"Resources/abc.png");
	byte[] buffer = new byte[1024];
	int len = 0;
	while((len = in.read(buffer)) != -1){
		output.write(buffer, 0, len);
	}
	out.close();
	in.close();
%>

</body>
</html>