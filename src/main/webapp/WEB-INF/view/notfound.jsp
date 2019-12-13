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
            <div class="col-md-12 text-center">
                <span class="display-1 d-block">404</span>
                <div class="mb-4 lead">
                    <fmt:message key="page.notfound.info"/>
                </div>

            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
