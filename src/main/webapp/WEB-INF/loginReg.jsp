<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login and Registration</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body class="bg-info pt-5">
	<div class="container pt-5 pb-3 text-center bg-light">
    <h1 class="mb-3">Register</h1>
    
    <p class="text-danger"><form:errors path="user.*"/></p>
    <p class="text-danger">${warning}</p>
    
    <form:form method="POST" action="/registration" modelAttribute="user" class="form-group">
    	<p>
            <form:label path="firstName">First Name:</form:label>
            <form:input type="text" path="firstName"/>
        </p>
    	<p>
            <form:label path="lastName">Last Name:</form:label>
            <form:input type="text" path="lastName"/>
        </p>
    	<p>
            <form:label path="username">Username:</form:label>
            <form:input type="text" path="username"/>
        </p>
    	<p>
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email"/>
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
        </p>
        <p>
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation"/>
        </p>
        <input type="submit" value="Register" class="btn btn-primary mt-4"/>
    </form:form>
    <p class="mt-5">If you have registered all ready please <a href="/login">Log In</a></p>
    </div>
</body>
</html>