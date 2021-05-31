<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sandipan.util.Mappings"%>
<html>
<head>
<title>Todo Items</title>
</head>
<body>
	<div align="center">
		<c:url var="tableUrl" value="${Mappings.ITEMS}" />
			<a href="${tableUrl}">Todo Items</a>

		<table border="1" cellpadding="3">
			<caption>
				<h2>Todo Item Details</h2>
			</caption>
			<tr> 
				<th>ID</th>
				<th>Title</th>
				<th>Deadline</th>
				<th>Details</th>
			</tr>
				<tr>
					<td><c:out value="${todoItem.id}" /></td>
					<td><c:out value="${todoItem.title}" /></td>
					<td><c:out value="${todoItem.deadline}" /></td>
					<td><c:out value="${todoItem.details}" /></td>
				</tr>
		</table>
	</div>
</body>
</html>