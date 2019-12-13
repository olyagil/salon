<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Menu</title>
</head>
<body>

<div class="nav-scroller bg-white shadow-sm">
    <nav class="nav nav-underline">
        <a class="nav-link"
           href="${accountMainUrl}"><fmt:message key="menu.main"/> </a>
        <c:url value="/client/list.html" var="clientListUrl"/>
        <c:url value="/employee/list.html" var="employeeListUrl"/>

        <c:url value="/service/list.html" var="serviceListUrl"/>
        <c:url value="/talon/list.html" var="talonListUrl"/>
        <c:choose>


            <c:when test="${sessionScope.role eq 0}">
                <a class="nav-link" href="${clientListUrl}">
                    <fmt:message key="menu.client.list"/></a>

                <a class="nav-link" href="${employeeListUrl}">
                    <fmt:message key="menu.employee.list"/></a>

                <a class="nav-link" href="${serviceListUrl}">
                    <fmt:message key="menu.services.list"/> </a>

                <a class="nav-link" href="${talonListUrl}">
                    <fmt:message key="menu.talon.list"/></a>
            </c:when>

            <c:when test="${sessionScope.role eq 1}">
                <a class="nav-link" href="${talonListUrl}">
                    <fmt:message key="menu.talon.list"/></a>
            </c:when>

            <c:when test="${sessionScope.role eq 2}">
                <a class="nav-link" href="${talonListUrl}">
                    <fmt:message key="menu.talon.list"/></a>
            </c:when>

        </c:choose>

        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="${accountEditUrl}"
               id="navbarDropdown" data-toggle="dropdown">
                <fmt:message key="header.edit.profile"/> </a>
            <div class="dropdown-menu">

                <a class="dropdown-item"
                   href="${accountEditInfoUrl}">
                    <fmt:message key="header.edit.info"/></a>

                <a class="dropdown-item"
                   href="${accountEditPasswordUrl}">
                    <fmt:message key="header.edit.password"/></a>
            </div>
        </li>

        <a class="nav-link" href="${logoutUrl}">
            <fmt:message key="header.logout"/> </a>
    </nav>
</div>
<br>
</body>
</html>
