<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付页面</title>
</head>
<body>
	<form action="payForOrder.do" method="post">
		<input type="hidden" name="orderId" value="<%=request.getParameter("orderId")%>"/>
		<p><%=request.getParameter("orderId")%>号订单需支付
			<%=request.getParameter("amount")%>元</p>
		<input type="submit" value="确认支付" />
	</form>
	<font color="red">${errorMsg}</font>
</body>
</html>