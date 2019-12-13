<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="talon.edit"/></title>
</head>
<body>
<%@include file="../fragments/header.jsp" %>
<%@include file="../fragments/menu.jsp" %>

<div class="container">
    <ul class="breadcrumb">
        <li class="breadcrumb-item"><a href="${accountMainUrl}">
            <fmt:message key="menu.main"/>
        </a></li>
        <li class="breadcrumb-item"><a href="${talonListUrl}">
            <fmt:message key="menu.talon.list"/>
        </a>
        </li>
        <li class="breadcrumb-item active"><fmt:message key="talon.edit"/></li>
    </ul>
    <div class="row">
        <div class="col-md-6">
            <h2><fmt:message key="talon.edit"/></h2></div>

        <c:if test="${not empty requestScope.talon}">
            <c:url value="/talon/delete.html"
                   var="talonDeleteUrl"/>
            <form action="${talonDeleteUrl}" method="post">
                <input type="hidden" value="${requestScope.talon.id}" name="id">
                <button class="btn btn-lg btn-danger" type="submit" name="id"
                        value="${requestScope.talon.id}">
                    <fmt:message key="button.delete"/>
                </button>
            </form>
        </c:if>
    </div>
    <div class="col-md-6">

        <c:if test="${not empty sessionScope.failure_save_talon}">
            <div class="alert alert-success" role="alert">
                    ${sessionScope.failure_save_talon}
                <c:remove var="failure_save_talon" scope="session"/>
            </div>
        </c:if></div>
    <br>

    <div class="col-md-9">
        <c:url value="/talon/save.html" var="talonSaveUrl"/>
        <form action="${talonSaveUrl}" method="post" class="row">
            <div class="col-md-6">
                <input type="hidden" value="${requestScope.talon.id}" name="id">
                <div class="form-group">
                    <p><fmt:message key="name.client"/>:
                        <c:out value="${requestScope.talon.client.surname}
                               ${requestScope.talon.client.name}"/></p>
                    <p><fmt:message key="name.employee"/>:
                        <c:out value="${requestScope.talon.employee.surname}
                               ${requestScope.talon.employee.name}"/></p>
                </div>

                <div class="form-group">
                    <label for="service"> <fmt:message key="service"/> </label>
                    <select class="form-control" id="service" name="serviceId">
                        <c:forEach items="${requestScope.services}"
                                   var="service">
                            <c:if test="${requestScope.talon.service.id eq service.id}">
                                <option selected value="${service.id}">
                                        ${service.name} </option>
                            </c:if>
                            <c:if test="${requestScope.talon.service.id ne service.id}">
                                <option value="${service.id}">
                                        ${service.name} </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="receptionDate">
                        <fmt:message key="service.date"/> </label>
                    <input type="text" class="form-control"
                           name="receptionDate" id="receptionDate"
                           value="${requestScope.talon.receptionDate}"
                           required>
                </div>

                <div class="form-group">
                    <label for="status">
                        <fmt:message key="talon.status"/> </label>
                    <select class="form-control" id="status" name="status">
                        <c:if test="${requestScope.talon.status eq true}">
                            <option value="true" selected>
                                <fmt:message key="status.true"/></option>
                            <option value="false">
                                <fmt:message key="status.false"/></option>
                        </c:if>
                        <c:if test="${requestScope.talon.status eq false}">
                            <option value="true"><fmt:message
                                    key="status.true"/></option>
                            <option value="false" selected>
                                <fmt:message key="status.false"/></option>
                        </c:if>
                    </select>
                </div>

                <div class="form-group">
                    <div class="col">
                        <br>
                        <input type="hidden" name="clientId"
                               value="${requestScope.talon.client.id}">
                        <input type="hidden" name="employeeId"
                               value="${requestScope.talon.employee.id}">
                        <button type="submit" class="btn btn-lg btn-primary">
                            <fmt:message key="button.save"/>
                        </button>
                        <button class="btn btn-lg" type="reset">
                            <fmt:message key="button.reset"/>
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>