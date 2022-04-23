<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Expenses</title>
</head>
<body>
    <h1>Edit Expense</h1>

    <c:if test="${error}">
        <p>Error message: "${errorMessage}"</p>
    </c:if>

    <a href="/expenses">Go back</a>

    <form:form action="/expenses/edit/${expense.id}" method="post" modelAttribute="expense">
        <input type="hidden" name="_method" value="put">
        <p>
            <form:label path="expenseName">Expense Name</form:label>
            <form:errors path="expenseName" />
            <form:input path="expenseName" />
        </p>
        <p>
            <form:label path="vendor">Vendor</form:label>
            <form:errors path="vendor" />
            <form:input path="vendor" />
        </p>
        <p>
            <form:label path="amount">Amount</form:label>
            <form:errors path="amount" />
            <form:input path="amount" />
        </p>
        <p>
            <form:label path="description">Description</form:label>
            <form:errors path="description" />
            <form:textarea path="description" />
        </p>
        <input type="submit" value="Submit" />
    </form:form>
</body>
</head>