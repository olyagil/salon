<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="header.edit.password"/></title>
</head>
<body>
<%@include file="../../fragments/header.jsp" %>
<%@include file="../../fragments/menu.jsp" %>

<div class="container">
    <ul class="breadcrumb">
        <li class="breadcrumb-item"><a href="${accountMainUrl}">
            <fmt:message key="menu.main"/>
        </a></li>
        <li class="breadcrumb-item active">
            <fmt:message key="header.edit.password"/>
        </li>
    </ul>
    <div class="row">
        <div class="col-md-6">
            <h2><fmt:message key="header.edit.password"/></h2></div>
    </div>

    <div class="row">

        <div class="col-md-9">
            <c:url value="/account/save/password.html" var="accountEditUrl"/>
            <form action="${accountEditUrl}" class="row" method="post">
                <div class="col-md-6"></div>

                <div class="col-md-6">

                    <div class="form-group">
                        <fmt:message key="placeholder.user.password.old"
                                     var="oldPassword"/>
                        <label for="old-password">
                            <fmt:message key="user.password.old"/> </label>
                        <input type="password" class="form-control"
                               name="old-password" id="old-password"
                               placeholder="${oldPassword}"
                               pattern="[a-zA-Zа-яА-Я0-9]{2,13}" required>
                    </div>

                    <div class="form-group">
                        <fmt:message key="placeholder.user.password"
                                     var="password"/>
                        <label for="password">
                            <fmt:message key="user.password"/> </label>
                        <input type="password" class="form-control"
                               name="password" id="password"
                               placeholder="${password}"
                               pattern="[a-zA-Zа-яА-Я0-9]{2,13}" required>
                    </div>
                    <c:if test="${not empty sessionScope.success_save_password}">
                        <div class="alert alert-success" role="alert">
                                ${sessionScope.success_save_password}
                            <c:remove var="success_save_password"
                                      scope="session"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.message}">
                        <div class="alert alert-danger" role="alert">
                                ${sessionScope.message}
                            <c:remove var="message" scope="session"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.failure_save_password}">
                        <div class="alert alert-danger" role="alert">
                                ${sessionScope.failure_save_password}
                            <c:remove var="failure_save_password"
                                      scope="session"/>
                        </div>
                    </c:if>


                    <div class="row">
                        <div class="form-group ">
                            <div class="col">
                                <br>
                                <button class="btn btn-lg btn-success"
                                        type="submit">
                                    <fmt:message key="button.save"/>
                                </button>
                                <button class="btn btn-lg" type="reset">
                                    <fmt:message key="button.reset"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="../../fragments/footer.jsp" %>
</body>
</html>