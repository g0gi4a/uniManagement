<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Manager</title>
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
	width: 25%;
}

.button:hover {
	background-color: #4CAF50;
	color: white;
}
</style>
</head>
<body>
	<h1 align="center">University Database</h1>

	<div align="center">
		<h2>Actions</h2>
		<form action="http://localhost:8082/uniManagement/students/new">
			<input type="submit" class="button" value="Add student to database" />
		</form>

		<form action="http://localhost:8082/uniManagement/teachers/new">
			<input type="submit" class="button" value="Add teacher to database" />
		</form>

		<form action="http://localhost:8082/uniManagement/courses/new">
			<input type="submit" class="button" value="Add course to database" />
		</form>

		<form action="http://localhost:8082/uniManagement/students/manage">
			<input type="submit" class="button" value="Manage student courses" />
		</form>

		<form action="http://localhost:8082/uniManagement/teachersAndStudents">
			<input type="submit" class="button"
				value="List teachers and students" />
		</form>

		<form action="http://localhost:8082/uniManagement/students/">
			<input type="submit" class="button"
				value="List students and their course" />
		</form>

		<form action="http://localhost:8082/uniManagement/courses">
			<input type="submit" class="button"
				value="List courses and their teachers" />
		</form>

		<form action="http://localhost:8082/uniManagement/students/credit">
			<input type="submit" class="button"
				value="List students and their total credit" />
		</form>

		<form action="http://localhost:8082/uniManagement/teachers/">
			<input type="submit" class="button"
				value="List teachers, courses and number of total students" />
		</form>

		<form action="http://localhost:8082/uniManagement/courses/top3">
			<input type="submit" class="button"
				value="List top 3 courses with most students" />
		</form>

		<form action="http://localhost:8082/uniManagement/teachers/top3">
			<input type="submit" class="button"
				value="List top 3 teachers with most students" />
		</form>

	</div>



</body>
</html>