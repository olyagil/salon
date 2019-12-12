<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="talon.add"/></title>
</head>

<body>
<%@include file="../fragments/header.jsp" %>
<%@include file="../fragments/menu.jsp" %>
<div class="container">
    <ul class="breadcrumb">
        <li class="breadcrumb-item"><a href="${accountMainUrl}">
            <fmt:message key="menu.main"/>
        </a></li>
        <li class="breadcrumb-item"><a href="${talonListUrl}"> <fmt:message
                key="menu.talon.list"/></a></li>
        <li class="breadcrumb-item active"><fmt:message key="talon.add"/></li>
    </ul>
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="modal-body">
            <c:url value="/talon/save.html" var="talonSave"/>
            <form action="${talonSave}" method="post">
                <div class="form-group">
                    <label for="service">
                        <fmt:message key="talon.choose.service"/> </label>
                    <select class="form-control selectpicker"
                            id="service"
                            name="serviceId" data-live-search="true">
                        <c:forEach items="${requestScope.services}"
                                   var="service">
                            <option value="${service.id}">
                                <c:out value="${service.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="employee">
                        <fmt:message key="talon.choose.employee"/> </label>
                    <c:choose>
                        <c:when test="${sessionScope.role eq 1}">
                            <select class="form-control" id="employee"
                                    name="employeeId">
                                <option selected
                                        value="${requestScope.employees.id}">
                                        ${requestScope.employees.surname}
                                        ${requestScope.employees.name}
                                </option>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select class="form-control selectpicker"
                                    data-live-search="true" id="employee"
                                    name="employeeId">
                                <c:forEach items="${requestScope.employees}"
                                           var="employee">
                                    <option value="${employee.id}">
                                            ${employee.surname} ${employee.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group">
                    <label for="clients"> <fmt:message
                            key="talon.choose.client"/></label>
                    <select class="form-control selectpicker"
                            data-live-search="true" id="clients"
                            name="clientId">
                        <c:forEach items="${requestScope.clients}" var="client">
                            <option value="${client.id}">
                                    ${client.surname} ${client.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="receptionDateCol">
                        <fmt:message key="talon.choose.datetime"/> </label>
                    <input type="datetime-local" id="receptionDateCol"
                           name="receptionDateCol" class="form-control"
                           required>
                </div>
                <div class="modal-footer">

                    <button type="submit"
                            class="btn tn-lg btn-success">
                        <fmt:message key="button.add"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="../fragments/footer.jsp" %>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>
