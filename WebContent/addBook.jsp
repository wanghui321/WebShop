<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加新书</title>
</head>
<body>
	<form action="addBook.do" method="post" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td>书名：</td>
				<td><input type="text" name="bookName" /></td>
			</tr>
			<tr>
				<td>类型：</td>
				<td>  
					<c:forEach items="${bookTypeList}" var="bookType">
						<input type="radio" name="typeName" value="${bookType.typeName}">${bookType.typeName}
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>价格:</td>
				<td><input type="text" name="price" /></td>
			</tr>
			<tr>
				<td>作者:</td>
				<td><input type="text" name="author"></td>
			</tr>
			<tr>
				<td>出版社:</td>
				<td><input type="text" name="publisher"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="file" name="file" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="提交" />
				<td><input type="reset" name="重置" />
			</tr>
			<tr>
				<td colspan="2"><font color="red">${errorMsg}</font></td>
			</tr>
		</table>
	</form>
</body>
</html>