<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html>
<head>
    <title><fmt:message key="menu.employee.add"/></title>
<body>
<%@include file="../fragments/header.jsp" %>
<%@include file="../fragments/menu.jsp" %>

<div class="container">
    <ul class="breadcrumb">
        <li class="breadcrumb-item"><a href="${accountMainUrl}">
            <fmt:message key="menu.main"/> </a></li>
        <li class="breadcrumb-item"><a href="${employeeListUrl}">
            <fmt:message key="menu.employee.list"/></a>
        </li>
        <li class="breadcrumb-item active">
            <fmt:message key="menu.employee.add"/>
        </li>
    </ul>
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h2><fmt:message key="menu.employee.add"/></h2>
                <p><fmt:message key="employee.info.add"/></p>
            </div>
            <c:url value="/employee/save.html" var="employeeSaveUrl"/>
            <div class="card-body">
                <c:if test="${not empty sessionScope.alert_message}">
                    <div class="alert alert-danger" role="alert">
                        <p><c:out value="${sessionScope.alert_message}"/></p>
                        <c:remove var="alert_message" scope="session"/>

                    </div>
                </c:if>
                <form action="${employeeSaveUrl}" method="post" class="row"
                      enctype="multipart/form-data">
                    <div class="col-md-5">
                        <div class="form-group">
                            <fmt:message key="placeholder.user.login"
                                         var="login"/>
                            <label for="login">
                                <fmt:message key="user.login"/> </label>
                            <input type="text" id="login"
                                   placeholder="${login}"
                                   class="form-control" name="login"
                                   pattern="[a-zA-Zа-яА-Я0-9]{2,30}" required>
                        </div>

                        <div class="form-group">
                            <fmt:message key="placeholder.user.password"
                                         var="password"/>
                            <label for="password">
                                <fmt:message key="user.password"/> </label>
                            <input type="password" id="password"
                                   placeholder="${password}"
                                   class="form-control" name="password"
                                   pattern="[a-zA-Zа-яА-Я0-9]{2,13}" required>
                        </div>

                        <div class="form-group">
                            <fmt:message key="placeholder.user.name"
                                         var="name"/>
                            <label for="name">
                                <fmt:message key="user.name"/> </label>
                            <input type="text" id="name"
                                   placeholder="${name}" class="form-control"
                                   name="name"
                                   pattern="[a-zA-Zа-яА-Я]{2,30}" required>
                        </div>

                        <div class="form-group">
                            <fmt:message key="placeholder.user.surname"
                                         var="surname"/>
                            <label for="surname">
                                <fmt:message key="user.surname"/>
                            </label>
                            <input type="text" id="surname"
                                   placeholder="${surname}"
                                   class="form-control" name="surname"
                                   pattern="[a-zA-Zа-яА-Я-]{2,30}" required>
                        </div>

                        <div class="form-group">
                            <fmt:message key="placeholder.user.patronymic"
                                         var="patronymic"/>
                            <label for="patronymic">
                                <fmt:message key="user.patronymic"/> </label>
                            <input type="text" id="patronymic"
                                   placeholder="${patronymic}"
                                   class="form-control" name="patronymic"
                                   pattern="[a-zA-Zа-яА-Я]{2,30}" required>
                        </div>

                        <div class="form-group">
                            <label for="img">
                                <fmt:message key="user.avatar"/> </label>
                            <input type="file" name="img" id="img"
                                   class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label class="radio inline">
                                <input type="radio" name="gender"
                                       value="male">
                                <span><fmt:message key="user.gender.male"/>
                                </span>
                            </label>
                            <label class="radio inline">
                                <input type="radio" name="gender"
                                       value="female" checked>
                                <span><fmt:message key="user.gender.female"/>
                                </span>
                            </label>
                        </div>
                    </div>

                    <div class="col-md-5">
                        <fmt:message key="placeholder.user.phone" var="phone"/>
                        <div class="form-group">
                            <label for="phone">
                                <fmt:message key="user.phone"/> </label>
                            <input type="text" id="phone"
                                   placeholder="${phone}"
                                   class="form-control" name="phone"
                                   pattern="[0-9]{9}" required>
                        </div>

                        <div class="form-group">
                            <label for="birth_date">
                                <fmt:message key="user.date.birth"/>
                            </label>
                            <input type="date" id="birth_date"
                                   class="form-control" name="birth_date"
                                   required>
                        </div>
                        <div class="form-group">
                            <fmt:message key="placeholder.user.cabinet.number"
                                         var="cabinetNumber"/>
                            <label for="cabinet_number">
                                <fmt:message key="user.cabinet.number"/>
                            </label>
                            <input type="text" class="form-control"
                                   name="cabinet_number" id="cabinet_number"
                                   placeholder="${cabinetNumber}"
                                   pattern="[0-9]{0,4}" required>
                        </div>

                        <div class="form-group">
                            <fmt:message key="placeholder.user.salary"
                                         var="salary"/>
                            <label for="salary">
                                <fmt:message key="user.salary"/> </label>
                            <input type="text" class="form-control"
                                   name="salary" id="salary"
                                   placeholder="${salary}"
                                   pattern="[0-9]+([,\.][0-9]+)?"
                                   title="The number input must start with a
                                   number and use either comma or a dot as a
                                   decimal character." required>
                        </div>

                        <div class="form-group">
                            <label for="employment_date">
                                <fmt:message key="user.date.employment"/>
                            </label>
                            <input type="date" class="form-control"
                                   name="employment_date" id="employment_date"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="specialty">
                                <fmt:message key="user.specialty"/>
                            </label>
                            <select class="form-control" id="specialty"
                                    name="specialty">
                                <c:forEach items="${requestScope.specialties}"
                                           var="specialty">
                                    <option value="${specialty.id}">
                                            ${specialty.name} </option>
                                </c:forEach>
                            </select>

                        </div>

                    </div>
                    <div class="col-md-6 offset-md-6">
                        <button type="submit"
                                class="btn btn-lg btn-success">
                            <fmt:message key="button.add"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>
