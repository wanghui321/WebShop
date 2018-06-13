<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改数量</title>
</head>
<body>
	<form action="updateCount.do" method="post">
		<input type="hidden" name="bookId" value='<%=request.getParameter("bookId") %>'>
		<input type="hidden" name="price" value='<%=request.getParameter("price") %>'>
		<table align="center">
			<tr>
				<td>书名：</td>
				<td><%=request.getParameter("bookName") %></td>
			</tr>
			<tr>
				<td>数量：</td>
				<td><input type="text" name="number" value='<%=request.getParameter("number") %>'></td>
			</tr>
			<tr><td colspan="2"><input type="submit" value="确定" /></tr>
		</table>
	</form>
</body>
</html>