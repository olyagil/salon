<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="service.edit"/></title>
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
        <li class="breadcrumb-item"><a href="${serviceListUrl}">
            <fmt:message key="menu.services.list"/>
        </a>
        </li>
        <li class="breadcrumb-item active">
            <fmt:message key="service.edit"/></li>
    </ul>
    <c:if test="${not empty requestScope.service}">
        <div class="row">
            <div class="col-md-7">
                <h2><fmt:message key="service.edit"/></h2>
            </div>

            <c:url value="/service/delete.html" var="serviceDeleteUrl"/>
            <div class="col-md-5">

                <form action="${serviceDeleteUrl}" method="post"
                      onsubmit="return confirmation(this,
                      'Вы уверены, что хотите удалить услугу?')">
                    <input type="hidden" value="${requestScope.service.id}"
                           name="id">
                    <button class="btn btn-lg btn-danger" type="submit">
                        <fmt:message key="button.delete"/>
                    </button>
                </form>
            </div>
            <br>
        </div>

        <div class="col-md-9">
            <c:url value="/service/save.html" var="serviceSaveUrl"/>
            <form action="${serviceSaveUrl}" class="row">
                <div class="col-md-6">
                    <input type="hidden" value="${requestScope.service.id}"
                           name="id">
                    <div class="form-group">
                        <fmt:message key="placeholder.service.name"
                                     var="serviceName"/> <label for="name">
                        <fmt:message key="service.name"/> </label>
                        <input type="text" class="form-control"
                               name="name" id="name"
                               value="${requestScope.service.name}"
                               placeholder="${serviceName}"
                               pattern="[a-zA-Zа-яА-Я0-9 ]{2,50}" required>
                    </div>

                    <div class="form-group">
                        <fmt:message key="placeholder.service.price"
                                     var="servicePrice"/>
                        <label for="price"> <fmt:message
                                key="service.price"/> </label>
                        <input type="text" class="form-control"
                               name="price" id="price"
                               value="${requestScope.service.price}"
                               placeholder="${servicePrice}"
                               pattern="[0-9]+([,\.][0-9]+)?"
                               title="The number input must start with a
                                   number and use either comma or a dot as a
                                   decimal character." required>
                    </div>

                    <div class="form-group">
                        <fmt:message key="placeholder.service.duration"
                                     var="serviceDuration"/>
                        <label for="duration">
                            <fmt:message key="service.duration"/>
                        </label>
                        <input type="text" class="form-control"
                               name="duration" id="duration"
                               value="${requestScope.service.duration}"
                               placeholder="${serviceDuration}"
                               pattern="[0-9]+([,\.][0-9]+)?" required>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <fmt:message key="placeholder.service.description"
                                     var="serviceDescription"/>
                        <label for="description">
                            <fmt:message key="service.description"/></label>
                        <textarea class="form-control" rows="8"
                                  id="description"
                                  placeholder="${serviceDescription}"
                                  name="description" required><c:out
                                value="${requestScope.service.description}"/>
                        </textarea>
                    </div>
                    <div class="row">
                        <div class="form-group">
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
    </c:if>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>