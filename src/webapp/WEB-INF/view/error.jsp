<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="page.notfound"/></title>
</head>
<body>

<jsp:include page="fragments/header.jsp"/>

<div class="page-wrap d-flex flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-10 text-center">
                <c:choose>
                    <c:when test="${not empty requestScope.error}">
                        <div class="mb-4 lead"> ${requestScope.error}</div>
                    </c:when>
                    <c:when test="${not empty pageContext.errorData.requestURI}">
                    <span class="display-1 d-block"> Запрошенная страница ${pageContext.errorData.requestURI} не
                        найдена на сервере</span>
                    </c:when>
                    <c:otherwise><span class="display-1 d-block">
                        Непредвиденная ошибка приложения</span></c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>

