<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="menu.services.list"/></title>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/js/main.js"></script>
</head>

<body>

<%@include file="../fragments/header.jsp" %>
<%@include file="../fragments/menu.jsp" %>

<div class="container">
    <ul class="breadcrumb">
        <c:url value="/service/list.html" var="serviceListUrl"/>
        <li class="breadcrumb-item"><a href="${accountMainUrl}">
            <fmt:message key="menu.main"/>
        </a></li>
        <li class="breadcrumb-item active"><fmt:message
                key="menu.services.list"/></li>
    </ul>
    <div class="tab-pane">
        <button type="button" class="btn btn-primary btn-lg float-right"
                data-toggle="modal"
                data-target="#modal"><fmt:message key="menu.services.add"/>
        </button>

        <c:url value="/service/list.html" var="searchServiceUrl"/>
        <form action="${searchServiceUrl}" method="get"
              class="form-inline md-form mr-auto mb-4">

            <fmt:message key="placeholder.search" var="search"/>
            <label for="search"><fmt:message key="service.find"/> </label>
            <input class="form-control mr-sm-2" aria-label="Search"
                   name="searchName" type="text" placeholder="${search}"
                   id="search" pattern="[a-zA-Zа-яА-Я0-9]{2,20}" required>
            <button class="btn btn-rounded btn-primary btn-lg"
                    type="submit"><fmt:message key="button.search"/>
            </button>
        </form>
        <c:if test="${empty requestScope.services}">
            <p><fmt:message key="service.notfound"/></p>
        </c:if>

        <c:if test="${not empty sessionScope.alert}">
            <div class="alert alert-danger" role="alert">
                <p><c:out value="${sessionScope.alert}"/></p>
                <c:remove var="alert" scope="session"/></div>
        </c:if>
        <c:if test="${not empty requestScope.services}">
            <div class="card mb-5">
                <div class="card-header"><h2><fmt:message
                        key="menu.services.list"/></h2>
                    <p><fmt:message key="service.info"/></p></div>
                <div class="card-block p-0">
                    <table
                            class="table table-bordered table-sm m-0 table-hover">
                        <thead>
                        <tr>
                            <th> №</th>
                            <th><fmt:message key="service.name"/></th>
                            <th><fmt:message key="service.description"/></th>
                            <th><fmt:message key="service.price"/></th>
                            <th><fmt:message key="service.duration"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:url value="/service/edit.html"
                               var="serviceEditUrl"/>
                        <c:forEach items="${requestScope.services}"
                                   var="service">
                            <tr onclick="submitFormById('form-${service.id}')">
                                <td><c:out value="${service.id}"/>
                                    <form id="form-${service.id}"
                                          action="${serviceEditUrl}">
                                        <input type="hidden" name="serviceId"
                                               value="${service.id}">
                                    </form>
                                </td>

                                <td><c:out value="${service.name}"/></td>
                                <td><c:out
                                        value="${service.description}"/></td>
                                <td><c:out value="${service.price}"/></td>
                                <td><c:out
                                        value="${service.duration}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <c:if test="${requestScope.noOfPages gt 1}">
                    <form action="${serviceListUrl}" method="post"
                          class="col text-center card-footer p-0">
                        <ul class="pagination justify-content-center mt-3">
                            <c:if test="${requestScope.currentPage != 1}">
                                <li class="page-item">
                                    <button type="submit" class="page-link"
                                            name="currentPage"
                                            value="${requestScope.currentPage
                                             - 1}">
                                        <fmt:message key="button.previous"/>
                                    </button>
                                </li>
                            </c:if>

                            <c:forEach begin="1" end="${requestScope.noOfPages}"
                                       var="page">
                                <c:choose>
                                    <c:when test="${requestScope.currentPage
                                    eq page}">
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

                            <c:if test="${requestScope.currentPage lt
                            requestScope.noOfPages}">
                                <li class="page-item">
                                    <button type="submit" name="currentPage"
                                            value="${requestScope.currentPage
                                             + 1}"
                                            class="page-link"><fmt:message
                                            key="button.next"/>

                                    </button>
                                </li>
                            </c:if>
                        </ul>
                    </form>
                </c:if>
            </div>
        </c:if>
    </div>
    <div class="modal fade" id="modal" tabindex="-1" role="dialog"
         aria-labelledby="label" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="label">
                        <fmt:message key="service.add"/></h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <c:url value="/service/save.html" var="serviceSaveUrl"/>
                    <form action="${serviceSaveUrl}" method="post">
                        <div class="form-group">

                            <fmt:message key="placeholder.service.name"
                                         var="serviceName"/>

                            <label for="name">
                                <fmt:message key="service.name"/> </label>
                            <input type="text" class="form-control" id="name"
                                   name="name"
                                   placeholder="${serviceName}"
                                   pattern="[a-zA-Zа-яА-Я0-9 ]{2,50}"
                                   required>
                        </div>

                        <div class="form-group">

                            <fmt:message key="placeholder.service.price"
                                         var="servicePrice"/>
                            <label for="price">
                                <fmt:message key="service.price"/> </label>
                            <input type="text" class="form-control" id="price"
                                   name="price" placeholder="${servicePrice}"
                                   pattern="[0-9]+([,\.][0-9]+)?"
                                   title="The number input must start with a
                                   number and use either comma or a dot as a
                                   decimal character."
                                   required/>
                        </div>


                        <div class="form-group">
                            <fmt:message key="placeholder.service.duration"
                                         var="serviceDuration"/>
                            <label for="duration">
                                <fmt:message key="service.duration"/>
                            </label>
                            <input type="text" class="form-control"
                                   id="duration" name="duration" required
                                   placeholder="${serviceDuration}"
                                   pattern="[0-9]{0,4}"/>
                        </div>

                        <div class="form-group">
                            <fmt:message key="placeholder.service.description"
                                         var="serviceDescription"/>
                            <label for="description">
                                <fmt:message
                                        key="service.description"/> </label>
                            <textarea class="form-control" rows="7"
                                      id="description"
                                      name="description"
                                      placeholder="${serviceDescription}"
                                      required></textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary"
                                    data-dismiss="modal">
                                <fmt:message key="button.close"/>
                            </button>
                            <button type="submit"
                                    class="btn btn-primary">
                                <fmt:message key="button.add"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../fragments/footer.jsp" %>

</body>
</html>
