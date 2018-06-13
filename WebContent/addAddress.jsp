<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加新地址</title>
</head>
<body>
	<p align="center">请填写您的收货地址：</p>
	<form action="addAddress.do" method="post">
		<table align="center">
			<tr>
				<td>省：</td>
				<td><input type="text" name="province" /></td>
			</tr>
			<tr>
				<td>市：</td>
				<td><input type="text" name="city"></td>
			</tr>
			<tr>
				<td>区：</td>
				<td><input type="text" name="area"></td>
			</tr>
			<tr><td colspan="2"><input type="submit" name="提交"></td></tr>
			<tr><td colspan="2"><font color="red">${errorMsg}</font></td></tr>
		</table>
	</form>
</body>
</html>