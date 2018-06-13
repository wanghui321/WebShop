<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择地址</title>
</head>
<body>
	<form action="submitOrder.do" method="post">
		<table border="1">
			<tr><th>选择</th><th>省</th><th>市</th><th>区</th></tr>
			<c:forEach items="${addressList}" var="address">
					<tr>
						<td><input type="radio" name="addressId" value="${address.addressId}"/></td>
						<td align="center">${address.province}</td>
						<td align="center">${address.city}</td>
						<td align="center">${address.area}</td>
					</tr>
				</c:forEach>
				<tr><td colspan="4"><input type="submit" value="确定"></td></tr>
				<tr><td colspan="4"><a href="addAddress.jsp">添加新地址</a></td></tr>
		</table>
	</form>
</body>
</html>