<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改图片</title>
</head>
<body>
	<form action="updateImg.do" method="post" enctype="multipart/form-data">
		<img src="<%=request.getParameter("imgUrl")%>"/>
		<input type="file" name="file"/>
		<input type="submit" value="修改图片" />
	</form>
	<a href="selectAllBook.do">返回首页</a>
</body>
</html>