<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="menu.main"/></title>
</head>
<body>
<%@include file="../fragments/header.jsp" %>
<%@include file="../fragments/menu.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-10"><h2><fmt:message key="main.welcome"/>
            <c:out value="${requestScope.loggedUser.login}"/></h2></div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm-4"><!--left col-->
        </div>

        <div class="col-sm-8">

            <div class="row">
                <div class="col-md-6">
                    <div>
                        <h5><c:out value="${requestScope.loggedUser.surname}
                            ${requestScope.loggedUser.name}
                            ${requestScope.loggedUser.patronymic}"/></h5>
                        <h6><c:out
                                value="${requestScope.loggedUser.role.name}"/></h6>
                    </div>
                </div>
            </div>
            <hr>
            <div class="col-md-8">
                <div class="row">
                    <div class="col-md-6">
                        <label><fmt:message key="user.login"/> </label>
                    </div>
                    <div class="col-md-6">
                        <p><c:out value="${requestScope.loggedUser.login}"/></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <label><fmt:message key="user.fullname"/> </label>
                    </div>
                    <div class="col-md-6">
                        <p><c:out value="${requestScope.loggedUser.surname}
                            ${requestScope.loggedUser.name}
                            ${requestScope.loggedUser.patronymic}"/></p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <label><fmt:message key="user.phone"/> </label>
                    </div>
                    <div class="col-md-6">
                        <p><c:out value="${requestScope.loggedUser.phone}"/></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label><fmt:message key="user.date.birth"/> </label>
                    </div>
                    <div class="col-md-6">
                        <p><c:out
                                value="${requestScope.loggedUser.birthDate}"/></p>
                    </div>
                </div>

                <c:if test="${requestScope.loggedUser.role.id eq 1}">
                    <div class="row">
                        <div class="col-md-6">
                            <label><fmt:message key="user.cabinet.number"/>
                            </label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out
                                    value="${requestScope.loggedUser.cabinetNumber}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label><fmt:message key="user.salary"/> </label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out
                                    value="${requestScope.loggedUser.salary}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label><fmt:message key="user.date.employment"/>
                            </label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out
                                    value="${requestScope.loggedUser.employmentDate}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label><fmt:message key="user.specialty"/> </label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out
                                    value="${requestScope.loggedUser.specialty.name}"/></p>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>
