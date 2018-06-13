<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的购物车</title>
</head>
<body>
	<form action = "deleteSomeCart.do" action="post">
		<table border="1" >
			<tr><th>选择</th><th>书名</th><th>数量</th><th>单价</th><th>总额</th><th colspan="3">操作</th></tr>
			<c:forEach items="${cartList}" var="cart">
				<tr>
					<td><input type="checkbox" name="delete" value="${cart.bookId}"/></td>
					<td align="center">${cart.bookName}</td>
					<td align="center">${cart.number}</td>
					<td align="center">${cart.price}</td>
					<td align="center" id="amount">${cart.amount}</td>
					<td align="center"><a href="updateCount.jsp?number=${cart.number}&bookId=${cart.bookId}&bookName=${cart.bookName}&price=${cart.price}">修改数量</a></td>
					<td align="center"><a href="judgeAddress.do?bookId=${cart.bookId}&count=${cart.number}">提交订单</a></td>
					<td align="center"><a href="deleteCart.do?bookId=${cart.bookId}">删除购物车</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="8"><input type="submit" value="批量删除" /></td>
			</tr>
			<tr>
				<td colspan="8">总额:${total}</td>
			</tr>
			<tr>
				<td colspan="8"><a href="deleteAll.do">清空购物车</a></td>
			</tr>
			<tr>
				<td colspan="8"><a href="checkMyOrder.do">查看我的订单</a></td>
			</tr>
		</table>
		<font color="red">${errorMsg}</font>
	</form>
<a href="index.html">返回首页</a>
</body>
</html>