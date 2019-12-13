<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="user.login"/></title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<br>
<br>
<br>
<div class="row justify-content-md-center">
    <div class="col-sm-6">
        <!-- Default form login -->
        <c:url value="/login.html" var="loginUrl"/>

        <form class="border border-light p-5 needs-validation"
              action="${loginUrl}" method="post" novalidate>
            <p class="h4 mb-4 text-center"><fmt:message key="user.login"/></p>
            <c:if test="${not empty sessionScope.message_success_signup}">
                <div class="alert alert-success" role="alert">
                    <p> ${sessionScope.message_success_signup}</p>
                    <c:remove var="message_success_signup" scope="session"/>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.message_forbidden}">
                <div class="alert alert-danger" role="alert">
                    <p> ${requestScope.message_forbidden}</p>
                    <c:remove var="message" scope="session"/>
                </div>
            </c:if>

            <!-- Login -->
            <div class="form-group ">
                <fmt:message key="placeholder.user.login" var="login"/>
                <label for="login"><fmt:message key="user.login"/></label>
                <input type="text" class="form-control mb-4" id="login"
                       placeholder="${login}" name="login"
                       required>
            </div>

            <!-- Password -->
            <div class="form-group">
                <fmt:message key="placeholder.user.password" var="password"/>
                <label for="password"><fmt:message key="user.password"/></label>
                <input type="password" class="form-control mb-4" id="password"
                       placeholder="${password}" name="password" required>
            </div>

            <!-- Sign in button -->
            <button class="btn btn-info btn-block my-4" type="submit">
                <fmt:message key="button.enter"/>
            </button>

            <!-- Register -->
            <p class="text-center">
                <c:url value="/signup.html" var="signupUrl"/>

                <fmt:message key="member"/>
                <a class="nav-link" href="${signupUrl}">
                    <fmt:message key="signup"/> </a>
            </p>
        </form>
    </div>
</div>


<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
