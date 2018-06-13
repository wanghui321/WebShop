<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
</head>
<body>
	<table border="1">
		<tr><th>订单号</th><th>书名</th><th>数量</th><th>单价</th><th>总价</th><th>地址</th><th>订单时间</th><th>是否发货</th><th colspan="4">操作</th></tr>
		<c:forEach items="${orderList}" var="order">
			<tr>
				<td align="center">${order.orderId}</td>
				<td align="center">${order.bookName}</td>
				<td align="center">${order.count}</td>
				<td align="center">${order.price}</td>
				<td align="center">${order.amount}</td>
				<td align="center">${order.address}</td>
				<td align="center">${order.orderTime}</td>
				<td align="center">${order.sendState}</td>
				<c:choose>
					<c:when test="${order.payState=='未支付'}">
						<td align="center"><a href="payForOrder.jsp?orderId=${order.orderId}&amount=${order.amount}">支付</a></td>
					</c:when>
					<c:when test="${order.payState=='已支付'}">
						<td align="center">已支付</td>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${order.signState=='未签收'}">
						<td align="center"><a href="signForOrder.do?orderId=${order.orderId}">确认收货</a></td>
					</c:when>
					<c:when test="${order.signState=='已签收'}">
						<td align="center">已签收</td>
					</c:when>
				</c:choose>
				<td align="center"><a href="deleteOrder.do?orderId=${order.orderId}">取消订单</a></td>
				<td align="center"><a href="updateOrder.do?orderId=${order.orderId}">删除订单</a></td>
			</tr>
		</c:forEach>
		<tr><td colspan="12" align="center">
			<a href="checkMyOrder.do?pageS=1">首页</a>&nbsp;&nbsp;&nbsp;
			<c:if test="${page.dpage!=1}">
			<a href="checkMyOrder.do?pageS=${page.dpage-1}">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			<c:if test="${page.dpage!=page.totalpage}">
			<a href="checkMyOrder.do?pageS=${page.dpage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			<a href="checkMyOrder.do?pageS=${page.totalpage}">尾页</a>
		</td>
		</tr>
		<tr><td colspan="12"><a href="showCart.do">返回我的购物车</a></td></tr>
	</table>
</body>
</html>