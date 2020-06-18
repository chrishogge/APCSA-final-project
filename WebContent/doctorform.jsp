<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>AIT Hospital</title>
  </head>
  <body>
    <div>
      <h1>Doctor Management</h1>
      
      <div>
        <a href="${pageContext.request.contextPath}/">VIEW ALL</a>
        <a href="${pageContext.request.contextPath}/add">ADD A DOCTOR</a> 
      </div>
    </div>
    <div>
      <c:if test="${doctor != null}">
        <h2>Edit Doctor</h2>
        <form action="update" method="post">
          <input type="hidden" name="id" value="<c:out value="${doctor.doc_id}" />" />
          
          <label>
            First Name
            <input type="text" name="first_name" value="<c:out value="${doctor.first_name}" />" />
          </label>
          <label>
            Last Name
            <input type="text" name="last_name" value="<c:out value="${doctor.last_name}" />" />
          </label>
          <label>
            SSN
            <input type="text" name="ssn" value="<c:out value="${doctor.ssn}" />" />
          </label>
          <label>
            Department ID
            <input type="text" name="department_id" value="<c:out value="${doctor.department_id}" />" />
          </label>
          <label>
            Active
            <input type="text" name="active" value="<c:out value="${doctor.active}" />" />
          </label>
          <input type="submit" value="Save" name="submit" />
          <input type="submit" value="Delete" name="submit" />
        </form>
      </c:if>
      <c:if test="${doctor == null}">
        <h2>Add Doctor</h2>
        <form action="insert" method="post">
          <input type="hidden" name="id" />
          
          <label>
            First Name
            <input type="text" name="first_name" />
          </label>
          <label>
            Last Name
            <input type="text" name="last_name" />
          </label>
          <label>
            SSN
            <input type="text" name="ssn" />
          </label>
          <label>
            Department ID
            <input type="text" name="department_id" />
          </label>
          <label>
            Active
            <input type="text" name="active" />
          </label>
          <input type="submit" value="Add" name="submit" />
        </form>
      </c:if>
    </div>
  </body>
</html>