<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="menu.talon.list"/></title>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/js/main.js"></script>
</head>

<body>
<%@include file="../fragments/header.jsp" %>
<%@include file="../fragments/menu.jsp" %>
<div class="container">
    <ul class="breadcrumb">
        <li class="breadcrumb-item"><a href="${accountMainUrl}">
            <fmt:message key="menu.main"/>
        </a></li>
        <li class="breadcrumb-item active"><fmt:message
                key="menu.talon.list"/></li>
    </ul>

    <c:if test="${sessionScope.role eq 0}">
        <div class="row">
            <div class="btn-group">
                <form action="${talonListUrl}" method="post" class="col-md-4">
                    <input type="hidden" name="status" value="true">
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="status.true"/>
                    </button>
                </form>
            </div>
            <div class="btn-group">

                <form action="${talonListUrl}" method="post" class="col-md-8">
                    <input type="hidden" name="status" value="false">
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="status.false"/>
                    </button>
                </form>

            </div>


            <form action="${talonListUrl}" method="get"
                  class="form-inline md-form mr-auto mb-4">
                <fmt:message key="placeholder.search" var="search"/>
                <label for="search"><fmt:message key="talon.find"/> </label>
                <input class="form-control mr-sm-2" aria-label="Search"
                       name="searchDate" type="date" placeholder="${search}"
                       id="search" required>
                <button class="btn btn-rounded btn-primary btn-lg"
                        type="submit"><fmt:message key="button.search"/>
                </button>
            </form>
        </div>
        <c:if test="${empty requestScope.talons}">
            <p><fmt:message key="talon.notfound"/></p>
        </c:if>
    </c:if>
    <div class="row">
        <div class="col-md-12">
            <c:if test="${sessionScope.role ne 2}">
                <c:url value="/talon/add.html" var="talonAddUrl"/>
                <form action="${talonAddUrl}" method="post" class="col-md-12">
                    <button type="submit"
                            class="btn btn-primary btn-lg float-right">
                        <fmt:message key="talon.add"/>
                    </button>
                </form>
            </c:if>
        </div>
        <c:if test="${not empty sessionScope.alert}">
            <div class="alert alert-danger" role="alert">
                <p><c:out value="${sessionScope.alert}"/></p>
                <c:remove var="alert" scope="session"/></div>
        </c:if>
        <c:if test="${not empty sessionScope.success_save_talon}">
            <div class="alert alert-success" role="alert">
                    ${sessionScope.success_save_talon}
                <c:remove var="success_save_talon" scope="session"/>
                <c:remove var="talonId" scope="session"/>
            </div>
        </c:if>
        <c:if test="${not empty sessionScope.failure_save_talon}">
            <div class="alert alert-danger" role="alert">
                    ${sessionScope.failure_save_talon}
                <c:remove var="failure_save_talon" scope="session"/>
                <c:remove var="talonId" scope="session"/>
            </div>
        </c:if>
    </div>
    <c:if test="${not empty requestScope.talons}">
        <div class="tab-pane">
            <div class="card mb-5">
                <div class="card-header"><fmt:message key="menu.talon.list"/>
                </div>
                <div class="card-block p-0">
                    <table class="table table-bordered table-sm m-0 table-hover">
                        <thead>
                        <tr>
                            <th> №</th>
                            <c:if test="${sessionScope.role ne 2}">
                                <th><fmt:message key="client.fullname"/></th>
                                <th><fmt:message key="user.phone"/></th>
                            </c:if>
                            <c:if test="${sessionScope.role ne 1}">
                                <th><fmt:message key="employee.fullname"/></th>
                                <th><fmt:message key="user.phone"/></th>
                            </c:if>
                            <th><fmt:message key="service.name"/></th>
                            <th><fmt:message key="service.date"/></th>
                            <c:if test="${sessionScope.role eq 2}">
                                <th><fmt:message key="feedback"/></th>
                            </c:if>
                            <c:if test="${sessionScope.role ne 2}">
                                <th><fmt:message key="talon.status"/></th>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <c:url value="/talon/edit.html" var="talonEditUrl"/>
                        <c:forEach items="${requestScope.talons}" var="talon">
                            <c:choose>
                                <c:when test="${sessionScope.role ne 2}">
                                    <tr
                                    onclick="submitFormById('form-${talon.id}')">
                                    <td><c:out value="${talon.id}"/>
                                        <form id="form-${talon.id}"
                                              method="post"
                                              action="${talonEditUrl}">
                                            <input type="hidden" name="id"
                                                   value="${talon.id}">
                                        </form>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td><c:out value="${talon.id}"/></td>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${sessionScope.role ne 2}">
                                <td><c:out value="${talon.client.surname}
                                               ${talon.client.name}"/></td>
                                <td><c:out
                                        value="${talon.client.phone}"/></td>
                            </c:if>
                            <c:if test="${sessionScope.role ne 1}">
                                <td><c:out value="${talon.employee.surname}
                                            ${talon.employee.name} "/>
                                <td><c:out
                                        value="${talon.employee.phone}"/></td>
                            </c:if>
                            <td><c:out value="${talon.service.name}"/></td>
                            <td><c:out value="${talon.receptionDate}"/></td>
                            <c:if test="${sessionScope.role ne 2}">
                                <td><c:choose>
                                <c:when test="${talon.status}">
                                    <fmt:message key="status.true"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="status.false"/>
                                </c:otherwise>
                            </c:choose>
                            </c:if>

                            <c:if test="${sessionScope.role eq 2}">
                                <td>
                                    <c:url value="/feedback/add.html"
                                           var="feedbackAddUrl"/>
                                    <form action="${feedbackAddUrl}"
                                          method="get">
                                        <input type="hidden" name="talonId"
                                               value="${talon.id}">
                                        <button type="submit"
                                                class="btn btn-primary">
                                            <fmt:message key="feedback.add"/>
                                        </button>
                                    </form>
                                </td>
                            </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="col text-center card-footer p-0">
                    <c:if test="${requestScope.noOfPages gt 1}">
                        <form action="${talonListUrl}" method="post"
                              class="col text-center card-footer p-0">
                            <ul class="pagination justify-content-center mt-3">
                                <c:if test="${requestScope.currentPage != 1}">
                                    <li class="page-item">
                                        <button type="submit" class="page-link"
                                                name="currentPage"
                                                value="${requestScope.currentPage - 1}">
                                            <fmt:message key="button.previous"/>
                                        </button>
                                    </li>
                                </c:if>

                                <c:forEach begin="1"
                                           end="${requestScope.noOfPages}"
                                           var="page">
                                    <c:choose>
                                        <c:when test="${requestScope.currentPage eq page}">
                                            <li class="page-item active">
                                                <button type="submit"
                                                        class="page-link"
                                                        disabled>
                                                        ${page}
                                                    <span class="sr-only">(current)</span>
                                                </button>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                                <button type="submit"
                                                        class="page-link"
                                                        name="currentPage"
                                                        value="${page}">
                                                        ${page}</button>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                                    <li class="page-item">
                                        <button type="submit" name="currentPage"
                                                value="${requestScope.currentPage + 1}"
                                                class="page-link">
                                            <fmt:message key="button.previous"/>

                                        </button>
                                    </li>
                                </c:if>
                            </ul>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </c:if>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>
