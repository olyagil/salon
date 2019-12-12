<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="specialists"/></title>
</head>
<body>

<%@include file="fragments/header.jsp" %>

<div class="team-section text-center my-5">

    <!-- Section heading -->
    <h3 class="display-4 text-center"><fmt:message key="specialists"/></h3>
    <hr class="bg-dark mb-4 w-25">

    <p class="lead grey-text text-center w-responsive mx-auto mb-5">
        <fmt:message key="specialists.info"/>
    </p>

    <!-- Grid row -->
    <div class="row">
        <!-- Grid column -->
        <c:forEach items="${requestScope.employees}" var="employee">
            <div class="col-lg-4 col-md-6 mb-lg-0 mb-5">
                <div class="avatar mx-auto">
                    <img src="data:image/png;base64,${employee.avatar}"
                         class="rounded-circle z-depth-1"
                         width="300" height="300" alt="avatar">
                </div>
                <h5 class="font-weight-bold mt-4 mb-3">
                        ${employee.surname} ${employee.name}</h5>
                <p class="text-uppercase blue-text">
                    <strong>${employee.specialty.name}</strong></p>
            </div>
        </c:forEach>
        <!-- Grid column -->
    </div>
    <!-- Grid row -->
</div>


<%@include file="fragments/footer.jsp" %>

</body>
</html>
