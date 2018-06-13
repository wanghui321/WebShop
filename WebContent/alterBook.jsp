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
	<form action="updateBook.do" method="post">
		<table align="center">
			<tr>
				<td>书号：</td>
				<td><input type="text" name="bookId" value="${book.bookId}"/></td>
			</tr>
			<tr>
				<td>书名：</td>
				<td><input type="text" name="bookName" value="${book.bookName}"/></td>
			</tr>
			<tr>
				<td>类型：</td>
				<td>
				<c:forEach items="${bookTypeList}" var="bookType">
					<c:if test="${book.bookType.typeName==bookType.typeName}">
						<input type="radio" name="typeName" value="${bookType.typeName}" checked="checked"/>${bookType.typeName}
					</c:if>
					<c:if test="${book.bookType.typeName!=bookType.typeName}">
						<input type="radio" name="typeName" value="${bookType.typeName}"/>${bookType.typeName}
					</c:if>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td>价格:</td>
				<td><input type="text" name="price" value="${book.price}"/></td>
			</tr>
			<tr>
				<td>作者:</td>
				<td><input type="text" name="author" value="${book.author}"/></td>
			</tr>
			<tr>
				<td>出版社:</td>
				<td><input type="text" name="publisher" value="${book.publisher}"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="提交" />
			</tr>
		</table>
	</form>
</body>
</html>