<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="header.edit.info"/></title>
</head>
<body>
<%@include file="../../fragments/header.jsp" %>
<%@include file="../../fragments/menu.jsp" %>

<div class="container">
    <ul class="breadcrumb">
        <li class="breadcrumb-item"><a href="${accountMainUrl}">
            <fmt:message key="menu.main"/>
        </a></li>
        <li class="breadcrumb-item active">
            <fmt:message key="header.edit.info"/></li>
    </ul>
    <div class="row">
        <div class="col-md-6">
            <h2><fmt:message key="header.edit.info"/></h2></div>
    </div>
    <br>
    <br>
    <div class="row">
        <!--left col-->
        <c:url value="/account/save/avatar.html" var="saveImgUrl"/>
        <div class="col-md-3">
            <form action="${saveImgUrl}" method="post"
                  enctype="multipart/form-data">
                <div class="text-center">
                    <img src="data:image/png;base64,${requestScope.user.avatar}"
                         class="avatar img-circle img-thumbnail img-responsive"
                         alt="avatar">
                    <h6><fmt:message key="edit.avatar"/></h6>
                    <input type="file" name="img"
                           class="text-center center-block file-upload"
                           required>
                     <button type="submit"
                            class="btn btn-success float-right">
                        <fmt:message key="button.change"/>
                    </button>
                </div>

            </form>
        </div>
        <hr>

        <div class="col-md-9">
            <c:url value="/account/save/info.html" var="accountEditUrl"/>
            <form action="${accountEditUrl}" class="row">
                <div class="col-md-6">

                    <div class="form-group">
                        <fmt:message key="placeholder.user.surname"
                                     var="surname"/>
                        <label for="surname">
                            <fmt:message key="user.name"/> </label>
                        <input type="text" class="form-control"
                               name="surname" id="surname"
                               value="${requestScope.user.surname}"
                               placeholder="${surname}"
                               pattern="[a-zA-Zа-яА-Я0-9-]{2,30}" required>
                    </div>

                    <div class="form-group">
                        <fmt:message key="placeholder.user.name" var="name"/>
                        <label for="name"><fmt:message
                                key="user.name"/> </label>
                        <input type="text" class="form-control" name="name"
                               id="name" value="${requestScope.user.name}"
                               placeholder="${name}"
                               pattern="[a-zA-Zа-яА-Я0-9]{2,30}" required>
                    </div>

                    <div class="form-group">
                        <fmt:message key="placeholder.user.patronymic"
                                     var="patronymic"/>
                        <label for="patronymic"><fmt:message
                                key="user.patronymic"/> </label>
                        <input type="text" class="form-control"
                               name="patronymic" id="patronymic"
                               value="${requestScope.user.patronymic}"
                               placeholder="${patronymic}"
                               pattern="[a-zA-Zа-яА-Я0-9]{2,30}" required>
                    </div>
                    <div class="form-group">
                        <div class="max">
                            <c:if test="${requestScope.user.gender.id eq 0}">
                                <label class="radio inline">
                                    <input type="radio" name="gender"
                                           value="male">
                                    <span><fmt:message
                                            key="user.gender.male"/> </span>
                                </label>
                                <label class="radio inline">
                                    <input type="radio" name="gender"
                                           value="female" checked>
                                    <span><fmt:message
                                            key="user.gender.female"/></span>
                                </label>
                            </c:if>
                            <c:if test="${requestScope.user.gender.id eq 1}">
                                <label class="radio inline">
                                    <input type="radio" name="gender"
                                           value="male" checked>
                                    <span> <fmt:message
                                            key="user.gender.male"/>  </span>
                                </label>
                                <label class="radio inline">
                                    <input type="radio" name="gender"
                                           value="female">
                                    <span><fmt:message
                                            key="user.gender.female"/></span>
                                </label>
                            </c:if>
                        </div>
                    </div>

                    <c:if test="${not empty sessionScope.success_save_info}">
                        <div class="alert alert-success" role="alert">
                                ${sessionScope.success_save_info}
                            <c:remove var="success_save_info" scope="session"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.failure_save_info}">
                        <div class="alert alert-danger" role="alert">
                                ${sessionScope.failure_save_info}
                            <c:remove var="failure_save_info" scope="session"/>
                        </div>
                    </c:if>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <fmt:message key="placeholder.user.login" var="login"/>
                        <label for="login"> <fmt:message key="user.login"/>
                        </label>
                        <input type="text" class="form-control" name="login"
                               id="login" value="${requestScope.user.login}"
                               placeholder="${login}"
                               pattern="[a-zA-Zа-яА-Я0-9]{2,30}" required>
                    </div>

                    <div class="form-group">
                        <fmt:message key="placeholder.user.phone" var="phone"/>
                        <label for="phone"><fmt:message key="user.phone"/>
                        </label>
                        <input type="text" class="form-control"
                               name="phone" id="phone"
                               value="${requestScope.user.phone}"
                               placeholder="${phone}"
                               pattern="[0-9]{9}" required>
                    </div>

                    <div class="form-group">
                        <label for="birth_date"><fmt:message
                                key="user.date.birth"/> </label>
                        <input type="date" class="form-control"
                               name="birth_date" id="birth_date"
                               value="${requestScope.user.birthDate}"
                               required>
                    </div>

                    <div class="row">
                        <input type="hidden" name="id"
                               value="${requestScope.user.id}"/>
                        <div class="form-group ">
                            <div class="col">
                                <br>
                                <button class="btn btn-lg btn-success"
                                        type="submit"><fmt:message
                                        key="button.save"/>
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
    </div>
</div>
<%@include file="../../fragments/footer.jsp" %>
</body>
</html>