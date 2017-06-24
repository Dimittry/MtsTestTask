<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title></title>

</head>
<body>
<div class="container">
    <div class="header clearfix">
        <h3 class="text-muted">MTS Test Task</h3>
    </div>
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span> ${error}
        </div>
    </c:if>
    <c:choose>
        <c:when test="${empty employee.id}">
            <h4>Добавление сотрудника</h4>
        </c:when>
        <c:otherwise>
            <h4>Редактирование сотрудника</h4>
        </c:otherwise>
    </c:choose>
    <hr/>

    <form:form method="POST" action="${contextPath}/persistEmployee" modelAttribute="employee">
        <c:if test="${not empty employee.id}">
            <div class="form-group">
                <form:label path="id">ID</form:label>
                <form:input readonly="true" path="id" class="form-control"/>
            </div>
        </c:if>

        <div class="form-group">
            <form:label path="name1">Name1</form:label>
            <form:input path="name1" class="form-control"/>
        </div>
        <div class="form-group">
            <form:label path="name2">Name2</form:label>
            <form:input path="name2" class="form-control"/>
        </div>
        <button type="submit" class="btn btn-default">Сохранить</button>
        <a href="/" class="btn btn-primary ">Назад</a>
    </form:form>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet" type="text/css"/>
</div>
</body>
</html>