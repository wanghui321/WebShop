<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>输入数量</title>
</head>
<body>
	<form action="addCart.do">
		<input type="hidden" name="bookId" value="${bookId}">
		请输入数量：<input type="text" name="count" />
		<input type="submit" value="确定" />
	</form>
	<font color="red">${errorMsg}</font>
</body>
</html>