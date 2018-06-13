<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="selectSomeOrder.do" method="post">
		输入用户Id搜索订单：<input type="text" name="userId" />
		<input type="submit" value="确定" />
	</form>	
	<table border="1" align="center">
		<tr><th>订单号</th><th>用户ID</th><th>书号</th><th>书名</th><th>数量</th><th>单价</th><th>总价</th><th>订单时间</th><th>地址</th><th>发货情况</th><th>状态</th><th>支付情况</th><th>收货情况</th><th>删除订单</th></tr>
		<c:forEach items="${orderList}" var="order">
			<tr>
				<td align="center">${order.orderId}</td>
				<td align="center">${order.userId}</td>
				<td align="center">${order.bookId}</td>
				<td align="center">${order.bookName}</td>
				<td align="center">${order.count}</td>
				<td align="center">${order.price}</td>
				<td align="center">${order.amount}</td>
				<td align="center">${order.orderTime}</td>
				<td align="center">${order.address}</td>
				<c:choose>
					<c:when test="${order.sendState=='未发货'}">
						<td align="center"><a href="sendOrder.do?orderId=${order.orderId}">发货</a></td>
					</c:when>
					<c:when test="${order.sendState=='已发货'}">
						<td align="center">已发货</td>
					</c:when>
				</c:choose>
				<td align="center">${order.state}</td>
				<td align="center">${order.payState}</td>
				<td align="center">${order.signState}</td>
				<td align="center"><a href="deleteOneOrder.do?orderId=${order.orderId}">删除订单</a></td>
			</tr>
		</c:forEach>	
		<tr><td colspan="14" align="center">
			<a href="checkAllOrder.do?pageS=1">首页</a>&nbsp;&nbsp;&nbsp;
			<c:if test="${page.dpage!=1}">
			<a href="checkAllOrder.do?pageS=${page.dpage-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			<c:if test="${page.dpage!=page.totalpage}">
			<a href="checkAllOrder.do?pageS=${page.dpage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			<a href="checkAllOrder.do?pageS=${page.totalpage}">尾页</a>
		</td>
		</tr>
	</table>
</body>
</html>