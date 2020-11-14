<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Top 3 Teacher List</title>
<style>
.button {
	background-color: white;
	color: black;
	border: 2px solid #4CAF50;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	transition-duration: 0.4s;
	width: 10%;
}

.button:hover {
	background-color: #4CAF50;
	color: white;
}

.styled-table {
    border-collapse: collapse;
    margin: 25px 0;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.styled-table thead tr {
    background-color: #009879;
    color: #ffffff;
    text-align: left;
}

.styled-table th,
.styled-table td {
    padding: 12px 15px;
}

.styled-table tbody tr {
    border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
}

.styled-table tbody tr:nth-of-type(odd) {
    background-color: #FFFFFF;
}

.styled-table tbody tr:last-of-type {
    border-bottom: 2px solid #009879;
}

.styled-table tbody tr.active-row {
    font-weight: bold;
    color: #009879;
}

</style>
</head>
<body>
<div align="center">
		<h1>Top 3 Teacher List</h1>
		<button onclick="goBack()" class="button">Go Back</button>
		<script>
			function goBack() {
				window.history.back();
			}
		</script>
		<table border="1" class="styled-table">
			<tr>
				<th>Teacher ID</th>
				<th>First Name</th>
				<th>Last Name </th>
				<th>Number of Students</th>
			</tr>
			<c:forEach items="${teachersList}" var="teacher" varStatus="status">
				<tr>
					<td>${teacher.teacherID}</td>
					<td>${teacher.teacherFirstName}</td>
					<td>${teacher.teacherLastName}</td>
					<td>${teacher.totalStudents}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>