<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AIT Hospital</title>
</head>
<body>
<div>
      <h1>Doctor Management</h1>
      <h2><a href="/books">View All</a></h2>
    </div>
    <div>
      <table border="1">
        <caption>All Doctors in Database</caption>
        
        <tr>
          <td>First Name</td>
          <td>Last Name</td>
          <td>Department ID</td>
          <td>Doctor ID</td>
        </tr>
        <c:forEach var="doctor" items="${doctors}">
          <tr>
            <td><c:out value="${doctor.first_name}" /></td>
            <td><c:out value="${doctor.last_name}" /></td>
            <td><c:out value="${doctor.department_id}" /></td>
            <td><c:out value="${doctor.doc_id}" /></td>
          </tr>
        </c:forEach>
      </table>
    </div>
</body>
</html>