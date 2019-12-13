<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="user.view"/></title>
</head>
<body>
<%@include file="../fragments/header.jsp" %>
<%@include file="../fragments/menu.jsp" %>

<div class="container">
    <c:url value="/employee/edit.html" var="userEditUrl"/>
    <c:url value="/user/delete.html" var="userDeleteUrl"/>
    <br>
    <div class="row">
        <c:if test="${not empty requestScope.user}">
            <div class="col-sm-4"><!--left col-->
            </div>

            <div class="col-sm-8">
                <div class="row">
                    <div class="col-md-6">
                        <div>
                            <h5><c:out value="${requestScope.user.surname}
                            ${requestScope.user.name}
                            ${requestScope.user.patronymic}"/></h5>
                            <h6><c:out
                                    value="${requestScope.user.role.name}"/></h6>
                        </div>
                    </div>
                    <div class="col text-right">
                        <div class="btn-group">
                            <c:if test="${sessionScope.role eq 0}">
                                <form action="${userEditUrl}"
                                      method="get"
                                      class="col-md-6">
                                    <input type="hidden" name="specialistId"
                                           value="${requestScope.user.id}">
                                    <button type="submit"
                                            class="btn btn-primary btn-lg">
                                        <fmt:message key="button.edit"/>
                                    </button>
                                </form>

                                <form action="${userDeleteUrl}" method="post"
                                      class="col-md-6">
                                    <input type="hidden" name="userRole"
                                           value="${requestScope.user.role.id}">
                                    <button class="btn btn-lg btn-danger"
                                            type="submit"
                                            name="userId"
                                            value="${requestScope.user.id}">
                                        <fmt:message key="button.delete"/>
                                    </button>
                                </form>
                            </c:if>
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
                            <p><c:out value="${requestScope.user.login}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <label><fmt:message key="user.fullname"/></label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out
                                    value="${requestScope.user.surname}
                                ${requestScope.user.name}
                            ${requestScope.user.patronymic}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label><fmt:message key="user.gender"/></label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out
                                    value="${requestScope.user.gender.name}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label><fmt:message key="user.phone"/></label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out value="${requestScope.user.phone}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label><fmt:message key="user.date.birth"/></label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out
                                    value="${requestScope.user.birthDate}"/></p>
                        </div>
                    </div>

                    <c:if test="${requestScope.user.role.id eq 1}">
                        <div class="row">
                            <div class="col-md-6">
                                <label><fmt:message
                                        key="user.cabinet.number"/></label>
                            </div>
                            <div class="col-md-6">
                                <p><c:out
                                        value="${requestScope.user.cabinetNumber}"/></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label><fmt:message key="user.salary"/></label>
                            </div>
                            <div class="col-md-6">
                                <p><c:out
                                        value="${requestScope.user.salary}"/></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label><fmt:message
                                        key="user.date.employment"/></label>
                            </div>
                            <div class="col-md-6">
                                <p><c:out
                                        value="${requestScope.user.employmentDate}"/></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label><fmt:message
                                        key="user.specialty"/></label>
                            </div>
                            <div class="col-md-6">
                                <p><c:out
                                        value="${requestScope.user.specialty.name}"/></p>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:if>
    </div>
</div>
<br><br>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>
