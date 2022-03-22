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
<title>用户管理</title>
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>用户操作！</legend>
	</fieldset>

	<!-- 用户登陆 -->
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>用户登陆</legend>
		<form class="layui-form" action="login.jsp">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-block">
					<input type="text" name="username" lay-verify="title"
						autocomplete="off" placeholder="请输用户名" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">密 码：</label>
				<div class="layui-input-inline">
					<input type="password" name="password" lay-verify="pass"
						placeholder="请输入密码" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<input type="submit" class="layui-btn" value="登陆" />
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</fieldset>

	<!-- 添加用户 -->
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>添加用户</legend>
		<form class="layui-form" action="userinsert.jsp">
			   
			
			<div class="layui-form-item">
				<label class="layui-form-label">用户头像</label>
				<div class="layui-input-block">
					<input type="file" name="uploadFile" />
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-block">
					<input type="text" name="username" lay-verify="title"
						autocomplete="off" placeholder="请输用户名" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">密 码：</label>
				<div class="layui-input-inline">
					<input type="password" name="password" lay-verify="pass"
						placeholder="请输入密码" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">电话号码：</label>
				<div class="layui-input-inline">
					<input type="text" name="phoneNo" lay-verify=title
						placeholder="请输入号码" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">请填写7到11位号码</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">性别：</label>
				<div class="layui-input-block">
					<input type="radio" name="sex" value="男" title="男" checked="">
					<input type="radio" name="sex" value="女" title="女"> <input
						type="radio" name="sex" value="禁" title="中性" disabled="">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<input type="submit" class="layui-btn" value="增加信息" />
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</fieldset>


	<!-- 修改用户信息 -->
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>修改用户</legend>
		<form class="layui-form" action="userupdate.jsp">
			<div class="layui-form-item">
				<label class="layui-form-label">用户编号：</label>
				<div class="layui-input-block">
					<input type="text" name="uid" lay-verify="title" autocomplete="off"
						placeholder="请输用户编号" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-block">
					<input type="text" name="username" lay-verify="title"
						autocomplete="off" placeholder="请输用户名" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">密 码：</label>
				<div class="layui-input-inline">
					<input type="password" name="password" lay-verify="pass"
						placeholder="请输入密码" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">电话号码：</label>
				<div class="layui-input-inline">
					<input type="text" name="phoneNo" lay-verify=title
						placeholder="请输入号码" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">请填写7到11位号码</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">性别：</label>
				<div class="layui-input-block">
					<input type="radio" name="sex" value="男" title="男" checked="">
					<input type="radio" name="sex" value="女" title="女"> <input
						type="radio" name="sex" value="禁" title="中性" disabled="">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<input type="submit" class="layui-btn" value="修改信息" />
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</fieldset>

	<!-- 删除用户 -->
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>删除用户：</legend>

		<form class="layui-form" action="userdelete.jsp">
			<div class="layui-form-item">
				<div class="layui-input-block">
					<div class="layui-form-item">

						<div class="layui-form-item">
							<label class="layui-form-label">用户编号：</label>
							<div class="layui-input-inline">
								<input type="text" name="uid" lay-verify=title
									placeholder="请输入用户编号" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">请输入阿拉伯数字</div>
						</div>
					
						<input type="submit" class="layui-btn layui-btn-danger" value="删除用户" />
					</div>
				</div>
			</div>
		</form>
	</fieldset>

	<!-- 查询用户 -->
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>查询用户1：</legend>
		<form class="layui-form" action="OneUserList.jsp">
			<div class="layui-form-item">
				<div class="layui-input-block">
					<div class="layui-form-item">

						<div class="layui-form-item">
							<label class="layui-form-label">用户编号：</label>
							<div class="layui-input-inline">
								<input type="text" name="uid" lay-verify=title
									placeholder="请输入用户编号" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">请输入阿拉伯数字</div>
						</div>
						<input type="submit" class="layui-btn" value="查询用户" />
					</div>
				</div>
		</form>
	</fieldset>
	
		<!-- 查询用户 -->
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>查询用户2：</legend>
		<form class="layui-form" action="BlobTest.jsp">
			<div class="layui-form-item">
				<div class="layui-input-block">
					<div class="layui-form-item">

						<div class="layui-form-item">
							<label class="layui-form-label">用户编号：</label>
							<div class="layui-input-inline">
								<input type="text" name="uid" lay-verify=title
									placeholder="请输入用户编号" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">请输入阿拉伯数字</div>
						</div>
						<input type="submit" class="layui-btn" value="查询用户(头像)" />
					</div>
				</div>
		</form>
	</fieldset>

	<form class="layui-form" action="alluserList.jsp">
		<div class="layui-form-item">
			<div class="layui-input-block">
				<div class="layui-form-item">
					<input type="submit" class="layui-btn layui-btn-radius layui-btn-normal"
						value="查询所有用户" />
				</div>
			</div>
		</div>
	</form>

	<form class="layui-form" action="userColumn.jsp">
		<div class="layui-form-item">
			<div class="layui-input-block">
				<div class="layui-form-item">
					<input type="submit" class="layui-btn layui-btn-radius layui-btn-normal"
						value="查询记录条数" />
				</div>
			</div>
		</div>
	</form>

<script src="/static/build/layui.js"></script>

</body>
</html>