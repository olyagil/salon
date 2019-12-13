<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="header.signup"/></title>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/js/main.js"></script>
<body>
<jsp:include page="fragments/header.jsp"/>
<br><br>
<div class="container">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h1><fmt:message key="header.signup"/></h1>
                <p><fmt:message key="signup.info"/></p>
            </div>
            <div class="card-body">
                <c:url value="/signup.html" var="signupUrl"/>
                <form action="${signupUrl}" method="post"
                      enctype="multipart/form-data">
                    <c:if test="${not empty sessionScope.message_signup}">
                        <div class="alert alert-danger" role="alert">
                                ${sessionScope.message_signup}
                            <c:remove var="message_signup"
                                      scope="session"/>
                        </div>
                    </c:if>
                    <div class="form-group row">
                        <fmt:message key="placeholder.user.login"
                                     var="login"/>
                        <label for="login"
                               class="col-md-4 col-form-label text-md-right">
                            <fmt:message key="user.login"/></label>
                        <div class="col-md-6">
                            <input type="text" id="login"
                                   placeholder="${login}"
                                   class="form-control" name="login"
                                   pattern="[a-zA-Zа-яА-Я0-9]{2,30}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <fmt:message key="placeholder.user.password"
                                     var="password"/> <label for="password"
                                                             class="col-md-4 col-form-label text-md-right">
                        <fmt:message key="user.password"/></label>
                        <div class="col-md-6">
                            <input type="password" id="password"
                                   placeholder="${password}"
                                   class="form-control" name="password"
                                   pattern="[a-zA-Zа-яА-Я0-9]{2,13}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <fmt:message key="placeholder.user.name"
                                     var="name"/>
                        <label for="name"
                               class="col-md-4 col-form-label text-md-right">
                            <fmt:message key="user.name"/> </label>
                        <div class="col-md-6">
                            <input type="text" id="name"
                                   placeholder="${name}"
                                   class="form-control" name="name"
                                   pattern="[a-zA-Zа-яА-Я0-9]{2,30}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <fmt:message key="placeholder.user.surname"
                                     var="surname"/>
                        <label for="surname"
                               class="col-md-4 col-form-label text-md-right">
                            <fmt:message key="user.surname"/>
                        </label>
                        <div class="col-md-6">
                            <input type="text" id="surname"
                                   placeholder="${surname}"
                                   class="form-control" name="surname"
                                   pattern="[a-zA-Zа-яА-Я0-9]{2,30}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <fmt:message key="placeholder.user.patronymic"
                                     var="patronymic"/> <label for="patronymic"
                                                               class="col-md-4 col-form-label text-md-right">
                        <fmt:message key="user.patronymic"/>
                    </label>
                        <div class="col-md-6">
                            <input type="text" id="patronymic"
                                   placeholder="${patronymic}"
                                   class="form-control" name="patronymic"
                                   pattern="[a-zA-Zа-яА-Я0-9]{2,30}" required>
                        </div>
                    </div>
                    <div class="form-group ">
                        <div class="max">
                            <label class="radio inline col-md-6 col-form-label text-md-right">
                                <input type="radio" name="gender"
                                       value="male" checked>
                                <span> <fmt:message key="user.gender.male"/>
                                </span>
                            </label>
                            <label class="radio inline col-md-2 col-form-label text-md-right">
                                <input type="radio" name="gender"
                                       value="female">
                                <span><fmt:message
                                        key="user.gender.female"/></span>
                            </label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <fmt:message key="placeholder.user.phone" var="phone"/>
                        <label for="phone"
                               class="col-md-4 col-form-label text-md-right">
                            <fmt:message key="user.phone"/>
                        </label>
                        <div class="col-md-6">
                            <input type="text" id="phone"
                                   placeholder="${phone}"
                                   class="form-control phone_mask"
                                   name="phone" required>
                            <script src="js/jquery.maskedinput.min.js"></script>
                            <script>$(".phone_mask").mask("+375(99)999-99-99");
                            </script>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="birth_date"
                               class="col-md-4 col-form-label text-md-right">
                            <fmt:message key="user.date.birth"/> </label>
                        <div class="col-md-6">
                            <input type="date" id="birth_date"
                                   class="form-control" name="birth_date"
                                   required>
                        </div>
                    </div>

                    <div class="col-md-6 offset-md-6">
                        <button type="submit" class="btn btn-lg btn-primary">
                            <fmt:message key="signup"/>
                        </button>
                    </div>
                    <br>
                    <div class="form-group">
                        <p class="text-center"><fmt:message
                                key="already.account"/>
                            <c:url value="/login.html" var="loginUrl"/>
                            <a class="nav-link" href="${loginUrl}">
                                <fmt:message key="header.login"/></a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
