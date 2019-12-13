<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title>Header</title>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light ">
    <div class="container">
        <c:url value="/main.html" var="mainUrl"/>
        <a class="navbar-brand" href="${mainUrl}">
            <fmt:message key="header.name"/></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <c:url value="/services.html" var="servicesUrl"/>
                    <a class="nav-link" href="${servicesUrl}">
                        <fmt:message key="header.services"/></a>
                </li>

                <li class="nav-item">
                    <c:url value="/employees.html" var="specialistUrl"/>
                    <a class="nav-link" href="${specialistUrl}">
                        <fmt:message key="header.employees"/>
                    </a>
                </li>


                <c:if test="${empty sessionScope.role}">
                    <li class="nav-item">
                        <c:url value="/signup.html" var="signupUrl"/>
                        <a class="nav-link" href="${signupUrl}">
                            <fmt:message key="header.signup"/> </a>
                    </li>
                    <li class="nav-item">
                        <c:url value="/login.html" var="loginUrl"/>
                        <a class="nav-link" href="${loginUrl}">
                            <fmt:message key="header.login"/></a>
                    </li>
                </c:if>
                <c:if test="${not empty sessionScope.role}">

                    <li class="nav-item dropdown">
                        <c:url value="/account/main.html" var="accountMainUrl"/>
                        <a class="nav-link dropdown-toggle"
                           href="${accountMainUrl}"
                           id="navbarDropdown" data-toggle="dropdown">
                            <fmt:message key="header.cabinet"/> </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item"
                               href="${accountMainUrl}">
                                <fmt:message key="header.profile"/>
                            </a>
                            <c:url value="/account/edit/info.html"
                                   var="accountEditInfoUrl"/>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item"
                               href="${accountEditInfoUrl}">
                                <fmt:message key="header.edit.info"/></a>
                            <c:url value="/account/edit/password.html"
                                   var="accountEditPasswordUrl"/>
                            <a class="dropdown-item"
                               href="${accountEditPasswordUrl}">
                                <fmt:message key="header.edit.password"/>
                            </a>
                            <c:url value="/logout.html" var="logoutUrl"/>
                            <a class="dropdown-item"
                               href="${logoutUrl}">
                                <fmt:message key="header.logout"/>
                            </a>
                        </div>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<link rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
      rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
      crossorigin="anonymous">
</body>
</html>
