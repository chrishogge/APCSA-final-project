<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AIT Hospital</title>
</head>
<body>
<div>
      <h1>Doctor Management</h1>
      <a href="${pageContext.request.contextPath}/">VIEW ALL</a>
      <a href="${pageContext.request.contextPath}/add">ADD A DOCTOR</a> 
    </div>
    <div>
      <table border="1">
        <caption>All Doctors in Database</caption>
        
        <tr>
          <td>First Name</td>
          <td>Last Name</td>
          <td>Department ID</td>
          <td>Doctor ID</td>
          <td>Actions</td>
        </tr>
        <c:forEach var="doctor" items="${doctors}">
          <tr>
            <td><c:out value="${doctor.first_name}" /></td>
            <td><c:out value="${doctor.last_name}" /></td>
            <td><c:out value="${doctor.department_id}" /></td>
            <td><c:out value="${doctor.doc_id}" /></td>
            <td>
		         <a href="${pageContext.request.contextPath}/update?action=active&id=
				  <c:out value="${doctor.doc_id}" />">MAKE ACTIVE
				</a>
			        <a href="${pageContext.request.contextPath}/update?action=inactive&id=
				  <c:out value="${doctor.doc_id}" />">MAKE INACTIVE
				</a>
			        <a href="${pageContext.request.contextPath}/edit?id=
				  <c:out value="${doctor.doc_id}" />">EDIT
				</a>
		      </td>
          </tr>
        </c:forEach>
      </table>
    </div>
</body>
</html>