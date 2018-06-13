<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>
</head>
<body>
	<p align="center">欢迎您：${user.userName}，以下是您的信息，您可以对其进行修改：</p>
	<form action="userUpdate.do" method="post">
		<table align="center">
			<tr>
				<td><input type="hidden" name="userId" value="${user.userId}"/></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="userName" value="${user.userName}"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="text" name="password" value="${user.password}"/></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" name="email" value="${user.email}"/></td>
			</tr>
		</table>
		<table align="center">
			<tr><td colspan="2">地址：</tr>
			<c:forEach items="${addressList}" var="address">
			<tr>
				<td>${address.province} ${address.city} ${address.area}</td>
				<td><a href="updateAddress.jsp?addressId=${address.addressId}&province=${address.province}&city=${address.city}&area=${address.area}">修改地址</a></td>
				<td><a href="deleteAddress.do?addressId=${address.addressId}">删除地址</a></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="2"><input type="submit" value="修改"></td>
			</tr>
			<tr>
			<td colspan="2">
				<font color="red">${errorMsg}</font>
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