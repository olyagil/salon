<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="services"/></title>
</head>
<body>

<%@include file="fragments/header.jsp" %>
<!-- Section: Features v.2 -->
<section class="my-5">
    <h3 class="display-4 text-center"><fmt:message key="services"/></h3>
    <hr class="bg-dark mb-4 w-25"> <!-- Section description -->
    <p class="lead grey-text text-center w-responsive mx-auto mb-5">
        <fmt:message key="services.info"/>
    </p>

    <!-- Grid row -->
    <div class="row">

        <c:forEach items="${requestScope.services}" var="service">

            <!-- Grid column -->
            <div class="col-md-4 mb-md-0 mb-5">

                <!-- Grid row -->
                <div class="row">

                    <!-- Grid column -->
                    <div class="col-lg-2 col-md-3 col-2">
                    </div>
                    <!-- Grid column -->

                    <!-- Grid column -->
                    <div class="col-lg-10 col-md-9 col-10">
                        <h4 class="font-weight-bold">${service.name}</h4>
                        <p class="grey-text">${service.description}</p>
                    </div>
                    <!-- Grid column -->

                </div>
                <!-- Grid row -->

            </div>
            <!-- Grid column -->
        </c:forEach>

    </div>
    <!-- Grid row -->

</section>
<%@include file="fragments/footer.jsp" %>

</body>
</html>

