<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详细信息</title>
</head>
<body>
	<p>${book.bookName}相关信息：</p>
	<p>搜索次数：${book.checkNumber}</p>
	<table border="1">
			<tr>
				<td>书名：</td>
				<td>${book.bookName}</td>
			</tr>
			<tr>
				<td>类型：</td>
				<td>${book.bookType.typeName}</td>
			</tr>
			<tr>
				<td>价格：</td>
				<td>${book.price}</td>
			</tr>
			<tr>
				<td>作者：</td>
				<td>${book.author}</td>
			</tr>
			<tr>
				<td>出版社：</td>
				<td>${book.publisher}</td>
			</tr>
	</table>
	<a href="judgeUser.do?bookId=${book.bookId}">加入购物车</a><br/>
	<font color="red">${errorMsg}</font><br/>
	<a href="selectAllBook.do">返回首页</a><br/>
	<a href="selectByCheckNumber.do">返回热门商品</a>
</body>
</html>