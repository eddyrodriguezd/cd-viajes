<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Expenses</title>
</head>
<body>
    <c:if test="${error}">
        <p>Error message: "${errorMessage}"</p>
    </c:if>

    <h1>Safe Travels </h1>
    <table>
        <thead>
            <tr>
                <th>Expense</th>
                <th>Vendor</th>
                <th>Amount</th>
                <th colspan="2">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="expense" items="${expenses}">
                <tr>
                    <th><a href="/expenses/${expense.id}"><c:out value="${expense.expenseName}"></c:out></a></th>
                    <th><c:out value="${expense.vendor}"></c:out></th>
                    <th><c:out value="${expense.amount}"></</c:out></th>
                    <th><a href="/expenses/edit/${expense.id}">edit</a></th>
                    <th>
                        <form action="/expenses/${expense.id}" method="post">
                            <input type="hidden" name="_method" value="delete">
                            <input type="submit" value="Delete">
                        </form>
                    </th>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Add an expense:</h2>
    <h3>Description must not be blank</h3>
    <form:form action="/expenses" method="post" modelAttribute="expense">
            <p>
                <form:label path="expenseName">Expense</form:label>
                <form:errors path="expenseName"/>
                <form:input path="expenseName"/>
            </p>
            <p>
                <form:label path="vendor">Vendor</form:label>
                <form:errors path="vendor"/>
                <form:input path="vendor"/>
            </p>
            <p>
                <form:label path="amount">Amount</form:label>
                <form:errors path="amount"/>
                <form:input path="amount"/>
            </p>
            <p>
                <form:label path="description">Description</form:label>
                <form:errors path="description"/>
                <form:textarea path="description"/>
            </p>
            <input type="submit" value="Submit"/>
        </form:form>
</body>
</head>