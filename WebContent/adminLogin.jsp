<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
</head>
<body>
<form action="adminLogin.do" method="post">
	<h1 align="center">欢迎登陆后台管理页面</h1>
	<table align="center">
		<tr>
			<td>用户名:</td>
			<td><input type="text" name="adminName"></td>		
		</tr>
		<tr>
			<td>密码:</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td><input type="submit" value="登录"></td>
			<td><input type="reset" value="重置"></td>
		</tr>
		<tr>
			<td colspan="2">
				<font color="red">
				${errorMsg}</font>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="index.html">返回首页</a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>