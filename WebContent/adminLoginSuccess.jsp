<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理界面</title>
</head>
<body>
<!-- 页面顶部 -->
	<div id="top" style="text-align:center;background-color:blue;width:100%">
		<h1 align="center">欢迎使用后台管理页面</h1>
	</div>
	<div id="bottom" style="text-align:center;width:100%">
		<div id="bottom_1" style="text-align:center;float:left"><iframe src="frame_left.jsp" width="300" height="600"></iframe></div>
		<div id="bottom_2" style="text-align:center;float:left"><iframe src="blank.jsp" width="800" height="600" name="view_frame"></iframe></div>
	</div>
</body>
</html>