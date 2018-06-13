<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
	<form action="userLogin.do" method="post">
	<h1 align="center">欢迎使用网络书城</h1>
	<table align="center">
		<tr>
			<td>用户名:</td>
			<td><input type="text" name="userName"></td>		
		</tr>
		<tr>
			<td>密码:</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr colspan="2">
			<td><input type="submit" value="登录"></td>
		</tr>
		<tr>
			<td>没有账号，请<a href="userRegist.jsp">注册</a></td>
		</tr>
		<tr>
			<td colspan="2">
				<font color="red" align="center">
					${errorMsg}
				</font>
			</td>
		</tr>
	</table>
</form>
</body>
</html>