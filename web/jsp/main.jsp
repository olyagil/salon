<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property.lang"/>
<html lang="en">
<head>
    <title><fmt:message key="header.name"/></title>
</head>


<body>
<%@include file="fragments/header.jsp" %>
<img src="img/main4.jpg" alt="main" style="width:100%">

<jsp:include page="fragments/footer.jsp"/>
</body>

</html>