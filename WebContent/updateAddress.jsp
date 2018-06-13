<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改地址</title>
</head>
<body>
	<form action="updateAddress.do" method="post">
		<input type="hidden" name="addressId" value='<%=request.getParameter("addressId") %>'>
		<table align="center">
			<tr>
				<td>省</td>
				<td><input type="text" name="province" value='<%=request.getParameter("province")%>'></td>
			</tr>
			<tr>
				<td>市</td>
				<td><input type="text" name="city" value='<%=request.getParameter("city")%>'></td>
			</tr>
			<tr>
				<td>区</td>
				<td><input type="text" name="area" value='<%=request.getParameter("area")%>'></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交" /></td>
			</tr>
			<tr>
				<td colspan="2"><a href="userSelect.do">返回我的信息</a></td>
			</tr>
		</table>
	</form>
</body>
</html>